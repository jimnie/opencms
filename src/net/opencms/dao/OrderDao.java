
package net.opencms.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.opencms.Filter;
import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Member;
import net.opencms.entity.Order;
import net.opencms.entity.Order.OrderStatus;
import net.opencms.entity.Order.PaymentStatus;
import net.opencms.entity.Order.ShippingStatus;

public interface OrderDao extends BaseDao<Order, Long> {

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

}