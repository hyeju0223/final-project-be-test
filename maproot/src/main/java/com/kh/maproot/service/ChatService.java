package com.kh.maproot.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.maproot.dao.ChatDao;
import com.kh.maproot.dao.MessageDao;
import com.kh.maproot.dto.ChatDto;
import com.kh.maproot.dto.MessageDto;
import com.kh.maproot.vo.MemberRequestVO;
import com.kh.maproot.vo.TokenVO;

@Service
public class ChatService {
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private ChatDao chatDao;
	
	@Transactional
	public void sendWaiting(long chatNo) {
		MessageDto messageDto = messageDao.insert(MessageDto.builder()
				.messageType("system")
				.messageContent("상담사와 연결하는 중 입니다")
				.messageChat(chatNo)
			.build()
		);
		
		simpMessagingTemplate.convertAndSend(
			"/public/message/" + chatNo + "/system", messageDto
		);
	}
	
//	@Transactional
//	public void sendAgentAssigned(long chatNo, String accountId) {
//		MessageDto messageDto = messageDao.insert(MessageDto.builder()
//				.messageType("system")
//				.messageContent("상담사와 연결되었습니다")
//				.messageChat(chatNo)
//			.build()
//		);
//		
//		simpMessagingTemplate.convertAndSend(
//			"/public/message/" + chatNo + "/system", messageDto
//		);
//	}
	@Transactional
	public void sendAgentAssigned(ChatDto chatDto, String accountId) {
		//1. DB 상태  업데이트(Active) 
		chatDto.setChatId(accountId);
		chatDto.setChatLevel("상담사");
		chatDto.setChatStatus("ACTIVE");
		
		chatDao.changeStatus(chatDto);
		
		// 상담사도 참여자 명단에 추가
		chatDao.enter(chatDto.getChatNo(), accountId);
		
		//2. 기존 메세지 전송 
		long chatNo = chatDto.getChatNo();
		
		MessageDto messageDto = MessageDto.builder()
					.messageType("system")
					.messageContent("상담사와 연결되었습니다")
					.messageChat(chatNo)
				.build();
		
		//DB에 저장
		messageDao.insert(messageDto);
		
		simpMessagingTemplate.convertAndSend("/public/message/" + chatNo + "/system", messageDto);
	}
	
	@Transactional
	public void sendChatEnd(long chatNo) {
		MessageDto messageDto = messageDao.insert(MessageDto.builder()
				.messageType("system")
				.messageContent("상담사와의 연결이 종료되었습니다.")
				.messageChat(chatNo)
			.build()
		);
		
		simpMessagingTemplate.convertAndSend(
			"/public/message/" + chatNo + "/system", messageDto
		);
	}
	
	@Transactional
	public void sendChat(long chatNo, MemberRequestVO requestVO, TokenVO tokenVO) {
		MessageDto messageDto = messageDao.insert(
			MessageDto.builder()
				.messageChat(chatNo)
				.messageType("TALK")//프론트엔드에서 보내는 messageType 불일치 (chat--->TALK 수정)
				.messageContent(requestVO.getContent())
				.messageSender(tokenVO.getLoginId())
				.messageTime(LocalDateTime.now()) //시간 추가
			.build()
		);
		simpMessagingTemplate.convertAndSend(
			    "/public/message/" + chatNo, messageDto 
			);
	}
	
	@Transactional
	public void sendWarning(long chatNo, MemberRequestVO requestVO, TokenVO tokenVO) {
		MessageDto messageDto = messageDao.insert(MessageDto.builder()
					.messageChat(chatNo)
					.messageType("warning")
					.messageContent("욕설은 사용하실 수 없습니다")
				.build());
		
		simpMessagingTemplate.convertAndSend(
				"/private/message/"+chatNo+"/warning/"+tokenVO.getLoginId(), messageDto
		);
	}
}
