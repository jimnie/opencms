
package net.opencms.service;

import java.util.List;

import net.opencms.entity.ProductCategory;

public interface ProductCategoryService extends BaseService<ProductCategory, Long> {

	List<ProductCategory> findRoots();

	List<ProductCategory> findRoots(Integer count);

	List<ProductCategory> findRoots(Integer count, String cacheRegion);

	List<ProductCategory> findParents(ProductCategory productCategory);

	List<ProductCategory> findParents(ProductCategory productCategory, Integer count);

	List<ProductCategory> findParents(ProductCategory productCategory, Integer count, String cacheRegion);

	List<ProductCategory> findTree();

	List<ProductCategory> findChildren(ProductCategory productCategory);

	List<ProductCategory> findChildren(ProductCategory productCategory, Integer count);

	List<ProductCategory> findChildren(ProductCategory productCategory, Integer count, String cacheRegion);

}