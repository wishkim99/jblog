package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.CategoryVo;


@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	public void join(String id) {
		categoryRepository.insert(id);
	}

	public CategoryVo getCategory() {
		return categoryRepository.findAll();
	}

//	public void insert(UserVo userVo) {
//		categoryRepository.insert(userVo);
//	}

	
}
