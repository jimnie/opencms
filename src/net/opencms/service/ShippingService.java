
package net.opencms.service;

import java.util.Map;

import net.opencms.entity.Shipping;

public interface ShippingService extends BaseService<Shipping, Long> {

	Shipping findBySn(String sn);

	Map<String, Object> query(Shipping shipping);

}