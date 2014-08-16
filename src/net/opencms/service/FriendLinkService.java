
package net.opencms.service;

import java.util.List;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.entity.FriendLink;
import net.opencms.entity.FriendLink.Type;

public interface FriendLinkService extends BaseService<FriendLink, Long> {

	List<FriendLink> findList(Type type);

	List<FriendLink> findList(Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

}