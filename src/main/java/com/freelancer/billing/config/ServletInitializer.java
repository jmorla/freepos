package com.freelancer.billing.config;

import com.freelancer.billing.BillingApplication;
import com.sun.faces.config.ConfigureListener;
import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;

import java.util.EnumSet;

@Configuration
public class ServletInitializer extends SpringBootServletInitializer implements ServletContextAware{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BillingApplication.class);
	}

	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		ServletRegistrationBean servletRegistrationBean =
				new ServletRegistrationBean(new FacesServlet(), "*.xhtml");
		servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
	}

	@Bean
	public FilterRegistrationBean rewriteFilter() {
		FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
		rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST,
				DispatcherType.ASYNC, DispatcherType.ERROR));
		rwFilter.addUrlPatterns("/*");
		return rwFilter;
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
	}

}
