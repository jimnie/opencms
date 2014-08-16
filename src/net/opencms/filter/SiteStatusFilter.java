package net.opencms.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.opencms.Setting;
import net.opencms.util.SettingUtils;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@Component("siteStatusFilter")
public class SiteStatusFilter extends OncePerRequestFilter {

	private static final String[] DEFAULT_IGNORE_URL_PATTERNS = new String[] { "/admin/**" };

	private static final String DEFAULT_REDIRECT_URL = "/common/site_close.jhtml";

	private static AntPathMatcher antPathMatcher = new AntPathMatcher();

	private String[] ignoreUrlPatterns = DEFAULT_IGNORE_URL_PATTERNS;

	private String redirectUrl = DEFAULT_REDIRECT_URL;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Setting setting = SettingUtils.get();
        // 检查网站是否被设置为启用
		if (setting.getIsSiteEnabled()) {
			filterChain.doFilter(request, response);
		} else {
            // 如果关闭则重定位到站点关闭页面
			String path = request.getServletPath();
			if (path.equals(redirectUrl)) {
				filterChain.doFilter(request, response);
				return;
			}
            // 不对admin路径进行过滤操作
			if (ignoreUrlPatterns != null) {
				for (String ignoreUrlPattern : ignoreUrlPatterns) {
                    // 匹配每次请求路径
					if (antPathMatcher.match(ignoreUrlPattern, path)) {
						filterChain.doFilter(request, response);
						return;
					}
				}
			}
			response.sendRedirect(request.getContextPath() + redirectUrl);
		}
	}

	public String[] getIgnoreUrlPatterns() {
		return ignoreUrlPatterns;
	}

	public void setIgnoreUrlPatterns(String[] ignoreUrlPatterns) {
		this.ignoreUrlPatterns = ignoreUrlPatterns;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}