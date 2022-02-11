package com.poscoict.jblog.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poscoict.jblog.interceptor.BlogInterceptor;
import com.poscoict.jblog.security.AuthInterceptor;
import com.poscoict.jblog.security.AuthUserHandlerMethodArgumentResolver;
import com.poscoict.jblog.security.LoginInterceptor;
import com.poscoict.jblog.security.LogoutInterceptor;



@SpringBootConfiguration
@PropertySource("classpath:com/poscoict/jblog/config/WebConfig.properties")
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;
	// Argument Resolver
		@Bean
		public HandlerMethodArgumentResolver handlerMethodArgumentResolver() {
			return new AuthUserHandlerMethodArgumentResolver();
		}
		
		@Override
		public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
			argumentResolvers.add(handlerMethodArgumentResolver());
		}
		
		// Interceptors
		@Bean
		public HandlerInterceptor loginInterceptor() {
			return new LoginInterceptor();
		}

		@Bean
		public HandlerInterceptor logoutInterceptor() {
			return new LogoutInterceptor();
		}

		@Bean
		public HandlerInterceptor authInterceptor() {
			return new AuthInterceptor();
		}

		@Bean
		public HandlerInterceptor blogInterceptor() {
			return new BlogInterceptor();
		}
		
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry
				.addInterceptor(loginInterceptor())
				.addPathPatterns("/user/auth");
			
			registry
				.addInterceptor(logoutInterceptor())
				.addPathPatterns("/user/logout");

			registry
				.addInterceptor(authInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/user/auth")
				.excludePathPatterns("/user/logout")
				.excludePathPatterns("/asset/**");
			
			registry
			.addInterceptor(blogInterceptor()) //siteVo에서 타이틀, 웰컴메시지 등을 가져오기 위해 
			.addPathPatterns("/**");
		}
		
		//Resource Mapping
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler(env.getProperty("fileupload.resourceMapping"))
					.addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation"));
		}
}
