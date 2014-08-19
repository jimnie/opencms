
package net.opencms.entity;

import net.opencms.Setting;
import net.opencms.util.SettingUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "xx_order")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_order_sequence")
public class Order extends BaseEntity {

    private static final long serialVersionUID = 8370942500343156156L;

    private static final String NAME_SEPARATOR = " ";

    public enum OrderStatus {

        unconfirmed,

        confirmed,

        completed,

        cancelled
    }

    public enum PaymentStatus {

        unpaid,

        partialPayment,

        paid,

        partialRefunds,

        refunded
    }

    public enum ShippingStatus {

        unshipped,

        partialShipment,

        shipped,

        partialReturns,

        returned
    }

    private String sn;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;

    private ShippingStatus shippingStatus;

    private BigDecimal fee;

    private BigDecimal freight;

    private BigDecimal promotionDiscount;

    private BigDecimal couponDiscount;

    private BigDecimal offsetAmount;

    private BigDecimal amountPaid;

    private Long point;

    private String consignee;

    private String areaName;

    private String address;

    private String zipCode;

    private String phone;

    private Boolean isInvoice;

    private String invoiceTitle;

    private BigDecimal tax;

    private String memo;

    private String promotion;

    private Date expire;

    private Date lockExpire;

    private Boolean isAllocatedStock;

    private String paymentMethodName;

    private String shippingMethodName;

    private Area area;

    private PaymentMethod paymentMethod;

    private ShippingMethod shippingMethod;

    private Admin operator;

    private Member member;

    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    private Set<OrderLog> orderLogs = new HashSet<OrderLog>();

    private Set<Deposit> deposits = new HashSet<Deposit>();

    private Set<Payment> payments = new HashSet<Payment>();

    private Set<Refunds> refunds = new HashSet<Refunds>();

    private Set<Shipping> shippings = new HashSet<Shipping>();

    private Set<Returns> returns = new HashSet<Returns>();

    @Column(nullable = false, updatable = false, unique = true, length = 100)
    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Column(nullable = false)
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(nullable = false)
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    @Column(nullable = false)
    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    @Column(nullable = false, precision = 21, scale = 6)
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @NotNull
    @Min(0)
    @Digits(integer = 12, fraction = 3)
    @Column(nullable = false, precision = 21, scale = 6)
    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    @Column(nullable = false, updatable = false, precision = 21, scale = 6)
    public BigDecimal getPromotionDiscount() {
        return promotionDiscount;
    }

    public void setPromotionDiscount(BigDecimal promotionDiscount) {
        this.promotionDiscount = promotionDiscount;
    }

