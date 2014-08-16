
package net.opencms.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.opencms.entity.ProductCategory;
import net.opencms.service.ProductCategoryService;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("productCategoryRootListDirective")
public class ProductCategoryRootListDirective extends BaseDirective {

	private static final String VARIABLE_NAME = "productCategories";

	@Resource(name = "productCategoryServiceImpl")
	private ProductCategoryService productCategoryService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<ProductCategory> productCategories;
		boolean useCache = useCache(env, params);
		String cacheRegion = getCacheRegion(env, params);
		Integer count = getCount(params);
		if (useCache) {
			productCategories = productCategoryService.findRoots(count, cacheRegion);
		} else {
			productCategories = productCategoryService.findRoots(count);
		}
		setLocalVariable(VARIABLE_NAME, productCategories, env, body);
	}

}