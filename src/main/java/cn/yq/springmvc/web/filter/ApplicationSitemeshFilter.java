package cn.yq.springmvc.web.filter;

import javax.servlet.annotation.WebFilter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
@WebFilter(value="/*",filterName="sitemesh")
public class ApplicationSitemeshFilter extends ConfigurableSiteMeshFilter{

	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		//注册sitemesh自定义标签
		builder.addTagRuleBundle(new SitemeshCssTag())
		 .addTagRuleBundle(new SitemeshJsTag())
		 .addDecoratorPath("/*", "/WEB-INF/views/layout/application.jsp")
		 .addExcludedPath("/sign*");
	}
	

}
