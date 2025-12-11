package com.kh.maproot.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.maproot.dto.ScheduleTagDto;

@Repository
public class ScheduleTagDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(ScheduleTagDto scheduleTagDto) {
		sqlSession.insert("scheduleTag.insert",scheduleTagDto );
	}
}
