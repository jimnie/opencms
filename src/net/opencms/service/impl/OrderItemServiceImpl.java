
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.OrderItemDao;
import net.opencms.entity.OrderItem;
import net.opencms.service.OrderItemService;

import org.springframework.stereotype.Service;

@Service("orderItemServiceImpl")
public class OrderItemServiceImpl extends BaseServiceImpl<OrderItem, Long> implements OrderItemService {

	@Resource(name = "orderItemDaoImpl")
	public void setBaseDao(OrderItemDao orderItemDao) {
		super.setBaseDao(orderItemDao);
	}

}