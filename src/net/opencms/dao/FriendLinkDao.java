
package net.opencms.dao;

import java.util.List;

import net.opencms.entity.FriendLink;
import net.opencms.entity.FriendLink.Type;

public interface FriendLinkDao extends BaseDao<FriendLink, Long> {

	List<FriendLink> findList(Type type);

}