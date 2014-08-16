
package net.opencms.dao;

import java.util.List;

import net.opencms.entity.Area;

public interface AreaDao extends BaseDao<Area, Long> {

	List<Area> findRoots(Integer count);

}