package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;

	public void join(String id) { //회원가입시 blog에도 id값 받아옴
		blogRepository.insert(id);
	}

	public BlogVo getBlog(String id) {
		// TODO Auto-generated method stub
		return blogRepository.findById(id);
	}

	public boolean update(BlogVo blogVo) {
		return blogRepository.update(blogVo);
	}
}