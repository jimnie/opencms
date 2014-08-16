
package net.opencms.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.opencms.Filter;
import net.opencms.Order;
import net.opencms.entity.Article;
import net.opencms.entity.ArticleCategory;
import net.opencms.entity.Tag;
import net.opencms.service.ArticleCategoryService;
import net.opencms.service.ArticleService;
import net.opencms.service.TagService;
import net.opencms.util.FreemarkerUtils;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("articleListDirective")
public class ArticleListDirective extends BaseDirective {

	private static final String ARTICLE_CATEGORY_ID_PARAMETER_NAME = "articleCategoryId";

	private static final String TAG_IDS_PARAMETER_NAME = "tagIds";

	private static final String VARIABLE_NAME = "articles";

	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	@Resource(name = "tagServiceImpl")
	private TagService tagService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Long articleCategoryId = FreemarkerUtils.getParameter(ARTICLE_CATEGORY_ID_PARAMETER_NAME, Long.class, params);
		Long[] tagIds = FreemarkerUtils.getParameter(TAG_IDS_PARAMETER_NAME, Long[].class, params);

		ArticleCategory articleCategory = articleCategoryService.find(articleCategoryId);
		List<Tag> tags = tagService.findList(tagIds);

		List<Article> articles;
		if ((articleCategoryId != null && articleCategory == null) || (tagIds != null && tags.isEmpty())) {
			articles = new ArrayList<Article>();
		} else {
			boolean useCache = useCache(env, params);
			String cacheRegion = getCacheRegion(env, params);
			Integer count = getCount(params);
			List<Filter> filters = getFilters(params, Article.class);
			List<Order> orders = getOrders(params);
			if (useCache) {
				articles = articleService.findList(articleCategory, tags, count, filters, orders, cacheRegion);
			} else {
				articles = articleService.findList(articleCategory, tags, count, filters, orders);
			}
		}
		setLocalVariable(VARIABLE_NAME, articles, env, body);
	}

}