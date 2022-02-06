package com.poscoict.jblog.service;

import java.util.List;

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

	public CategoryVo getCategory(String id) {
		return categoryRepository.findById(id);
	}

	public List<CategoryVo> getCategoryList() {
		return categoryRepository.findAllList();
	}


//	public void insert(UserVo userVo) {
//		categoryRepository.insert(userVo);
//	}

	
}
