
package net.opencms.dao;

import java.util.List;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.entity.Promotion;

public interface PromotionDao extends BaseDao<Promotion, Long> {

	List<Promotion> findList(Boolean hasBegun, Boolean hasEnded, Integer count, List<Filter> filters, List<Order> orders);

}