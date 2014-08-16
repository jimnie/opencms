
package net.opencms.dao;

import net.opencms.entity.Seo;
import net.opencms.entity.Seo.Type;

public interface SeoDao extends BaseDao<Seo, Long> {

	Seo find(Type type);

}