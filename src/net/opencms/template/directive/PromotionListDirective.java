
package net.opencms.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.entity.Promotion;
import net.opencms.service.PromotionService;
import net.opencms.util.FreemarkerUtils;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("promotionListDirective")
public class PromotionListDirective extends BaseDirective {

	private static final String HAS_BEGUN_PARAMETER_NAME = "hasBegun";

	private static final String HAS_ENDED_PARAMETER_NAME = "hasEnded";

	private static final String VARIABLE_NAME = "promotions";

	@Resource(name = "promotionServiceImpl")
	private PromotionService promotionService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Boolean hasBegun = FreemarkerUtils.getParameter(HAS_BEGUN_PARAMETER_NAME, Boolean.class, params);
		Boolean hasEnded = FreemarkerUtils.getParameter(HAS_ENDED_PARAMETER_NAME, Boolean.class, params);

		List<Promotion> promotions;
		boolean useCache = useCache(env, params);
		String cacheRegion = getCacheRegion(env, params);
		Integer count = getCount(params);
		List<Filter> filters = getFilters(params, Promotion.class);
		List<Order> orders = getOrders(params);
		if (useCache) {
			promotions = promotionService.findList(hasBegun, hasEnded, count, filters, orders, cacheRegion);
		} else {
			promotions = promotionService.findList(hasBegun, hasEnded, count, filters, orders);
		}
		setLocalVariable(VARIABLE_NAME, promotions, env, body);
	}

}