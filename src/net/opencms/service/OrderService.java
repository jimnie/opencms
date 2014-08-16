
package net.opencms.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.opencms.Filter;
import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Admin;
import net.opencms.entity.Cart;
import net.opencms.entity.CouponCode;
import net.opencms.entity.Member;
import net.opencms.entity.Order;
import net.opencms.entity.Order.OrderStatus;
import net.opencms.entity.Order.PaymentStatus;
import net.opencms.entity.Order.ShippingStatus;
import net.opencms.entity.Payment;
import net.opencms.entity.PaymentMethod;
import net.opencms.entity.Receiver;
import net.opencms.entity.Refunds;
import net.opencms.entity.Returns;
import net.opencms.entity.Shipping;
import net.opencms.entity.ShippingMethod;

public interface OrderService extends BaseService<Order, Long> {

	Order findBySn(String sn);

	List<Order> findList(Member member, Integer count, List<Filter> filters, List<net.opencms.Order> orders);

	Page<Order> findPage(Member member, Pageable pageable);

	Page<Order> findPage(OrderStatus orderStatus, PaymentStatus paymentStatus, ShippingStatus shippingStatus, Boolean hasExpired, Pageable pageable);

	Long count(OrderStatus orderStatus, PaymentStatus paymentStatus, ShippingStatus shippingStatus, Boolean hasExpired);

	Long waitingPaymentCount(Member member);

	Long waitingShippingCount(Member member);

	BigDecimal getSalesAmount(Date beginDate, Date endDate);

	Integer getSalesVolume(Date beginDate, Date endDate);

	void releaseStock();

	Order build(Cart cart, Receiver receiver, PaymentMethod paymentMethod, ShippingMethod shippingMethod, CouponCode couponCode, boolean isInvoice, String invoiceTitle, boolean useBalance, String memo);

	Order create(Cart cart, Receiver receiver, PaymentMethod paymentMethod, ShippingMethod shippingMethod, CouponCode couponCode, boolean isInvoice, String invoiceTitle, boolean useBalance, String memo, Admin operator);

	void update(Order order, Admin operator);

	void confirm(Order order, Admin operator);

	void complete(Order order, Admin operator);

	void cancel(Order order, Admin operator);

	void payment(Order order, Payment payment, Admin operator);

	void refunds(Order order, Refunds refunds, Admin operator);

	void shipping(Order order, Shipping shipping, Admin operator);

	void returns(Order order, Returns returns, Admin operator);

}