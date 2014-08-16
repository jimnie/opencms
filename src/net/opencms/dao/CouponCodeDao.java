
package net.opencms.dao;

import java.util.List;

import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Coupon;
import net.opencms.entity.CouponCode;
import net.opencms.entity.Member;

public interface CouponCodeDao extends BaseDao<CouponCode, Long> {

	boolean codeExists(String code);

	CouponCode findByCode(String code);

	CouponCode build(Coupon coupon, Member member);

	List<CouponCode> build(Coupon coupon, Member member, Integer count);

	Page<CouponCode> findPage(Member member, Pageable pageable);

	Long count(Coupon coupon, Member member, Boolean hasBegun, Boolean hasExpired, Boolean isUsed);

}