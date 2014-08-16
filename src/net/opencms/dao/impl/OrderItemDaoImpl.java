
package net.opencms.dao.impl;

import net.opencms.dao.OrderItemDao;
import net.opencms.entity.OrderItem;

import org.springframework.stereotype.Repository;

@Repository("orderItemDaoImpl")
public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem, Long> implements OrderItemDao {

}