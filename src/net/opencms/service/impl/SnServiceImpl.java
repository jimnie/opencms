
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.SnDao;
import net.opencms.entity.Sn.Type;
import net.opencms.service.SnService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("snServiceImpl")
public class SnServiceImpl implements SnService {

	@Resource(name = "snDaoImpl")
	private SnDao snDao;

	@Transactional
	public String generate(Type type) {
		return snDao.generate(type);
	}

}