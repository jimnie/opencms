
package net.opencms.service;

import net.opencms.entity.Article;
import net.opencms.entity.Product;

import java.util.Map;

public interface StaticService {

    int build(String templatePath, String staticPath, Map<String, Object> model);

    int build(String templatePath, String staticPath);

    int build(Article article);

    int build(Product product);

    int buildIndex();

    int buildOther();

    int buildAll();

    int delete(String staticPath);

    int delete(Article article);

    int delete(Product product);

    int deleteIndex();

    int deleteOther();

}