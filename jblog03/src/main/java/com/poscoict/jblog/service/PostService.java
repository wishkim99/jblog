package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.PostVo;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public boolean addPost(PostVo postVo) {
		return postRepository.insert(postVo);
		
	}

	public List<PostVo> getPostList(String id) {
		return postRepository.findByCategoryNo(id);
	}

}
