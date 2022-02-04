package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.repository.UserRepository;
import com.poscoict.jblog.vo.UserVo;


@Service
public class UserService {//userService 안에서 레퍼지토리 사용할 것
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	public void join(UserVo userVo) {
		userRepository.insert(userVo);
//		blogService.insert(userVo);
//		categoryService.insert(userVo);
	}
	
	public void insertBlog(UserVo userVo, String logo, String title) {
		userRepository.insertBlog(userVo, logo, title);
		
	}
	public UserVo getUser(String id, String password) {
		// TODO Auto-generated method stub
		return userRepository.findByIdAndPassword(id, password);
	}


	public UserVo getUser(String id) { //userRepository의 findById를 받아옴
		// TODO Auto-generated method stub
		return userRepository.findById(id);
	}


	public void updateUser(UserVo userVo) {
		// TODO Auto-generated method stub
		userRepository.update(userVo);
	}
}
