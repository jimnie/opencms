
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.OrderLogDao;
import net.opencms.entity.OrderLog;
import net.opencms.service.OrderLogService;

import org.springframework.stereotype.Service;

@Service("orderLogServiceImpl")
public class OrderLogServiceImpl extends BaseServiceImpl<OrderLog, Long> implements OrderLogService {

	@Resource(name = "orderLogDaoImpl")
	public void setBaseDao(OrderLogDao orderLogDao) {
		super.setBaseDao(orderLogDao);
	}

}