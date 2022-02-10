package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(String id) {
		Map<String, String> map = new HashMap<>();
		map.put("name", "미분류");
		map.put("description", "기본 카테코리");
		map.put("blog_id",id);
		int count = sqlSession.insert("category.insert", map);
		return count == 1;
		
	}

	public List<CategoryVo> findById(String id) { //admin/basic에서 사용
		return sqlSession.selectList("category.findById", id);
	}
	
	public List<CategoryVo> findAllList() { //admin/category에서 사용
		return sqlSession.selectList("category.findAllList");
	}

	public int deleteCategory(Long no) {
		Map<String, Object> map = new HashMap<>(); // object는 string, long다 받음
		map.put("no", no); 
		return sqlSession.delete("category.deleteCategory", map);
	}

	public boolean add(CategoryVo vo) {
		int count = sqlSession.insert("category.insert", vo);
		return count == 1;
	}

	
}
