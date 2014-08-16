
package net.opencms.dao;

import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Coupon;

public interface CouponDao extends BaseDao<Coupon, Long> {

	Page<Coupon> findPage(Boolean isEnabled, Boolean isExchange, Boolean hasExpired, Pageable pageable);

}