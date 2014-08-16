
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.SpecificationDao;
import net.opencms.entity.Specification;
import net.opencms.service.SpecificationService;

import org.springframework.stereotype.Service;

@Service("specificationServiceImpl")
public class SpecificationServiceImpl extends BaseServiceImpl<Specification, Long> implements SpecificationService {

	@Resource(name = "specificationDaoImpl")
	public void setBaseDao(SpecificationDao specificationDao) {
		super.setBaseDao(specificationDao);
	}

}