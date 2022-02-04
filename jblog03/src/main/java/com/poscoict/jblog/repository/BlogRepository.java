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
	
	
	public boolean insert(BlogVo blogVo) {
		int count = sqlSession.insert("blog.insert", blogVo);
		return count == 1;
	}	

	public BlogVo findById(String id) {
		return sqlSession.selectOne("blog.findById", id);
	}


	public boolean insert(UserVo userVo) {
		int count = sqlSession.insert("user.insert", userVo);
		return count == 1;
	}	

	
}