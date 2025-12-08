package com.kh.maproot.kakaomap;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class KakaoMapTest01 {
	
	
	@Test
	public void test() {
		// WebClient 준비
		WebClient webClient = WebClient.builder()
									.baseUrl("https://apis-navi.kakaomobility.com") // 시작주소 지정
									.defaultHeader("Authorization", "KakaoAK 2be850ebcd2e3cb4e67e989a5398494c")
									.defaultHeader("Content-Type", "application/json") // 전송데이터 유형설정
							.build();
		
		// Body 준비
		
		Map response = webClient.get() // 포스트 요철
				.uri(uriBuilder -> uriBuilder
				        .path("/v1/directions") // 🚨 baseUrl 이후의 경로만 지정
				        .queryParam("origin", "127.1112,37.3949") // 🚨 쿼리 파라미터로 데이터 전달
				        .queryParam("destination", "127.1110,37.3949")
				        .build()
				    )
			.retrieve() // 응답을 수신하겠다
				.bodyToMono(Map.class) // 데이터는 한번에 오고(Mono) 형태는 Map이다 (연속적으로 오면 Flux)
				.block(); // 동기적으로 변환하여 응답이 올때까지 기다려라. (RestTemplate과 같아짐)

		// 출력 (다음 테스트를 위해 정보 확인_
		for(Object name : response.keySet()) {
			Object value = response.get(name);
			log.debug("{} = {}", name, value);
		}		
	}
}
