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

	public List<PostVo> getPostList(Long category_no) {
		return postRepository.findByCategoryNo(category_no);
	}

	public PostVo getPostContents(Long post_no, Long category_no) {
		// TODO Auto-generated method stub
		return postRepository.findByPostNoAndCategoryNo(post_no, category_no);
	}

	public PostVo getLastPost(Long category_no) {
		// TODO Auto-generated method stub
		return postRepository.findLastPost(category_no);
	}

	public List<PostVo> getCategoryList(Long category_no) {
		return postRepository.findCategoryList(category_no);
	}

}
