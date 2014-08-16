
package net.opencms.dao;

import net.opencms.entity.DeliveryTemplate;

public interface DeliveryTemplateDao extends BaseDao<DeliveryTemplate, Long> {

	DeliveryTemplate findDefault();

}