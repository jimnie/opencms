
package net.opencms.dao;

import net.opencms.entity.Log;

public interface LogDao extends BaseDao<Log, Long> {

	void removeAll();

}