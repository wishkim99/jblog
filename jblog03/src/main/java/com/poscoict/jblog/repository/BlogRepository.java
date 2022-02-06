package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	

	public BlogVo findById(String id) {
		return sqlSession.selectOne("blog.findById", id);
	}

	//BlogService의 16번째 코드(blogRepository.insert(id)로 불려짐
	public boolean insert(String id) { //user의 id를 받아옴
		Map<String, String> map = new HashMap<>();
		map.put("user_id", id);
		map.put("logo", "/images/original.jpg");
		map.put("title",id+"의 블로그");
		return 1==sqlSession.insert("blog.insert", map);
	}


	public BlogVo findAll() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("blog.findAll");
	}


	public boolean update(BlogVo blogVo) {
		int count = sqlSession.update("blog.update", blogVo);
		return count == 1;
	}	
}