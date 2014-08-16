
package net.opencms.service;

import java.util.List;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.entity.Tag;
import net.opencms.entity.Tag.Type;

public interface TagService extends BaseService<Tag, Long> {

	List<Tag> findList(Type type);

	List<Tag> findList(Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

}