
package net.opencms.service;

import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Coupon;

public interface CouponService extends BaseService<Coupon, Long> {

	Page<Coupon> findPage(Boolean isEnabled, Boolean isExchange, Boolean hasExpired, Pageable pageable);

}