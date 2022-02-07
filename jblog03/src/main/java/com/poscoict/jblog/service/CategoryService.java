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

	public List<CategoryVo> getCategory(String id) {
		return categoryRepository.findById(id);
	}

	public List<CategoryVo> getCategoryList(String id) {
		return categoryRepository.findById(id);
	}

	public int deleteCategory(Long no) {
		return categoryRepository.deleteCategory(no);
		
	}

	public void add(CategoryVo vo) {
		categoryRepository.add(vo);
		
	}


//	public CategoryVo getCntCategory(String name,String id) {
//		return categoryRepository.cntCategory(name, id);
//	}

	
}
