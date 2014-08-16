
package net.opencms.service;

import java.io.Serializable;
import java.util.List;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.Page;
import net.opencms.Pageable;

public interface BaseService<T, ID extends Serializable> {

	T find(ID id);

	List<T> findAll();

	List<T> findList(ID... ids);

	List<T> findList(Integer count, List<Filter> filters, List<Order> orders);

	List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders);

	Page<T> findPage(Pageable pageable);

	long count();

	long count(Filter... filters);

	boolean exists(ID id);

	boolean exists(Filter... filters);

	void save(T entity);

	T update(T entity);

	T update(T entity, String... ignoreProperties);

	void delete(ID id);

	void delete(ID... ids);

	void delete(T entity);

}