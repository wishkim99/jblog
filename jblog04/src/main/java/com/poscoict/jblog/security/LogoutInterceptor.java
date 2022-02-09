package com.poscoict.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LogoutInterceptor extends HandlerInterceptorAdapter {

	@Override
	// post를 처리
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session != null) {
			session.removeAttribute("authUser");
			session.invalidate(); // 세션을 없애고 세션에 속해있는 값들을 모두 없앤다
		}

		response.sendRedirect(request.getContextPath());
		return false;
	}
}
