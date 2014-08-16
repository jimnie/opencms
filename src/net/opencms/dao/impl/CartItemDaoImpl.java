
package net.opencms.dao.impl;

import net.opencms.dao.CartItemDao;
import net.opencms.entity.CartItem;

import org.springframework.stereotype.Repository;

@Repository("cartItemDaoImpl")
public class CartItemDaoImpl extends BaseDaoImpl<CartItem, Long> implements CartItemDao {

}