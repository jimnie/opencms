
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.LogDao;
import net.opencms.entity.Log;
import net.opencms.service.LogService;

import org.springframework.stereotype.Service;

@Service("logServiceImpl")
public class LogServiceImpl extends BaseServiceImpl<Log, Long> implements LogService {

	@Resource(name = "logDaoImpl")
	private LogDao logDao;

	@Resource(name = "logDaoImpl")
	public void setBaseDao(LogDao logDao) {
		super.setBaseDao(logDao);
	}

	public void clear() {
		logDao.removeAll();
	}

}