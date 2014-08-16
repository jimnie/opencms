
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.SpecificationValueDao;
import net.opencms.entity.SpecificationValue;
import net.opencms.service.SpecificationValueService;

import org.springframework.stereotype.Service;

@Service("specificationValueServiceImpl")
public class SpecificationValueServiceImpl extends BaseServiceImpl<SpecificationValue, Long> implements SpecificationValueService {

	@Resource(name = "specificationValueDaoImpl")
	public void setBaseDao(SpecificationValueDao specificationValueDao) {
		super.setBaseDao(specificationValueDao);
	}

}