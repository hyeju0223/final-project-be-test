package com.kh.maproot.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.kh.maproot.configuration.JwtProperties;
import com.kh.maproot.vo.ChatTokenRefreshVO;
import com.kh.maproot.vo.MemberRequestVO;
import com.kh.maproot.vo.MemberResponseVO;
import com.kh.maproot.vo.SystemMessageVO;
import com.kh.maproot.vo.TokenVO;

@Controller
public class MessageService {
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private JwtProperties jwtProperties;
	
	@MessageMapping("/member")
	public void member(Message<MemberRequestVO> message) {
		StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
		String accessToken = accessor.getFirstNativeHeader("accessToken");
		String refreshToken = accessor.getFirstNativeHeader("refreshToken");
		if(accessToken == null || refreshToken == null) return;
		
		TokenVO tokenVO;
	    try {
	       tokenVO = tokenService.parse(accessToken);
	       
	       long ms = tokenService.getRemain(accessToken);
	       
	       if(ms >= jwtProperties.getRenewalLimit() * 60L * 1000L) {
	    	   simpMessagingTemplate.convertAndSend(
	    			   "/private/member/token/" + tokenVO.getLoginId(), 
	    			   ChatTokenRefreshVO.builder()
						.accessToken(tokenService.generateAccessToken(tokenVO))
						.refreshToken(tokenService.generateRefreshToken(tokenVO))
						.build()
	    		);
	       }
	       MemberRequestVO requestVO = message.getPayload();
	       
	       String regex = "(.*?)(씨발|시발|병신|존나|개새끼|미친놈)(.*?)";
	       if(requestVO.getContent().matches(regex)) {
	    	   simpMessagingTemplate.convertAndSend(
	    			   "/private/member/warning/" + tokenVO.getLoginId(),
	    			   SystemMessageVO.builder()
	    			   .type("warning")
	    			   .content("부적절한 언어 사용이 감지되었습니다.")
	    			   .time(LocalDateTime.now())
	    			   .build()
	    		);
	    	   return;
	       }
	       simpMessagingTemplate.convertAndSend(
	   			"/public/member",
	   			MemberResponseVO.builder()
	   				.loginId(tokenVO.getLoginId())
	   				.content(requestVO.getContent())
	   				.time(LocalDateTime.now())
	   				.build()
	   		);
	    }
	    catch (Exception e) {
			
		}
	}
}
