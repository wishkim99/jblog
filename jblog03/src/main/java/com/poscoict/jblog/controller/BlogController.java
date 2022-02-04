package com.poscoict.jblog.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/{user_id}")
public class BlogController {
//
//	@Autowired
//	BlogService blogService;
	
	@RequestMapping("")
	//jsp에서 여기로 보내서 kwd 실행-> 받아서 BoardService로 이동
	public String index(@RequestParam(value = "user_id", required = true, defaultValue = "") String userId, Model model) {
//		List<BlogVo> list = blogService.getMessageList(kwd);
//		model.addAttribute("list", list);
		System.out.println("~!~!~!~!~!~!~!~!~!~!~!~!~!~!~!~!~!");
		return "blog/blog-main";
	}

//	//글쓰기
//	@Auth
//	@RequestMapping(value="/writeform", method=RequestMethod.GET) //먼저 form으로 가야함
//	public String writeform() {
//		return "board/write";
//	}
//	@Auth
//	@RequestMapping(value="/write", method=RequestMethod.POST) //가서 write수행
//	public String add(BoardVo vo) {
//		boardService.addContents(vo);
//		return "redirect:/board";
//
//	}
//	
//	//글 수정
//	@Auth
//	@RequestMapping(value="/modifyform/{no}", method=RequestMethod.GET) //먼저 form으로 가야함
//	public String modifyform(@PathVariable("no") Long no, Model model) {
//		BoardVo boardVo = (BoardVo) boardService.getContents(no); //수정해야할 제목, 글 받아와야함
//		System.out.println(boardVo);
//		model.addAttribute("boardVo", boardVo);
//		return "board/modify";
//	} 
//	
//	@Auth
//	@RequestMapping(value="/modify", method=RequestMethod.POST) //가서 modify 수행
//	public String modify(BoardVo vo) {
//		boardService.updateContents(vo);
//		System.out.println(vo);
//		return "redirect:/board";
//	}
//	
//	//글 보기
//	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
//	public String view(@PathVariable("no") Long no, Model model) {
//		BoardVo boardVo = (BoardVo) boardService.getContents(no);
//		model.addAttribute("boardVo", boardVo);
//		
//		return "board/view";
//	}
//
//	//글 삭제
//	@RequestMapping(value="/delete", method=RequestMethod.GET)
//	public String delete(BoardVo vo) {
//		boardService.deleteContents(vo);
//	//	model.addAttribute("no", no);
//		return "redirect:/board";
//	}

}