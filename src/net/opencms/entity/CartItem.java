
package net.opencms.entity;

import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.opencms.Setting;
import net.opencms.util.SettingUtils;

@Entity
@Table(name = "xx_cart_item")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_cart_item_sequence")
public class CartItem extends BaseEntity {

	private static final long serialVersionUID = 2979296789363163144L;

	public static final Integer MAX_QUANTITY = 10000;

	private Integer quantity;

	private Product product;

	private Cart cart;

	private BigDecimal tempPrice;

	private Long tempPoint;

	@Column(nullable = false)
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, updatable = false)
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Transient
	public BigDecimal getTempPrice() {
		if (tempPrice == null) {
			return getSubtotal();
		}
		return tempPrice;
	}

	@Transient
	public void setTempPrice(BigDecimal tempPrice) {
		this.tempPrice = tempPrice;
	}

	@Transient
	public Long getTempPoint() {
		if (tempPoint == null) {
			return getPoint();
		}
		return tempPoint;
	}

	@Transient
	public void setTempPoint(Long tempPoint) {
		this.tempPoint = tempPoint;
	}

	@Transient
	public long getPoint() {
		if (getProduct() != null && getProduct().getPoint() != null && getQuantity() != null) {
			return getProduct().getPoint() * getQuantity();
		} else {
			return 0L;
		}
	}

	@Transient
	public int getWeight() {
		if (getProduct() != null && getProduct().getWeight() != null && getQuantity() != null) {
			return getProduct().getWeight() * getQuantity();
		} else {
			return 0;
		}
	}

	@Transient
	public BigDecimal getPrice() {
		if (getProduct() != null && getProduct().getPrice() != null) {
			Setting setting = SettingUtils.get();
			if (getCart() != null && getCart().getMember() != null && getCart().getMember().getMemberRank() != null) {
				MemberRank memberRank = getCart().getMember().getMemberRank();
				Map<MemberRank, BigDecimal> memberPrice = getProduct().getMemberPrice();
				if (memberPrice != null && !memberPrice.isEmpty()) {
					if (memberPrice.containsKey(memberRank)) {
						return setting.setScale(memberPrice.get(memberRank));
					}
				}
				if (memberRank.getScale() != null) {
					return setting.setScale(getProduct().getPrice().multiply(new BigDecimal(memberRank.getScale())));
				}
			}
			return setting.setScale(getProduct().getPrice());
		} else {
			return new BigDecimal(0);
		}
	}

	@Transient
	public BigDecimal getSubtotal() {
		if (getQuantity() != null) {
			return getPrice().multiply(new BigDecimal(getQuantity()));
		} else {
			return new BigDecimal(0);
		}
	}

	@Transient
	public boolean getIsLowStock() {
		if (getQuantity() != null && getProduct() != null && getProduct().getStock() != null && getQuantity() > getProduct().getAvailableStock()) {
			return true;
		} else {
			return false;
		}
	}

	@Transient
	public void add(int quantity) {
		if (quantity > 0) {
			if (getQuantity() != null) {
				setQuantity(getQuantity() + quantity);
			} else {
				setQuantity(quantity);
			}
		}
	}

}