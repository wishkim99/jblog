package com.poscoict.jblog.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.BlogVo;

public class BlogInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception { // 컨트롤러가 수행되기 전에 접근
		ServletContext sc = request.getServletContext();
		BlogVo blogVo = (BlogVo) sc.getAttribute("blogVo"); // blogVo이름의 객체를 불러옴

		// String userId=blogVo.getUser_id();
		if(blogVo!=null) { //객체에 저장된 값이 없으므로 받아와야함
			System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + blogVo.getUser_id());
			blogVo = blogService.getBlog(blogVo.getUser_id()); // 비어있을때 db에서 값을 받아옴(필드값 다 넣어줌)
			
			sc.setAttribute("blogVo", blogVo); // blogVo 객체 셋팅
		} //여기
		return true;
	}

}
