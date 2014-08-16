
package net.opencms.service;

import net.opencms.entity.DeliveryCenter;

public interface DeliveryCenterService extends BaseService<DeliveryCenter, Long> {

	DeliveryCenter findDefault();

}