
package net.opencms.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.entity.Brand;
import net.opencms.entity.Consultation;
import net.opencms.entity.Member;
import net.opencms.entity.Product;
import net.opencms.service.ConsultationService;
import net.opencms.service.MemberService;
import net.opencms.service.ProductService;
import net.opencms.util.FreemarkerUtils;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("consultationListDirective")
public class ConsultationListDirective extends BaseDirective {

	private static final String MEMBER_ID_PARAMETER_NAME = "memberId";

	private static final String PRODUCT_ID_PARAMETER_NAME = "productId";

	private static final String VARIABLE_NAME = "consultations";

	@Resource(name = "consultationServiceImpl")
	private ConsultationService consultationService;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "productServiceImpl")
	private ProductService productService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Long memberId = FreemarkerUtils.getParameter(MEMBER_ID_PARAMETER_NAME, Long.class, params);
		Long productId = FreemarkerUtils.getParameter(PRODUCT_ID_PARAMETER_NAME, Long.class, params);

		Member member = memberService.find(memberId);
		Product product = productService.find(productId);

		List<Consultation> consultations;
		boolean useCache = useCache(env, params);
		String cacheRegion = getCacheRegion(env, params);
		Integer count = getCount(params);
		List<Filter> filters = getFilters(params, Brand.class);
		List<Order> orders = getOrders(params);
		if ((memberId != null && member == null) || (productId != null && product == null)) {
			consultations = new ArrayList<Consultation>();
		} else {
			if (useCache) {
				consultations = consultationService.findList(member, product, true, count, filters, orders, cacheRegion);
			} else {
				consultations = consultationService.findList(member, product, true, count, filters, orders);
			}
		}
		setLocalVariable(VARIABLE_NAME, consultations, env, body);
	}

}