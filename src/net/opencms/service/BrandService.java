
package net.opencms.service;

import java.util.List;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.entity.Brand;

public interface BrandService extends BaseService<Brand, Long> {

	List<Brand> findList(Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

}