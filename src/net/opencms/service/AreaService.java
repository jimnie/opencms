
package net.opencms.service;

import java.util.List;

import net.opencms.entity.Area;

public interface AreaService extends BaseService<Area, Long> {

	List<Area> findRoots();

	List<Area> findRoots(Integer count);

}