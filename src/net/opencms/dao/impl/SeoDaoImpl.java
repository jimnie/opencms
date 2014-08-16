
package net.opencms.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import net.opencms.dao.SeoDao;
import net.opencms.entity.Seo;
import net.opencms.entity.Seo.Type;

import org.springframework.stereotype.Repository;

@Repository("seoDaoImpl")
public class SeoDaoImpl extends BaseDaoImpl<Seo, Long> implements SeoDao {

	public Seo find(Type type) {
		if (type == null) {
			return null;
		}
		try {
			String jpql = "select seo from Seo seo where seo.type = :type";
			return entityManager.createQuery(jpql, Seo.class).setFlushMode(FlushModeType.COMMIT).setParameter("type", type).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}