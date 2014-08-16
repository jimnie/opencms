
package net.opencms.dao.impl;

import net.opencms.dao.SpecificationDao;
import net.opencms.entity.Specification;

import org.springframework.stereotype.Repository;

@Repository("specificationDaoImpl")
public class SpecificationDaoImpl extends BaseDaoImpl<Specification, Long> implements SpecificationDao {

}