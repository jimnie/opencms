
package net.opencms.entity;

import net.opencms.Setting;
import net.opencms.util.SettingUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.time.DateUtils;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "xx_cart")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_cart_sequence")
public class Cart extends BaseEntity {

    private static final long serialVersionUID = -6565967051825794561L;

    public static final int TIMEOUT = 604800;

    public static final Integer MAX_PRODUCT_COUNT = 100;

    public static final String ID_COOKIE_NAME = "cartId";

    public static final String KEY_COOKIE_NAME = "cartKey";

    private String key;

    private Member member;

    private Set<CartItem> cartItems = new HashSet<CartItem>();

    @Column(name = "cart_key", nullable = false, updatable = false)
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @Transient
    public int getWeight() {
        int weight = 0;
        if (getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                if (cartItem != null) {
                    weight += cartItem.getWeight();
                }
            }
        }
        return weight;
    }

    @Transient
    public int getQuantity() {
        int quantity = 0;
        if (getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                if (cartItem != null && cartItem.getQuantity() != null) {
                    quantity += cartItem.getQuantity();
                }
            }
        }
        return quantity;
    }

    @Transient
    public long getPoint() {
        long point = 0L;
        if (getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                if (cartItem != null) {
                    point += cartItem.getPoint();
                }
            }
        }
        return point;
    }

    @Transient
    public long getAddedPoint() {
        long originalPoint = 0L;
        long currentPoint = 0L;
        for (Promotion promotion : getPromotions()) {
            if (promotion != null) {
                int promotionQuantity = getQuantity(promotion);
                long originalPromotionPoint = getTempPoint(promotion);
                long currentPromotionPoint = promotion.calculatePoint(promotionQuantity, originalPromotionPoint);
                originalPoint += originalPromotionPoint;
                currentPoint += currentPromotionPoint;
                Set<CartItem> cartItems = getCartItems(promotion);
                for (CartItem cartItem : cartItems) {
                    if (cartItem != null && cartItem.getTempPoint() != null) {
                        long tempPoint;
                        if (originalPromotionPoint > 0) {
                            tempPoint = (long) (currentPromotionPoint / (double) originalPromotionPoint * cartItem.getTempPoint());
                        } else {
                            tempPoint = (currentPromotionPoint - originalPromotionPoint) / promotionQuantity;
                        }
                        cartItem.setTempPoint(tempPoint > 0 ? tempPoint : 0L);
                    }
                }
            }
        }
        if (getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                if (cartItem != null) {
                    cartItem.setTempPoint(null);
                }
            }
        }
        long addedPoint = currentPoint - originalPoint;
        return addedPoint > 0 ? addedPoint : 0L;
    }

    @Transient
    public long getEffectivePoint() {
        long effectivePoint = getPoint() + getAddedPoint();
        return effectivePoint > 0L ? effectivePoint : 0L;
    }

    @Transient
    public BigDecimal getPrice() {
        BigDecimal price = new BigDecimal(0);
        if (getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                if (cartItem != null && cartItem.getSubtotal() != null) {
                    price = price.add(cartItem.getSubtotal());
                }
            }
        }
        return price;
    }

    @Transient
    public BigDecimal getDiscount() {
        BigDecimal originalPrice = new BigDecimal(0);
        BigDecimal currentPrice = new BigDecimal(0);
        for (Promotion promotion : getPromotions()) {
            if (promotion != null) {
                int promotionQuantity = getQuantity(promotion);
                BigDecimal originalPromotionPrice = getTempPrice(promotion);
                BigDecimal currentPromotionPrice = promotion.calculatePrice(promotionQuantity, originalPromotionPrice);
                originalPrice = originalPrice.add(originalPromotionPrice);
                currentPrice = currentPrice.add(currentPromotionPrice);
                Set<CartItem> cartItems = getCartItems(promotion);
                for (CartItem cartItem : cartItems) {
                    if (cartItem != null && cartItem.getTempPrice() != null) {
                        BigDecimal tempPrice;
                        if (originalPromotionPrice.compareTo(new BigDecimal(0)) > 0) {
                            tempPrice = currentPromotionPrice.divide(originalPromotionPrice, 50, RoundingMode.DOWN).multiply(cartItem.getTempPrice());
                        } else {
                            tempPrice = new BigDecimal(0);
                        }
                        cartItem.setTempPrice(tempPrice.compareTo(new BigDecimal(0)) > 0 ? tempPrice : new BigDecimal(0));
                    }
                }
            }
        }
        if (getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                if (cartItem != null) {
                    cartItem.setTempPrice(null);
                }
            }
        }
        Setting setting = SettingUtils.get();
        BigDecimal discount = setting.setScale(originalPrice.subtract(currentPrice));
        return discount.compareTo(new BigDecimal(0)) > 0 ? discount : new BigDecimal(0);
    }

    @Transient
    public BigDecimal getEffectivePrice() {
        BigDecimal effectivePrice = getPrice().subtract(getDiscount());
        return effectivePrice.compareTo(new BigDecimal(0)) > 0 ? effectivePrice : new BigDecimal(0);
    }

    @Transient
    public Set<GiftItem> getGiftItems() {
        Set<GiftItem> giftItems = new HashSet<GiftItem>();
        for (Promotion promotion : getPromotions()) {
            if (promotion.getGiftItems() != null) {
                for (final GiftItem giftItem : promotion.getGiftItems()) {
                    GiftItem target = (GiftItem) CollectionUtils.find(giftItems, new Predicate() {
                        public boolean evaluate(Object object) {
                            GiftItem other = (GiftItem) object;
                            return other != null && other.getGift().equals(giftItem.getGift());
                        }
                    });
                    if (target != null) {
                        target.setQuantity(target.getQuantity() + giftItem.getQuantity());
                    } else {
                        giftItems.add(giftItem);
                    }
                }
            }
        }
        return giftItems;
    }

    @Transient
    public Set<Promotion> getPromotions() {
        Set<Promotion> allPromotions = new HashSet<Promotion>();
        if (getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                if (cartItem != null && cartItem.getProduct() != null) {
                    allPromotions.addAll(cartItem.getProduct().getValidPromotions());
                }
            }
        }
        Set<Promotion> promotions = new TreeSet<Promotion>();
        for (Promotion promotion : allPromotions) {
            if (isValid(promotion)) {
                promotions.add(promotion);
            }
        }
        return promotions;
    }

    @Transient
    private Set<CartItem> getCartItems(Promotion promotion) {
        Set<CartItem> cartItems = new HashSet<CartItem>();
        if (promotion != null && getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                if (cartItem != null && cartItem.getProduct() != null && cartItem.getProduct().isValid(promotion)) {
                    cartItems.add(cartItem);
                }
            }
        }
        return cartItems;
    }

    @Transient
    private int getQuantity(Promotion promotion) {
        int quantity = 0;
        for (CartItem cartItem : getCartItems(promotion)) {
            if (cartItem != null && cartItem.getQuantity() != null) {
                quantity += cartItem.getQuantity();
            }
        }
        return quantity;
    }

    @Transient
    private long getPoint(Promotion promotion) {
        long point = 0L;
        for (CartItem cartItem : getCartItems(promotion)) {
            if (cartItem != null) {
                point += cartItem.getPoint();
            }
        }
        return point;
    }

    @Transient
    private long getTempPoint(Promotion promotion) {
        long tempPoint = 0L;
        for (CartItem cartItem : getCartItems(promotion)) {
            if (cartItem != null && cartItem.getTempPoint() != null) {
                tempPoint += cartItem.getTempPoint();
            }
        }
        return tempPoint;
    }

    @Transient
    private BigDecimal getPrice(Promotion promotion) {
        BigDecimal price = new BigDecimal(0);
        for (CartItem cartItem : getCartItems(promotion)) {
            if (cartItem != null && cartItem.getSubtotal() != null) {
                price = price.add(cartItem.getSubtotal());
            }
        }
        return price;
    }

    @Transient
    private BigDecimal getTempPrice(Promotion promotion) {
        BigDecimal tempPrice = new BigDecimal(0);
        for (CartItem cartItem : getCartItems(promotion)) {
            if (cartItem != null && cartItem.getTempPrice() != null) {
                tempPrice = tempPrice.add(cartItem.getTempPrice());
            }
        }
        return tempPrice;
    }

    @Transient
    private boolean isValid(Promotion promotion) {
        if (promotion == null || !promotion.hasBegun() || promotion.hasEnded()) {
            return false;
        }
        if (promotion.getMemberRanks() == null || promotion.getMemberRanks().isEmpty() || getMember() == null || getMember().getMemberRank() == null || !promotion.getMemberRanks().contains(getMember().getMemberRank())) {
            return false;
        }
        Integer quantity = getQuantity(promotion);
        if ((promotion.getMinimumQuantity() != null && promotion.getMinimumQuantity() > quantity) || (promotion.getMaximumQuantity() != null && promotion.getMaximumQuantity() < quantity)) {
            return false;
        }
        BigDecimal price = getPrice(promotion);
        if ((promotion.getMinimumPrice() != null && promotion.getMinimumPrice().compareTo(price) > 0) || (promotion.getMaximumPrice() != null && promotion.getMaximumPrice().compareTo(price) < 0)) {
            return false;
        }
        return true;
    }

    @Transient
    public CartItem getCartItem(Product product) {
        if (product != null && getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                if (cartItem != null && cartItem.getProduct() != null && cartItem.getProduct().equals(product)) {
                    return cartItem;
                }
            }
        }
        return null;
    }

    @Transient
    public boolean contains(Product product) {
        if (product != null && getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                if (cartItem != null && cartItem.getProduct() != null && cartItem.getProduct().equals(product)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Transient
    public String getToken() {
        HashCodeBuilder hashCodeBuilder = new HashCodeBuilder(17, 37).append(getKey());
        if (getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                hashCodeBuilder.append(cartItem.getProduct()).append(cartItem.getQuantity()).append(cartItem.getPrice());
            }
        }
        return DigestUtils.md5Hex(hashCodeBuilder.toString());
    }

    @Transient
    public boolean getIsLowStock() {
        if (getCartItems() != null) {
            for (CartItem cartItem : getCartItems()) {
                if (cartItem != null && cartItem.getIsLowStock()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Transient
    public boolean hasExpired() {
        return new Date().after(DateUtils.addSeconds(getModifyDate(), TIMEOUT));
    }

    @Transient
    public boolean isCouponAllowed() {
        for (Promotion promotion : getPromotions()) {
            if (promotion != null && !promotion.getIsCouponAllowed()) {
                return false;
            }
        }
        return true;
    }

    @Transient
    public boolean isEmpty() {
        return getCartItems() == null || getCartItems().isEmpty();
    }

}