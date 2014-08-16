
package net.opencms.service;

import net.opencms.entity.Cart;
import net.opencms.entity.Member;

public interface CartService extends BaseService<Cart, Long> {

	Cart getCurrent();

	void merge(Member member, Cart cart);

	void evictExpired();

}