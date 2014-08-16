
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.PaymentMethodDao;
import net.opencms.entity.PaymentMethod;
import net.opencms.service.PaymentMethodService;

import org.springframework.stereotype.Service;

@Service("paymentMethodServiceImpl")
public class PaymentMethodServiceImpl extends BaseServiceImpl<PaymentMethod, Long> implements PaymentMethodService {

	@Resource(name = "paymentMethodDaoImpl")
	public void setBaseDao(PaymentMethodDao paymentMethodDao) {
		super.setBaseDao(paymentMethodDao);
	}

}