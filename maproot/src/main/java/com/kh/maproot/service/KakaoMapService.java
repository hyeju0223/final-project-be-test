package com.kh.maproot.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kh.maproot.vo.kakaomap.KakaoMapGeocoderRequestVO;
import com.kh.maproot.vo.kakaomap.KakaoMapGeocoderResponseVO;
import com.kh.maproot.vo.kakaomap.KakaoMapMultyRequestVO;
import com.kh.maproot.vo.kakaomap.KakaoMapRequestVO;
import com.kh.maproot.vo.kakaomap.KakaoMapResponseVO;

import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
public class KakaoMapService {
	@Autowired @Qualifier("kakaomapWebClient")
	private WebClient mapClient;
	
	@Autowired @Qualifier("kakaomapGeocoder")
	private WebClient geoClient;
	
	
	public KakaoMapResponseVO direction(KakaoMapRequestVO requestVO) {
		log.debug("requsetVO = {}", requestVO);
		KakaoMapResponseVO response = mapClient.get() 
				.uri(uriBuilder -> uriBuilder
				        .path("/v1/directions") // baseUrl 이후의 경로만 지정
				        .queryParam("origin", requestVO.getOrigin()) // 쿼리 파라미터로 데이터 전달
				        .queryParam("destination", requestVO.getDestination())
				        .queryParam("summary", requestVO.getSummary())
				        .queryParam("alternatives", requestVO.getAlternatives())
				        .queryParam("priority", requestVO.getPriority())
				        .queryParam("roadevent", requestVO.getRoadevent())
				        .build()
				    )
			.retrieve() // 응답을 수신하겠다
				.onStatus(HttpStatusCode::isError, clientResponse ->
					clientResponse.bodyToMono(String.class).map(body -> {
						log.error("Error body = {}", body);
						return new RuntimeException("Status: " + clientResponse.statusCode() + ", body: " + body);
					})
				) // 오류 체크용
				.bodyToMono(KakaoMapResponseVO.class)
				.block(); // 동기적으로 변환하여 응답이 올때까지 기다려라. (RestTemplate과 같아짐)
		
		return response;
	}
	public KakaoMapResponseVO directionMulty(KakaoMapMultyRequestVO requestVO) {
		log.debug("requsetVO = {}", requestVO);
		KakaoMapResponseVO response = mapClient.post() 
				.uri("/v1/waypoints/directions")
				.bodyValue(requestVO)
				.retrieve() // 응답을 수신하겠다
				.onStatus(HttpStatusCode::isError, clientResponse ->
				clientResponse.bodyToMono(String.class).map(body -> {
					log.error("Error body = {}", body);
					return new RuntimeException("Status: " + clientResponse.statusCode() + ", body: " + body);
				})
						) // 오류 체크용
				.bodyToMono(KakaoMapResponseVO.class)
				.block(); // 동기적으로 변환하여 응답이 올때까지 기다려라. (RestTemplate과 같아짐)
		
		return response;
	}
	public KakaoMapGeocoderResponseVO getAddress(KakaoMapGeocoderRequestVO requestVO) {
		// TODO Auto-generated method stub
		log.debug("requestVO = {}", requestVO);
		KakaoMapGeocoderResponseVO response = geoClient.get()
				.uri(uriBuilder -> uriBuilder
				        .path("/coord2address") // 🚨 baseUrl 이후의 경로만 지정
				        .queryParam("x", requestVO.getX()) // 🚨 쿼리 파라미터로 데이터 전달
				        .queryParam("y", requestVO.getY())
				        .queryParam("input_coord", requestVO.getInputCoord())
				        .build()
				    )
			.retrieve() // 응답을 수신하겠다
				.bodyToMono(KakaoMapGeocoderResponseVO.class) // 데이터는 한번에 오고(Mono) 형태는 Map이다 (연속적으로 오면 Flux)
				.block(); // 동기적으로 변환하여 응답이 올때까지 기다려라. (RestTemplate과 같아짐)
		
		return response;
		
	}
}
