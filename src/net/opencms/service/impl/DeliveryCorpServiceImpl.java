
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.DeliveryCorpDao;
import net.opencms.entity.DeliveryCorp;
import net.opencms.service.DeliveryCorpService;

import org.springframework.stereotype.Service;

@Service("deliveryCorpServiceImpl")
public class DeliveryCorpServiceImpl extends BaseServiceImpl<DeliveryCorp, Long> implements DeliveryCorpService {

	@Resource(name = "deliveryCorpDaoImpl")
	public void setBaseDao(DeliveryCorpDao deliveryCorpDao) {
		super.setBaseDao(deliveryCorpDao);
	}

}