
package net.opencms.dao.impl;

import net.opencms.dao.AdDao;
import net.opencms.entity.Ad;

import org.springframework.stereotype.Repository;

@Repository("adDaoImpl")
public class AdDaoImpl extends BaseDaoImpl<Ad, Long> implements AdDao {

}