
package net.opencms.dao;

import java.util.List;
import java.util.Set;

import net.opencms.entity.Parameter;
import net.opencms.entity.ParameterGroup;

public interface ParameterDao extends BaseDao<Parameter, Long> {

	List<Parameter> findList(ParameterGroup parameterGroup, Set<Parameter> excludes);

}