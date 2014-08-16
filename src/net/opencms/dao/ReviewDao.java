
package net.opencms.dao;

import java.util.List;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Member;
import net.opencms.entity.Product;
import net.opencms.entity.Review;
import net.opencms.entity.Review.Type;

public interface ReviewDao extends BaseDao<Review, Long> {

	List<Review> findList(Member member, Product product, Type type, Boolean isShow, Integer count, List<Filter> filters, List<Order> orders);

	Page<Review> findPage(Member member, Product product, Type type, Boolean isShow, Pageable pageable);

	Long count(Member member, Product product, Type type, Boolean isShow);

	boolean isReviewed(Member member, Product product);

	long calculateTotalScore(Product product);

	long calculateScoreCount(Product product);

}