
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.RefundsDao;
import net.opencms.entity.Refunds;
import net.opencms.service.RefundsService;

import org.springframework.stereotype.Service;

@Service("refundsServiceImpl")
public class RefundsServiceImpl extends BaseServiceImpl<Refunds, Long> implements RefundsService {

	@Resource(name = "refundsDaoImpl")
	public void setBaseDao(RefundsDao refundsDao) {
		super.setBaseDao(refundsDao);
	}

}