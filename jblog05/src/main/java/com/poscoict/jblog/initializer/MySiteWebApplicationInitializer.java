package com.poscoict.jblog.initializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.poscoict.jblog.config.AppConfig;
import com.poscoict.jblog.config.WebConfig;


public class MySiteWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {AppConfig.class}; //new int[5] {1,2,3,4,5};랑 같은거라 생각
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { //webapplication을 설정하는 클래스(WebConfig.java)
		return new Class<?>[] {WebConfig.class}; //new int[5] {1,2,3,4,5};랑 같은거라 생각
	}
	
	@Override
	protected Filter[] getServletFilters() { //옵션
		return new Filter[] {new CharacterEncodingFilter("utf-8", false)};
	}

//!!!!과제
//	@Override //DefaultServletHandler를 했기 때문에 절대 안먹힘
	//defaultServlet Handler: 메소드 설정 안됐을 시 Default Servlet으로 보냄
//	protected void customizeRegistration(Dynamic registration) {
//		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true"); //noHandler라고 되어있지만 Mvc.configurer에 handler 있음=> 따라 걍 404 에러 나옴 
//	}
	

}
