
package net.opencms.dao;

import net.opencms.entity.DeliveryCenter;

public interface DeliveryCenterDao extends BaseDao<DeliveryCenter, Long> {

	DeliveryCenter findDefault();

}