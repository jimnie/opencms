
package net.opencms.service;

import net.opencms.entity.DeliveryTemplate;

public interface DeliveryTemplateService extends BaseService<DeliveryTemplate, Long> {

	DeliveryTemplate findDefault();

}