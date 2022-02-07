package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.PostVo;


@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
		public boolean insert(PostVo vo) {
		int count = sqlSession.insert("post.insert", vo);
		return count == 1;
	}
}
