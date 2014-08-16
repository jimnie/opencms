
package net.opencms.service;

import java.util.List;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.entity.Navigation;
import net.opencms.entity.Navigation.Position;

public interface NavigationService extends BaseService<Navigation, Long> {

	List<Navigation> findList(Position position);

	List<Navigation> findList(Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

}