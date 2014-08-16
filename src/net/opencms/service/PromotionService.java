
package net.opencms.service;

import java.util.List;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.entity.Promotion;

public interface PromotionService extends BaseService<Promotion, Long> {

	List<Promotion> findList(Boolean hasBegun, Boolean hasEnded, Integer count, List<Filter> filters, List<Order> orders);

	List<Promotion> findList(Boolean hasBegun, Boolean hasEnded, Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

}