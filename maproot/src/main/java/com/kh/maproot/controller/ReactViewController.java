package com.kh.maproot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


//React로 화면을 연결(forward)하기 위한 컨트롤러
@Controller
public class ReactViewController {

	@RequestMapping(value = {
			"/",
			"/kakaomap/**",
			"/kakaopay/**",
			"/account/**",
			"/schedule/**",
			"/mypage/**",
			"/dashboard/**",
			"/error/**",
			"/admin/**",
			"/share/**",
			"/schedulePage/**"
			})
	public String forward() {
		return "forward:/index.html";
	}
	
}
