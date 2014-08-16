
package net.opencms.dao;

import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Deposit;
import net.opencms.entity.Member;

public interface DepositDao extends BaseDao<Deposit, Long> {

	Page<Deposit> findPage(Member member, Pageable pageable);

}