    @Column(nullable = false, updatable = false, precision = 21, scale = 6)
    public BigDecimal getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(BigDecimal couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    @NotNull
    @Digits(integer = 12, fraction = 3)
    @Column(nullable = false, precision = 21, scale = 6)
    public BigDecimal getOffsetAmount() {
        return offsetAmount;
    }

    public void setOffsetAmount(BigDecimal offsetAmount) {
        this.offsetAmount = offsetAmount;
    }

    @Column(nullable = false, precision = 21, scale = 6)
    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    @NotNull
    @Min(0)
    @Column(nullable = false)
    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    @Column(nullable = false)
    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @NotNull
    @Column(nullable = false)
    public Boolean getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(Boolean isInvoice) {
        this.isInvoice = isInvoice;
    }

    @Length(max = 200)
    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    @Min(0)
    @Digits(integer = 12, fraction = 3)
    @Column(nullable = false, precision = 21, scale = 6)
    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    @Length(max = 200)
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Column(updatable = false)
    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Date getLockExpire() {
        return lockExpire;
    }

    public void setLockExpire(Date lockExpire) {
        this.lockExpire = lockExpire;
    }

    @Column(nullable = false)
    public Boolean getIsAllocatedStock() {
        return isAllocatedStock;
    }

    public void setIsAllocatedStock(Boolean isAllocatedStock) {
        this.isAllocatedStock = isAllocatedStock;
    }

    @Column(nullable = false)
    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    @Column(nullable = false)
    public String getShippingMethodName() {
        return shippingMethodName;
    }

    public void setShippingMethodName(String shippingMethodName) {
        this.shippingMethodName = shippingMethodName;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    public ShippingMethod getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public Admin getOperator() {
        return operator;
    }

    public void setOperator(Admin operator) {
        this.operator = operator;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Valid
    @NotEmpty
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("isGift asc")
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("createDate asc")
    public Set<OrderLog> getOrderLogs() {
        return orderLogs;
    }

    public void setOrderLogs(Set<OrderLog> orderLogs) {
        this.orderLogs = orderLogs;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<Deposit> deposits) {
        this.deposits = deposits;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("createDate asc")
    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("createDate asc")
    public Set<Refunds> getRefunds() {
        return refunds;
    }

    public void setRefunds(Set<Refunds> refunds) {
        this.refunds = refunds;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("createDate asc")
    public Set<Shipping> getShippings() {
        return shippings;
    }

    public void setShippings(Set<Shipping> shippings) {
        this.shippings = shippings;
    }

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("createDate asc")
    public Set<Returns> getReturns() {
        return returns;
    }

    public void setReturns(Set<Returns> returns) {
        this.returns = returns;
    }

    @Transient
    public String getName() {
        StringBuffer name = new StringBuffer();
        if (getOrderItems() != null) {
            for (OrderItem orderItem : getOrderItems()) {
                if (orderItem != null && orderItem.getFullName() != null) {
                    name.append(NAME_SEPARATOR).append(orderItem.getFullName());
                }
            }
            if (name.length() > 0) {
                name.deleteCharAt(0);
            }
        }
        return name.toString();
    }

    @Transient
    public int getWeight() {
        int weight = 0;
        if (getOrderItems() != null) {
            for (OrderItem orderItem : getOrderItems()) {
                if (orderItem != null) {
                    weight += orderItem.getTotalWeight();
                }
            }
        }
        return weight;
    }

    @Transient
    public int getQuantity() {
        int quantity = 0;
        if (getOrderItems() != null) {
            for (OrderItem orderItem : getOrderItems()) {
                if (orderItem != null && orderItem.getQuantity() != null) {
                    quantity += orderItem.getQuantity();
                }
            }
        }
        return quantity;
    }

    @Transient
    public int getShippedQuantity() {
        int shippedQuantity = 0;
        if (getOrderItems() != null) {
            for (OrderItem orderItem : getOrderItems()) {
                if (orderItem != null && orderItem.getShippedQuantity() != null) {
                    shippedQuantity += orderItem.getShippedQuantity();
                }
            }
        }
        return shippedQuantity;
    }

    @Transient
    public int getReturnQuantity() {
        int returnQuantity = 0;
        if (getOrderItems() != null) {
            for (OrderItem orderItem : getOrderItems()) {
                if (orderItem != null && orderItem.getReturnQuantity() != null) {
                    returnQuantity += orderItem.getReturnQuantity();
                }
            }
        }
        return returnQuantity;
    }

    @Transient
    public BigDecimal getPrice() {
        BigDecimal price = new BigDecimal(0);
        if (getOrderItems() != null) {
            for (OrderItem orderItem : getOrderItems()) {
                if (orderItem != null && orderItem.getSubtotal() != null) {
                    price = price.add(orderItem.getSubtotal());
                }
            }
        }
        return price;
    }

    @Transient
    public BigDecimal getAmount() {
        BigDecimal amount = getPrice();
        if (getFee() != null) {
            amount = amount.add(getFee());
        }
        if (getFreight() != null) {
            amount = amount.add(getFreight());
        }
        if (getPromotionDiscount() != null) {
            amount = amount.subtract(getPromotionDiscount());
        }
        if (getCouponDiscount() != null) {
            amount = amount.subtract(getCouponDiscount());
        }
        if (getOffsetAmount() != null) {
            amount = amount.add(getOffsetAmount());
        }
        if (getTax() != null) {
            amount = amount.add(getTax());
        }
        return amount.compareTo(new BigDecimal(0)) > 0 ? amount : new BigDecimal(0);
    }

    @Transient
    public BigDecimal getAmountPayable() {
        BigDecimal amountPayable = getAmount().subtract(getAmountPaid());
        return amountPayable.compareTo(new BigDecimal(0)) > 0 ? amountPayable : new BigDecimal(0);
    }

    @Transient
    public boolean isExpired() {
        return getExpire() != null && new Date().after(getExpire());
    }

    @Transient
    public OrderItem getOrderItem(String sn) {
        if (sn != null && getOrderItems() != null) {
            for (OrderItem orderItem : getOrderItems()) {
                if (orderItem != null && sn.equalsIgnoreCase(orderItem.getSn())) {
                    return orderItem;
                }
            }
        }
        return null;
    }

    @Transient
    public boolean isLocked(Admin operator) {
        return getLockExpire() != null && new Date().before(getLockExpire()) && ((operator != null && !operator.equals(getOperator())) || (operator == null && getOperator() != null));
    }

    @Transient
    public BigDecimal calculateTax() {
        BigDecimal tax = new BigDecimal(0);
        Setting setting = SettingUtils.get();
        if (setting.getIsTaxPriceEnabled()) {
            BigDecimal amount = getPrice();
            if (getPromotionDiscount() != null) {
                amount = amount.subtract(getPromotionDiscount());
            }
            if (getCouponDiscount() != null) {
                amount = amount.subtract(getCouponDiscount());
            }
            if (getOffsetAmount() != null) {
                amount = amount.add(getOffsetAmount());
            }
            tax = amount.multiply(new BigDecimal(setting.getTaxRate().toString()));
        }
        return setting.setScale(tax);
    }

    @PrePersist
    public void prePersist() {
        if (getArea() != null) {
            setAreaName(getArea().getFullName());
        }
        if (getPaymentMethod() != null) {
            setPaymentMethodName(getPaymentMethod().getName());
        }
        if (getShippingMethod() != null) {
            setShippingMethodName(getShippingMethod().getName());
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (getArea() != null) {
            setAreaName(getArea().getFullName());
        }
        if (getPaymentMethod() != null) {
            setPaymentMethodName(getPaymentMethod().getName());
        }
        if (getShippingMethod() != null) {
            setShippingMethodName(getShippingMethod().getName());
        }
    }

    @PreRemove
    public void preRemove() {
        Set<Deposit> deposits = getDeposits();
        if (deposits != null) {
            for (Deposit deposit : deposits) {
                deposit.setOrder(null);
            }
        }
    }

}