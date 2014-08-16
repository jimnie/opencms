
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.ParameterGroupDao;
import net.opencms.entity.ParameterGroup;
import net.opencms.service.ParameterGroupService;

import org.springframework.stereotype.Service;

@Service("parameterGroupServiceImpl")
public class ParameterGroupServiceImpl extends BaseServiceImpl<ParameterGroup, Long> implements ParameterGroupService {

	@Resource(name = "parameterGroupDaoImpl")
	public void setBaseDao(ParameterGroupDao parameterGroupDao) {
		super.setBaseDao(parameterGroupDao);
	}

}