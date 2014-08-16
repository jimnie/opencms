
package net.opencms.dao.impl;

import net.opencms.dao.RoleDao;
import net.opencms.entity.Role;

import org.springframework.stereotype.Repository;

@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {

}