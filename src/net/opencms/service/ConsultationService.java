
package net.opencms.service;

import java.util.List;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.Page;
import net.opencms.Pageable;
import net.opencms.entity.Consultation;
import net.opencms.entity.Member;
import net.opencms.entity.Product;

public interface ConsultationService extends BaseService<Consultation, Long> {

	List<Consultation> findList(Member member, Product product, Boolean isShow, Integer count, List<Filter> filters, List<Order> orders);

	List<Consultation> findList(Member member, Product product, Boolean isShow, Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

	Page<Consultation> findPage(Member member, Product product, Boolean isShow, Pageable pageable);

	Long count(Member member, Product product, Boolean isShow);

	void reply(Consultation consultation, Consultation replyConsultation);

}