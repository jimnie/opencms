
package net.opencms.service.impl;

import javax.annotation.Resource;

import net.opencms.dao.CartItemDao;
import net.opencms.entity.CartItem;
import net.opencms.service.CartItemService;

import org.springframework.stereotype.Service;

@Service("cartItemServiceImpl")
public class CartItemServiceImpl extends BaseServiceImpl<CartItem, Long> implements CartItemService {

	@Resource(name = "cartItemDaoImpl")
	public void setBaseDao(CartItemDao cartItemDao) {
		super.setBaseDao(cartItemDao);
	}

}