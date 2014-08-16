
package net.opencms.dao;

import net.opencms.entity.Shipping;

public interface ShippingDao extends BaseDao<Shipping, Long> {

	Shipping findBySn(String sn);

}