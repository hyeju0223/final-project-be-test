package com.kh.maproot.vo.kakaomap;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class KakaoMapGeocoderRoadAddressVO {
	private String addressName; // 전체 도로명 주소
	private String region1depthName; // 지역 1Depth명, 시도 단위
	private String region2depthName; // 지역 2Depth명, 구 단위
	private String region3depthName; // 지역 3Depth명, 면 단위
	private String roadName; // 도로명
	private String undergroundYn; // 지하 여부, Y or N
	private String mainBuildingNo; // 건물 본번
	private String subBuildingNo; // 건물 부번, 없을 경우 빈 문자열("") 반환
	private String buildingName; // 건물 이름
	private String zoneNo; // 우편 번호
}
