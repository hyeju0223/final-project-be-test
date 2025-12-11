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

}
