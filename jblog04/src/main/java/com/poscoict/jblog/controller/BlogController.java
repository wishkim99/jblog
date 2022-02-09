package com.poscoict.jblog.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.poscoict.jblog.security.Auth;
import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.PostService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/{user_id:(?!assets)(?!images).*}")
public class BlogController {
	@Autowired
	private ServletContext servletContext;

	@Autowired
	private BlogService blogService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PostService postService;

	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping("")
	public String index(@PathVariable("user_id") String user_id, Model model, BlogVo vo) {
		// ServletContext 객체는 모든 서블릿이 공유하는 객체
		Long no = categoryService.getCategory(user_id).get(0).getNo(); // post의 category_no, get(0)은 순서대로 출력
		List<PostVo> PostList = postService.getPostList(no);
		model.addAttribute("plist", PostList);

		List<CategoryVo> CategoryList = categoryService.getCategory(user_id);
		model.addAttribute("list", CategoryList);

		BlogVo blogVo = blogService.getBlog(user_id);
		model.addAttribute("blogVo", blogVo);

		PostVo LastPostVo = postService.getLastPost(CategoryList.get(0).getNo());
		model.addAttribute("LastPostVo", LastPostVo);
		model.addAttribute("category_no", no); // post의 category_no=category의 no

		return "blog/blog-main";
	}

	@Auth // id가 있을 경우만 blog/admin 접속
	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	// jsp에서 여기로 보내서 user_id 실행-> 받아서 BlogService로 이동
	public String basic(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id, Model model, BlogVo blogVo) {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (!authUser.getId().equals(blogVo.getUser_id())) {
			return "redirect:/{user_id}";
		}

		blogVo = blogService.getBlog(user_id);
		model.addAttribute("blogVo", blogVo);

		List<CategoryVo> categoryVo = categoryService.getCategory(user_id);
		model.addAttribute("categoryVo", categoryVo);
		return "blog/blog-admin-basic";

	}

	@Auth // id가 있을 경우만 blog/admin 접속
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	// BlogVo가운데 UserVo에 상관있음(객체 타입의 클래스 이름의 맨 앞을 소문자로만 해서 쓰면 됨)
	public String update(HttpServletRequest request, HttpServletResponse response, @ModelAttribute BlogVo blogVo,
			@RequestParam(value = "upload-file") MultipartFile multipartFile) { // join.jsp안에서 사용
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (!authUser.getId().equals(blogVo.getUser_id())) {
			return "redirect:/{user_id}";
		}
		String url = fileUploadService.restore(multipartFile);
		blogVo.setLogo(url);
		if (blogService.update(blogVo)) {
			// ServletContext 객체는 모든 서블릿이 공유하는 객체
			servletContext.setAttribute("blogVo", blogVo);
		}
		return "redirect:/{user_id}/admin/basic";
	}

	@Auth // id가 있을 경우만 blog/admin 접속
	@RequestMapping(value = "/admin/category")
	// jsp에서 여기로 보내서 kwd 실행-> 받아서 BoardService로 이동
	public String category(HttpServletRequest request, HttpServletResponse response, BlogVo blogVo,
			@PathVariable("user_id") String user_id, Model model) {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (!authUser.getId().equals(blogVo.getUser_id())) {
			return "redirect:/{user_id}";
		}
		blogVo = blogService.getBlog(user_id);
		model.addAttribute("blogVo", blogVo); // jsp에서 사용하기 위함

		List<CategoryVo> list = categoryService.getCategoryList(user_id);
		model.addAttribute("list", list);
		return "blog/blog-admin-category";
	}

	// 카테고리 삭제
	@Auth
	@RequestMapping(value = "/category/delete/{no}")
	public String delete(@PathVariable("no") Long no, Model model) {
		// model.addAttribute("no", no); //객체를 넘겨서 JSP에서 사용(이걸 해줘야 삭제됨,,,)
		categoryService.deleteCategory(no);
		return "redirect:/{user_id}/admin/category";
	}

	@Auth
	@RequestMapping(value = "/category/add", method = RequestMethod.POST)
	// @Valid UserVo userVo에서 가운데 UserVo에 상관있음(객체 타입의 클래스 이름의 맨 앞을 소문자로만 해서 쓰면 됨)
	public String add(HttpServletRequest request, HttpServletResponse response, CategoryVo vo, BlogVo blogVo) {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (!authUser.getId().equals(blogVo.getUser_id())) {
			return "redirect:/{user_id}";
		}
		// 블로그아이디 자리 채움
		vo.setBlog_id(blogVo.getUser_id()); // blogVo의 user_id의 값을 못받아왔으므로 null이 되지 않게 받아옴

		categoryService.add(vo);

		return "redirect:/{user_id}/admin/category";
	}

	// 글쓰기
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET) // 먼저 form으로 가야함
	public String add(HttpServletRequest request, HttpServletResponse response, @PathVariable("user_id") String user_id,
			Model model, BlogVo blogVo) {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");

		if (!authUser.getId().equals(blogVo.getUser_id())) {
			return "redirect:/{user_id}";
		}

		blogVo = blogService.getBlog(user_id);
		model.addAttribute("blogVo", blogVo);
		List<CategoryVo> list = categoryService.getCategoryList(user_id);
		model.addAttribute("list", list);
		return "blog/blog-admin-write";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST) // 가서 write수행
	public String add(@PathVariable("user_id") String user_id, PostVo vo, CategoryVo categoryvo, Long categoryNo,
			Model model) {
		BlogVo blogVo = blogService.getBlog(user_id);
		model.addAttribute("blogVo", blogVo);

		List<CategoryVo> list = categoryService.getCategoryList(user_id);
		model.addAttribute("list", list);
		vo.setCategory_no(categoryNo);
		postService.addPost(vo);
		return "redirect:/{user_id}";
	}

	// 카테고리의 글 내용 보기
	@RequestMapping("/{category_no}/{post_no}")
	public String main(@PathVariable("user_id") String user_id, @PathVariable("category_no") Long category_no,
			@PathVariable("post_no") Long post_no, Model model) {
		BlogVo blogVo = blogService.getBlog(user_id);
		model.addAttribute("blogVo", blogVo);

		List<CategoryVo> CategoryList = categoryService.getCategory(user_id);
		model.addAttribute("list", CategoryList);

		List<PostVo> PostList = postService.getPostList(category_no);
		model.addAttribute("plist", PostList);

		PostVo postVo = postService.getPostContents(post_no, category_no);
		model.addAttribute("selectedPostVo", postVo);

		return "blog/blog-main";
	}

	// 카테고리의 글 내용 보기
	@RequestMapping("/{category_no}")
	public String categoryList(@PathVariable("user_id") String user_id, @PathVariable("category_no") Long category_no,
			Model model) {
		BlogVo blogVo = blogService.getBlog(user_id);
		model.addAttribute("blogVo", blogVo);

		List<CategoryVo> CategoryList = categoryService.getCategory(user_id); //오른쪽 화면 카테고리 리스트들
		model.addAttribute("list", CategoryList);

		List<PostVo> PostList = postService.getPostList(category_no); //가운데 화면 카테고리별 글 목록 리스트들
		model.addAttribute("plist", PostList);

		PostVo PostVo = postService.getLastPost(category_no); //카테고리 눌렀을시 해당 글목록 보기
		model.addAttribute("LastPostVo", PostVo);

		return "blog/blog-main";
	}
}