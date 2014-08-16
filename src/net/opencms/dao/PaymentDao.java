
package net.opencms.dao;

import net.opencms.entity.Payment;

public interface PaymentDao extends BaseDao<Payment, Long> {

	Payment findBySn(String sn);

}