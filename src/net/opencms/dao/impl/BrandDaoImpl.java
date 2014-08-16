
package net.opencms.dao.impl;

import net.opencms.dao.BrandDao;
import net.opencms.entity.Brand;

import org.springframework.stereotype.Repository;

@Repository("brandDaoImpl")
public class BrandDaoImpl extends BaseDaoImpl<Brand, Long> implements BrandDao {

}