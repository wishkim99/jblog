package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.poscoict.jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean update(UserVo vo) {
		int count = sqlSession.update("user.update", vo);
		return count == 1;
	}
	
	public boolean insert(UserVo vo) {
		int count = sqlSession.insert("user.insert", vo);
		return count == 1;
	}	

	public UserVo findById(String id) {
		return sqlSession.selectOne("user.findById", id);
	}

	public UserVo findByIdAndPassword(String id, String password){ //aop는 스탑워치 적용할 경우 코드 변경 안해도 됨! 그러나 지금은 함

		Map<String, String> map = new HashMap<>();
		map.put("i", id);
		map.put("p", password);
		
//		UserVo vo=sqlSession.selectOne("user.findByEmailAndPassword", map);
//		
//		return vo;
	return sqlSession.selectOne("user.findByIdAndPassword", map);
	}
	
	public boolean insertBlog(UserVo userVo, String logo, String title) {
		Map<String, String> map = new HashMap<>();
		map.put("id", userVo.getId());
		map.put("logo", logo);
		map.put("title", title);
		return 1==sqlSession.insert("user.insertblog", map);
	}
	
}