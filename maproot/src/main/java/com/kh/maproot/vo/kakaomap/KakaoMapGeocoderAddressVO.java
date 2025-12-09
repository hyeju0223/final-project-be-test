package com.kh.maproot.vo.kakaomap;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class KakaoMapGeocoderAddressVO {
	private String addressName; // 전체 지번 주소
	private String region1depthName; // 지역 1Depth명, 시도 단위
	private String region2depthName; // 지역 2Depth명, 구 단위
	private String region3depthName; // 지역 3Depth명, 동 단위
	private String mountainYn; // 산 여부, Y or N
	private String mainAddressNo; // 지번 주 번지
	private String subAddressNo; // 지번 부 번지, 없을 경우 빈 문자열 ("") 반환
}
