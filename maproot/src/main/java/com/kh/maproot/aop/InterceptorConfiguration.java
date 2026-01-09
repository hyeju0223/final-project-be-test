package com.kh.maproot.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

	@Autowired
	private AccountInterceptor accountInterceptor;
	@Autowired
	private TokenRenewalInterceptor tokenRenewalInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(accountInterceptor)
				.addPathPatterns("/api/account/logout", "/api/account/mypage", "/api/chat/**",
						"/api/chat", "/api/kakaopay/buy",
						"/api/payment/**", "/api/schedule/list", "/api/schedule/delete/**",
						"/api/account/edit", "/api/message/**",
						"/api/kakaoMap/**", "/api/account/withdraw", "/api/schedule/insert", "/api/admin/**",
						"/api/account/scheduleLike/**", "/api/account/dropAdmin", "/api/account/profile"
						)
				.excludePathPatterns(

				);
		registry.addInterceptor(tokenRenewalInterceptor).addPathPatterns("/api/**").excludePathPatterns("/",
				"/index.html", "/favicon.ico", "/assets/**", "/css/**", "/js/**", "/images/**",

				"/api/account/refresh", "/api/account/join", "/api/account/login", 
				"/api/account/logout", "/ws", "/websocket/**",
				"/api/schedule/detail", "/api/share/**", "/api/review/**",
				 "/api/attachment/download", "/api/attachment/download/**"
				);
	
	}
}

