
package net.opencms.dao;

import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Member;
import net.opencms.entity.Product;
import net.opencms.entity.ProductNotify;

public interface ProductNotifyDao extends BaseDao<ProductNotify, Long> {

	boolean exists(Product product, String email);

	Page<ProductNotify> findPage(Member member, Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent, Pageable pageable);

	Long count(Member member, Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent);

}