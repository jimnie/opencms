
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.ParameterDao;
import net.opencms.entity.Parameter;
import net.opencms.service.ParameterService;

import org.springframework.stereotype.Service;

@Service("parameterServiceImpl")
public class ParameterServiceImpl extends BaseServiceImpl<Parameter, Long> implements ParameterService {

	@Resource(name = "parameterDaoImpl")
	public void setBaseDao(ParameterDao parameterDao) {
		super.setBaseDao(parameterDao);
	}

}