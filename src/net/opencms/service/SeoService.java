
package net.opencms.service;

import net.opencms.entity.Seo;
import net.opencms.entity.Seo.Type;

public interface SeoService extends BaseService<Seo, Long> {

	Seo find(Type type);

	Seo find(Type type, String cacheRegion);

}