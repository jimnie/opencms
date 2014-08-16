
package net.opencms.service;

import net.opencms.entity.AdPosition;

public interface AdPositionService extends BaseService<AdPosition, Long> {

	AdPosition find(Long id, String cacheRegion);

}