
package net.opencms.dao;

import java.util.List;

import net.opencms.entity.Tag;
import net.opencms.entity.Tag.Type;

public interface TagDao extends BaseDao<Tag, Long> {

	List<Tag> findList(Type type);

}