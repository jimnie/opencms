
package net.opencms.dao.impl;

import net.opencms.dao.PaymentMethodDao;
import net.opencms.entity.PaymentMethod;

import org.springframework.stereotype.Repository;

@Repository("paymentMethodDaoImpl")
public class PaymentMethodDaoImpl extends BaseDaoImpl<PaymentMethod, Long> implements PaymentMethodDao {

}