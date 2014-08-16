
package net.opencms.service;

import net.opencms.entity.Payment;

public interface PaymentService extends BaseService<Payment, Long> {

	Payment findBySn(String sn);

	void handle(Payment payment);

}