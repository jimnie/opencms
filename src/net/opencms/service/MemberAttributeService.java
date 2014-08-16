
package net.opencms.service;

import java.util.List;

import net.opencms.entity.MemberAttribute;

public interface MemberAttributeService extends BaseService<MemberAttribute, Long> {

	Integer findUnusedPropertyIndex();

	List<MemberAttribute> findList();

	List<MemberAttribute> findList(String cacheRegion);

}