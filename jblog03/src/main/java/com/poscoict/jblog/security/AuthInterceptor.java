package com.poscoict.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.jblog.vo.UserVo;


public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1. handler 종류 확인
		if(handler instanceof HandlerMethod ==false) {
			return true;
		}
		//2. casting
		HandlerMethod handlerMethod=(HandlerMethod)handler;
		
		//3. Handler Method의 @Auth 받아오기
		Auth auth=handlerMethod.getMethodAnnotation(Auth.class);
		
//		//4. Handler Method @Auth가 없다면 Type에 있는지 확인(과제!!!!!!!!)(인증이 필요 없다는 의미)
		if(auth==null) {
			//한줄이면 됨
			auth=handlerMethod.getBeanType().getAnnotation(Auth.class);
			//Auth라는 클래스이름으로 해당 bean타입을 얻어오겠다
			
			/*강사님 코드*/
//			handlerMethod.getMethod().getDeclaringClass().getAnnotation(Auth.class);
		}

		//5. type(4번)과 method(3번)에 @Auth가 적용이 안되어있는 경우
		if(auth == null) {
			return true; //true면 그냥 진행됨
		}
		
		//6. @Auth가 적용이 되어있기 때문에 인증(Authentication)여부 확인
		HttpSession session=request.getSession();
		if(session==null) {
			//session이 없으니까 loginform으로 다시 요청
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null) {
			//session이 없으니까 loginform으로 다시 요청
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
		/*강사님 코드
		//7.권한(Authorization) 체크를 위해서 @Auth의 role 가져오기("USER","ADMIN")
		String role=auth.role();
		
		//8. @Auth의 role이 "USER"인 경우, authUser의 role은 상관이 없다.
		if("User".equals(role)) {
			return true;
		}
		
		//9. @Auth의 role이 "ADMIN"인 경우, authUser은 "ADMIN"이어야 한다.
		if("ADMIN".equals(authUser.getRole())==false) {
			response.sendRedirect(request.getContextPath());
			return false;
		}
		
		//10. 옳은 관리자
		//@Auth의 role: "ADMIN"
		//authUser의 role:"ADMIN"
		 return true;
		*/
		
		//6. 인증 확인!!!->controller의 handler(method)실행
		return true;
	}

	
}
