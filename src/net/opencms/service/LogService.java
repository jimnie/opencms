
package net.opencms.service;

import net.opencms.entity.Log;

public interface LogService extends BaseService<Log, Long> {

	void clear();

}