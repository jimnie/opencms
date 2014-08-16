
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.AttributeDao;
import net.opencms.entity.Attribute;
import net.opencms.service.AttributeService;

import org.springframework.stereotype.Service;

@Service("attributeServiceImpl")
public class AttributeServiceImpl extends BaseServiceImpl<Attribute, Long> implements AttributeService {

	@Resource(name = "attributeDaoImpl")
	public void setBaseDao(AttributeDao attributeDao) {
		super.setBaseDao(attributeDao);
	}

}