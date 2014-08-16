
package net.opencms.dao;

import java.util.List;

import net.opencms.entity.Navigation;
import net.opencms.entity.Navigation.Position;

public interface NavigationDao extends BaseDao<Navigation, Long> {

	List<Navigation> findList(Position position);

}