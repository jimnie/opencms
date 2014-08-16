
package net.opencms.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.opencms.entity.Admin;
import net.opencms.entity.Member;

public interface MemberService extends BaseService<Member, Long> {

	boolean usernameExists(String username);

	boolean usernameDisabled(String username);

	boolean emailExists(String email);

	boolean emailUnique(String previousEmail, String currentEmail);

	void save(Member member, Admin operator);

	void update(Member member, Integer modifyPoint, BigDecimal modifyBalance, String depositMemo, Admin operator);

	Member findByUsername(String username);

	List<Member> findListByEmail(String email);

	List<Object[]> findPurchaseList(Date beginDate, Date endDate, Integer count);

	boolean isAuthenticated();

	Member getCurrent();

	String getCurrentUsername();

}