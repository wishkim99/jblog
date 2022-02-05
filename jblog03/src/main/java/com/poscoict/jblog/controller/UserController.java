package com.poscoict.jblog.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.UserService;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(UserVo vo) {
		return "user/join";
	}

	@RequestMapping(value="/join", method=RequestMethod.POST)
	//@Valid UserVo userVo에서 가운데 UserVo에 상관있음(객체 타입의 클래스 이름의 맨 앞을 소문자로만 해서 쓰면 됨)
	public String join( UserVo vo, BindingResult result, Model model) { //Bind: 넘어온 데이터를 셋팅, join.jsp안에서 사용
		if(result.hasErrors()) {//여기 안 들어오면 밑으로 넘어가게 하면 안됨
			model.addAllAttributes(result.getModel());
			return "user/join";
		}
		userService.join(vo);
		String id=vo.getId();
		blogService.join(id);
		categoryService.join(id);
		return "redirect:/user/joinsuccess";
	}

	@RequestMapping(value = "/joinsuccess")
	public String joinsuccess() { // join.jsp안에서 사용
		return "/user/joinsuccess";
	}

	@RequestMapping(value="/login")
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) { 
		session.removeAttribute("authUser");
		session.invalidate(); //sessionId를 새걸로 바꿔줌
		return "redirect:/";
	}
	
//	@Auth
//	@RequestMapping(value="/update", method=RequestMethod.GET)
//	public String update(@AuthUser UserVo authUser, Model model) {
//		Long userNo = authUser.getNo();
//		UserVo userVo = userService.getUser(userNo);
//		model.addAttribute("userVo", userVo);
//		
//		return "user/update";
//	}
//	
//	@Auth
//	@RequestMapping(value="/update", method=RequestMethod.POST)
//	public String update(@AuthUser UserVo authUser, UserVo userVo) {
//		userVo.setNo(authUser.getNo());
//		userService.updateUser(userVo);
//		
//		return "redirect:/user/update";
//	}

	
	
}