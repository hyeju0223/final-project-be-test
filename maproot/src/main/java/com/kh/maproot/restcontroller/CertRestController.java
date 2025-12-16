package com.kh.maproot.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.maproot.dao.AccountDao;
import com.kh.maproot.dto.CertDto;
import com.kh.maproot.error.TargetAlreadyExistsException;
import com.kh.maproot.error.TargetNotfoundException;
import com.kh.maproot.service.CertService;

import io.swagger.v3.oas.annotations.Operation;

@CrossOrigin
@RestController
@RequestMapping("/cert")
public class CertRestController {
	
	@Autowired
	private CertService certService;
	@Autowired
	private AccountDao accountDao;
	
	@Operation(summary = "휴대폰 인증번호 발송")
	@PostMapping("/sendPhone")
	public void sendPhone(@RequestParam String phone) {
		// 발송 전 중복 검사 방지
		int count = accountDao.countByAccountContact(phone);
		if(count > 0) throw new TargetAlreadyExistsException("이미 가입된 전화번호입니다");
		certService.sendCertPhone(phone);
	}
	
	@Operation(summary = "이메일 인증번호 발송")
	@PostMapping("/sendEmail")
	public void sendEmail(@RequestParam String email) {
		certService.sendCertEmail(email);
	}
	
	@Operation(summary = "휴대폰 인증번호 확인")
	@PostMapping("/check")
	public boolean checkCert(@RequestBody CertDto certDto) {
		return certService.checkCertNumber(certDto.getCertTarget(), certDto.getCertNumber());
	}
	
	// [추가] 아이디/비번 찾기용 인증번호 발송
	@Operation(summary = "아이디/비번 찾기용 휴대폰 인증번호 발송")
	@PostMapping("/sendPhoneForFind")
	public void sendPhoneForFind(@RequestParam String phone) {
	    // 1. 가입된 번호인지 확인
	    int count = accountDao.countByAccountContact(phone);
	    
	    // 2. 가입되지 않았다면 예외 발생 (TargetNotFoundException 등 적절한 예외 사용)
	    // 예외가 발생하면 프론트로 404 상태코드를 보낸다고 가정합니다.
	    if(count == 0) throw new TargetNotfoundException("가입되지 않은 전화번호입니다");
	    
	    // 3. 인증번호 발송
	    certService.sendCertPhone(phone);
	}
	// 아이디/비번 찾기용 인증번호 발송
		@Operation(summary = "아이디/비번 찾기용 이메일 인증번호 발송")
		@PostMapping("/sendEmailForFind")
		public void sendEmailForFind(@RequestParam String email) {
		    // 1. 가입된 번호인지 확인
		    int count = accountDao.countByAccountEmail(email);
		    
		    if(count == 0) throw new TargetNotfoundException("가입되지 않은 이메일입니다");
		    
		    // 3. 인증번호 발송
		    certService.sendCertEmail(email);
		}
	

}
