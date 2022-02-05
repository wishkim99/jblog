package com.poscoict.jblog.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;

@Controller
@RequestMapping("/{user_id}")
public class BlogController {
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("")
	//jsp에서 여기로 보내서 kwd 실행-> 받아서 BoardService로 이동
	public String index( @RequestParam(value = "user_id", required = true, defaultValue = "") String userId, Model model) {
		//ServletContext 객체는 모든 서블릿이 공유하는 객체
		BlogVo blogVo=blogService.getBlog();
		model.addAttribute("blogVo", blogVo);
		System.out.println("================================"+blogVo);
		return "blog/blog-main";
	}

	@RequestMapping(value="/admin/basic")
	//jsp에서 여기로 보내서 kwd 실행-> 받아서 BoardService로 이동
		public String basic( @RequestParam(value = "user_id", required = true, defaultValue = "") String userId, Model model) {
//			List<BlogVo> list = blogService.getMessageList(kwd);
//			model.addAttribute("list", list);
			//ServletContext 객체는 모든 서블릿이 공유하는 객체
			BlogVo blogVo=blogService.getBlog();
			model.addAttribute("blogVo", blogVo);
			
			CategoryVo categoryVo=categoryService.getCategory();
			model.addAttribute("categoryVo", categoryVo);
			return "blog/blog-admin-basic"; 
	}
		
		

}