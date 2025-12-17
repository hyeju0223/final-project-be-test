package com.kh.maproot.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.maproot.configuration.WebSocketProperties;
import com.kh.maproot.dto.MessageDto;

@Repository
public class MessageDao {
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private WebSocketProperties webSocketProperties;
	
	public MessageDto insert(MessageDto messageDto) {
		long sequence = sqlSession.selectOne("message.sequence");
		messageDto.setMessageNo(sequence);
		sqlSession.insert("message.insert", messageDto);
		return sqlSession.selectOne("message.detail", sequence);
	}
	
	public List<MessageDto> selectList(long messageChat) {
		Map<String, Object> params = new HashMap<>();
		params.put("messageChat", messageChat);
		params.put("size", webSocketProperties.getStrSize());
		return sqlSession.selectList("message.list", params);
	}
	
	public List<MessageDto> selectList(long messageChat, long messageNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("messageChat", messageChat);
		params.put("messageNo", messageNo);
		params.put("size", webSocketProperties.getStrSize());
		return sqlSession.selectList("message.list", params);
	}
	
	public boolean checkLast(MessageDto messageDto) {
		int count = sqlSession.selectOne("message.checkLast", messageDto);
		return count == 0;
	}
}
