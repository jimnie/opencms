
package net.opencms.dao.impl;

import javax.persistence.FlushModeType;

import net.opencms.dao.LogDao;
import net.opencms.entity.Log;

import org.springframework.stereotype.Repository;

@Repository("logDaoImpl")
public class LogDaoImpl extends BaseDaoImpl<Log, Long> implements LogDao {

	public void removeAll() {
		String jpql = "delete from Log log";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
	}

}