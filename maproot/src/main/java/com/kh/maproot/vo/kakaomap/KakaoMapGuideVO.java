package com.kh.maproot.vo.kakaomap;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KakaoMapGuideVO {
	private String name; // 명칭
	private Double x; // 경도
	private Double y; // 위도
	private Integer distance; // 이전 가이드 지점부터 현재 가이드 지점까지 거리(미터)
	private Integer duration; // 이전 가이드 지점부터 현재 가이드 지점까지 시간(초)
	private Integer type; // 안내 타입 https://developers.kakaomobility.com/docs/navi-api/reference/#type-%EC%95%88%EB%82%B4-%ED%83%80%EC%9E%85
	private String guidance; // 안내 문구
	private Integer roadIndex; // 현재 가이드에 대한 링크 인덱스
}
