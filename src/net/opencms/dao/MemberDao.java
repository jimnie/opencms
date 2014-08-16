
package net.opencms.dao;

import java.util.Date;
import java.util.List;

import net.opencms.entity.Member;

public interface MemberDao extends BaseDao<Member, Long> {

	boolean usernameExists(String username);

	boolean emailExists(String email);

	Member findByUsername(String username);

	List<Member> findListByEmail(String email);

	List<Object[]> findPurchaseList(Date beginDate, Date endDate, Integer count);

}