
package net.opencms.dao;

import java.util.List;

import net.opencms.entity.MemberAttribute;

public interface MemberAttributeDao extends BaseDao<MemberAttribute, Long> {

	Integer findUnusedPropertyIndex();

	List<MemberAttribute> findList();

}