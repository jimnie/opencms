
package net.opencms.dao;

import net.opencms.entity.Cart;

public interface CartDao extends BaseDao<Cart, Long> {

	void evictExpired();

}