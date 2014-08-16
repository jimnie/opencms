
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.ShippingMethodDao;
import net.opencms.entity.ShippingMethod;
import net.opencms.service.ShippingMethodService;

import org.springframework.stereotype.Service;

@Service("shippingMethodServiceImpl")
public class ShippingMethodServiceImpl extends BaseServiceImpl<ShippingMethod, Long> implements ShippingMethodService {

	@Resource(name = "shippingMethodDaoImpl")
	public void setBaseDao(ShippingMethodDao shippingMethodDao) {
		super.setBaseDao(shippingMethodDao);
	}

}