package com.kh.maproot.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.maproot.dto.ReviewDto;

@Repository
public class ReviewDao {

	@Autowired
	private SqlSession sqlSession;
	
	//댓글 등록(일정 전)
	public int insert(ReviewDto reviewDto) {
		int sequence = sqlSession.selectOne("review.sequence");
		reviewDto.setReviewNo(sequence);
		sqlSession.insert("review.insert", reviewDto);
		return sequence;
	}
	
	//댓글 삭제
	public void delete(int reviewNo) {
		sqlSession.delete("review.delete",reviewNo);
	}

	public boolean update(ReviewDto reviewDto) {
		return sqlSession.update("review.update", reviewDto) > 0;
	}
}
