package com.kh.maproot.tmap;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.kh.maproot.MaprootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = MaprootApplication.class) @Slf4j
public class TmapTest01 {
	
	@Autowired @Qualifier("TmapWebClient")
	private WebClient webClient;
	
	@Test
	public void test() {
		Map request = new HashMap<>();
		
		request.put("startX", 126.92365493654832);
		request.put("startY", 37.556770374096615	);
		request.put("endX", 126.92432158129688	);
		request.put("endY", 37.55279861528311);
		request.put("startName", "출발지");
		request.put("endName", "도착지");
		request.put("passList", "126.97807504241935,37.56663488775221_126.97805305462796,37.56441843329491_126.97821199126308,37.56278765928395_126.98068442374593,37.56529736440929_126.98068516812958,37.56241417622502");
		request.put("reqCoordType", "WGS84GEO");
		request.put("resCoordType", "WGS84GEO");
		
		
		
		String responseBody = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/routes/pedestrian")
                        .queryParam("version", 1) // version 쿼리 파라미터는 필수입니다.
                        .build())
                .bodyValue(request) // TmapRouteRequest 객체가 JSON으로 직렬화됩니다.
                .retrieve()
                .bodyToMono(String.class) // 응답을 String 형태로 받습니다. (추후 Map이나 DTO로 변환 가능)
                .block(); // 비동기 WebClient를 동기적으로 사용 (주의: 블로킹됨)
		
		log.debug("resoponseBody = {}", responseBody);

	}
}
