package com.kh.maproot.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.maproot.dto.ChatDto;
import com.kh.maproot.error.TargetNotfoundException;

@Repository
public class ChatDao {
	@Autowired
	private SqlSession sqlSession;
	
	public ChatDto insert(ChatDto chatDto) {
//		int sequence = sqlSession.selectOne("chat.sequence");
//		chatDto.setChatNo(sequence);
//		sqlSession.insert("chat.insert", chatDto);
//		return chatDto;
		sqlSession.insert("chat.insert", chatDto);
		return chatDto;
	}
	
	public List<ChatDto> selectAllList() {
		return sqlSession.selectList("chat.selectAlllist");
	}
	public List<ChatDto> selectCounselorList(String partyAccount) {
		Map<String,Object> params = new HashMap<>();
		params.put("partyAccount", partyAccount);
		return sqlSession.selectList("chat.selectCounselorList", params);
	}
	
	public ChatDto selectOne(long chatNo) {
		ChatDto chatDto = sqlSession.selectOne("chat.detail", chatNo);
		if(chatDto == null) throw new TargetNotfoundException();
		return chatDto;
	}
	
	public boolean changeStatus(ChatDto chatDto) {
		int result = sqlSession.update("chat.changeStatus", chatDto);
		return result > 0;
	}
	
	public void enter(long chatNo, String accountId) {
		Map<String, Object> params = new HashMap<>();
		params.put("chatNo", chatNo);
		params.put("partyAccount", accountId);
		sqlSession.insert("chat.enter", params);
	}
	
	public boolean leave(long chatNo, String accountId) {
		Map<String, Object> params = new HashMap<>();
		params.put("chatNo", chatNo);
		params.put("partyAccount", accountId);
	
		return sqlSession.delete("chat.leave", params) > 0;
	}
	
	public boolean check(long chatNo, String accountId) {
		Map<String, Object> params = new HashMap<>();
		params.put("chatNo", chatNo);
		params.put("partyAccount", accountId);
		int count = sqlSession.selectOne("chat.check", params);
		return count > 0;
	}
}
