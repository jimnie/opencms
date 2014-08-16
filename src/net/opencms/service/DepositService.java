
package net.opencms.service;

import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Deposit;
import net.opencms.entity.Member;

public interface DepositService extends BaseService<Deposit, Long> {

	Page<Deposit> findPage(Member member, Pageable pageable);

}