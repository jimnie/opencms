
package net.opencms.service;

import java.math.BigDecimal;

import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Article;
import net.opencms.entity.Product;
import net.opencms.entity.Product.OrderType;

public interface SearchService {

	void index();

	void index(Class<?> type);

	void index(Article article);

	void index(Product product);

	void purge();

	void purge(Class<?> type);

	void purge(Article article);

	void purge(Product product);

	Page<Article> search(String keyword, Pageable pageable);

	Page<Product> search(String keyword, BigDecimal startPrice, BigDecimal endPrice, OrderType orderType, Pageable pageable);

}