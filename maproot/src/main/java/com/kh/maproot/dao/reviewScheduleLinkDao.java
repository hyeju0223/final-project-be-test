package com.kh.maproot.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.maproot.schedule.vo.ReviewScheduleLinkVO;

@Repository
public class reviewScheduleLinkDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<ReviewScheduleLinkVO> selectByScheduleNo(int scheduleNo) {
		return sqlSession.selectList("reviewScheduleLink.select", scheduleNo);
		
		
	}
}
