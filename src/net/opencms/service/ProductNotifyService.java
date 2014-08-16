
package net.opencms.service;

import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Member;
import net.opencms.entity.Product;
import net.opencms.entity.ProductNotify;

public interface ProductNotifyService extends BaseService<ProductNotify, Long> {

	boolean exists(Product product, String email);

	Page<ProductNotify> findPage(Member member, Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent, Pageable pageable);

	Long count(Member member, Boolean isMarketable, Boolean isOutOfStock, Boolean hasSent);

	int send(Long[] ids);

}