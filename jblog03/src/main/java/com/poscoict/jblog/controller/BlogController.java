package com.poscoict.jblog.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.security.Auth;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.FileUploadService;
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
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping("")
	//??@RequestParam(value = "user_id", required = true, defaultValue = "")왜 이런식은 에러나지....
	public String index( @PathVariable("user_id") String user_id, Model model, BlogVo vo) {
		//ServletContext 객체는 모든 서블릿이 공유하는 객체
		BlogVo blogVo=blogService.getBlog(user_id);
		model.addAttribute("blogVo", blogVo);
		System.out.println("================================"+blogVo);
		return "blog/blog-main";
	}

	@Auth(id=true) //id가 있을 경우만 blog/admin 접속
	@RequestMapping(value="/admin/basic", method=RequestMethod.GET)
	//jsp에서 여기로 보내서 user_id 실행-> 받아서 BlogService로 이동
		public String basic( @PathVariable("user_id") String user_id, Model model) {
			//ServletContext 객체는 모든 서블릿이 공유하는 객체
			BlogVo blogVo=blogService.getBlog(user_id);

			model.addAttribute("blogVo", blogVo);
			
			CategoryVo categoryVo=categoryService.getCategory(user_id);
			model.addAttribute("categoryVo", categoryVo);
			return "blog/blog-admin-basic"; 
	}
	
	@Auth(id=true) //id가 있을 경우만 blog/admin 접속
	@RequestMapping(value="/admin/basic", method=RequestMethod.POST)
	//BlogVo가운데 UserVo에 상관있음(객체 타입의 클래스 이름의 맨 앞을 소문자로만 해서 쓰면 됨)
	public String update(@ModelAttribute BlogVo vo, 
			@RequestParam(value="upload-file") MultipartFile multipartFile) { //join.jsp안에서 사용
		String url = fileUploadService.restore(multipartFile);
		vo.setLogo(url);
		if(blogService.update(vo)) {
			//ServletContext 객체는 모든 서블릿이 공유하는 객체
			servletContext.setAttribute("blogVo", vo); 
			System.out.println("~!~!~!~!~!~!~!~!~!"+vo.getUser_id());
		}
		return "redirect:/{user_id}/admin/basic";
	}
	
	@Auth(id=true) //id가 있을 경우만 blog/admin 접속
	@RequestMapping(value="/admin/category")
	//jsp에서 여기로 보내서 kwd 실행-> 받아서 BoardService로 이동
	public String category(@PathVariable("user_id") String user_id, Model model) {
		//ServletContext 객체는 모든 서블릿이 공유하는 객체
		List<CategoryVo> list = categoryService.getCategoryList();
		model.addAttribute("list", list);
		return "blog/blog-admin-category";
	}	

}