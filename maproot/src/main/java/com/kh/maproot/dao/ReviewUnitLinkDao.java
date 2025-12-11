package com.kh.maproot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.maproot.dto.ReviewDto;
import com.kh.maproot.dto.ReviewUnitLinkDto;

@Repository
public class ReviewUnitLinkDao {

	@Autowired
	private SqlSession sqlSession;
	
	
	public boolean deleteBySelectUnit(int scheduleUnitNo) {
		return sqlSession.delete("reviewUnitLink.delete", scheduleUnitNo) > 0;
	};
	
	public void insert(int reviewNo ,List<Integer> scheduleUnitList) {
		

		for(Integer scheduleUnitNo : scheduleUnitList) {
			ReviewUnitLinkDto reviewUnitLinkDto=
				ReviewUnitLinkDto.builder()
				.linkNo( sqlSession.selectOne("reviewUnitLink.sequence"))
				.reviewNo(reviewNo)
				.scheduleUnitNo(scheduleUnitNo)
				.build();
				
		 sqlSession.insert("reviewUnitLink.insert",reviewUnitLinkDto);
			
		}
	}

	public List<ReviewUnitLinkDto> selectByReviewNo( int reviewNo) {
		return sqlSession.selectList("reviewUnitLink.select", reviewNo);
	}
	
}
