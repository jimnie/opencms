
package net.opencms.dao;

import java.util.List;

import net.opencms.entity.ProductCategory;

public interface ProductCategoryDao extends BaseDao<ProductCategory, Long> {

	List<ProductCategory> findRoots(Integer count);

	List<ProductCategory> findParents(ProductCategory productCategory, Integer count);

	List<ProductCategory> findChildren(ProductCategory productCategory, Integer count);

}