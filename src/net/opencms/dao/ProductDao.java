
package net.opencms.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Attribute;
import net.opencms.entity.Brand;
import net.opencms.entity.Goods;
import net.opencms.entity.Member;
import net.opencms.entity.Product;
import net.opencms.entity.Product.OrderType;
import net.opencms.entity.ProductCategory;
import net.opencms.entity.Promotion;
import net.opencms.entity.Tag;

public interface ProductDao extends BaseDao<Product, Long> {

	boolean snExists(String sn);

	Product findBySn(String sn);

	List<Product> search(String keyword, Boolean isGift, Integer count);

	List<Product> findList(ProductCategory productCategory, Brand brand, Promotion promotion, List<Tag> tags, Map<Attribute, String> attributeValue, BigDecimal startPrice, BigDecimal endPrice, Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isGift, Boolean isOutOfStock, Boolean isStockAlert, OrderType orderType, Integer count, List<Filter> filters, List<Order> orders);

	List<Product> findList(ProductCategory productCategory, Date beginDate, Date endDate, Integer first, Integer count);

	List<Product> findList(Goods goods, Set<Product> excludes);

	List<Object[]> findSalesList(Date beginDate, Date endDate, Integer count);

	Page<Product> findPage(ProductCategory productCategory, Brand brand, Promotion promotion, List<Tag> tags, Map<Attribute, String> attributeValue, BigDecimal startPrice, BigDecimal endPrice, Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isGift, Boolean isOutOfStock, Boolean isStockAlert, OrderType orderType, Pageable pageable);

	Page<Product> findPage(Member member, Pageable pageable);

	Long count(Member favoriteMember, Boolean isMarketable, Boolean isList, Boolean isTop, Boolean isGift, Boolean isOutOfStock, Boolean isStockAlert);

	boolean isPurchased(Member member, Product product);

}