
package net.opencms.dao.impl;

import net.opencms.dao.OrderLogDao;
import net.opencms.entity.OrderLog;

import org.springframework.stereotype.Repository;

@Repository("orderLogDaoImpl")
public class OrderLogDaoImpl extends BaseDaoImpl<OrderLog, Long> implements OrderLogDao {

}