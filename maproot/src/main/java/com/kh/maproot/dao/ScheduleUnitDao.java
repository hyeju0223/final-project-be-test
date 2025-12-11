package com.kh.maproot.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.maproot.dto.ScheduleUnitDto;

@Repository
public class ScheduleUnitDao {

	@Autowired
	private SqlSession sqlSession;
	
	public List<ScheduleUnitDto> selectByScheduleNo(int scheduleNo) {
		return sqlSession.selectList("scheduleUnit.select", scheduleNo);
		
	}
}
