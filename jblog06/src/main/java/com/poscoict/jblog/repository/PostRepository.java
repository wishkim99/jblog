package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

		public List<PostVo> findByCategoryNo(Long category_no) {
			return sqlSession.selectList("post.findByCategoryNo", category_no);
		}

		public PostVo findByPostNoAndCategoryNo(Long post_no, Long category_no) {
			Map<String, Long> map = new HashMap<>();
			map.put("post_no", post_no);
			map.put("category_no", category_no);
			return sqlSession.selectOne("post.findByPostNoAndCategoryNo", map);
		}

		public PostVo findLastPost(Long category_no) {
			return sqlSession.selectOne("post.findLastPost", category_no);
		}

		public List<PostVo> findCategoryList(Long category_no) {
			return sqlSession.selectList("post.findCategoryList", category_no);
		}
}
