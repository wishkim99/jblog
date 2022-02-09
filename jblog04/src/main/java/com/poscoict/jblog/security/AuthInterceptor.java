package com.poscoict.jblog.security;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 1. handler 종류 확인
		if (handler instanceof HandlerMethod == false) {
			return true;
		}
		// 2. casting
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. Handler Method의 @Auth 받아오기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

//		//4. Handler Method @Auth가 없다면 Type에 있는지 확인(과제!!!!!!!!)(인증이 필요 없다는 의미)
		if (auth == null) {
			// 한줄이면 됨
			auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
			// Auth라는 클래스이름으로 해당 bean타입을 얻어오겠다

			/* 강사님 코드 */
//			handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}

		// 5. type(4번)과 method(3번)에 @Auth가 적용이 안되어있는 경우
		if (auth == null) {
			return true; // true면 그냥 진행됨
		}

		// 6. @Auth가 적용이 되어있기 때문에 인증(Authentication)여부 확인
		HttpSession session = request.getSession();
		if (session == null) {
			// session이 없으니까 loginform으로 다시 요청
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			// session이 없으니까 loginform으로 다시 요청
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		
//		// 6. 인증 확인!!!->controller의 handler(method)실행
		return true;
	}
}
