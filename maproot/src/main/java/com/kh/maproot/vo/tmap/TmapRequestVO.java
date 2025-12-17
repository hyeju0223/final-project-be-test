package com.kh.maproot.vo.tmap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TmapRequestVO {
	Double startX; // 출발지 X좌표	!
	Double startY; // 출발지 Y좌표	!
	String endPolid; // 목적지 POI ID
	Double endX; // 목적지 X좌표	!
	Double endY; // 목적지 Y좌표	!
	String passList; // 경유지 리스트(X 좌표, Y 좌표를 콤마(,)와 밑줄(_)로 구분하여 순서대로 나열합니다.(최대 5곳))
	@Builder.Default
    String reqCoordType = "WGS84GEO"; // 지구 위의 위치를 나타내는 좌표 타입
																						/*
																							1) EPSG3857- Google Mercator
																							2) WGS84GEO(기본값)
																							- 경위도
																							3) KATECH
																							- TM128(Transverse Mercator:횡메카토르): 한국 표준
																						*/
	String startName; // 출발지 명칭	!
	String endName; // 목적지 명칭	!
	@Builder.Default
    Integer searchOption = 0; // 경로 탐색 옵션
															/*
																- 0: 추천 (기본값)
																- 4: 추천+대로우선
																- 10: 최단
																- 30: 최단거리+계단제외
															*/
	@Builder.Default
    String resCoordType = "WGS84GEO"; // 받고자 하는 응답 좌표계 유형
	@Builder.Default
    String sort = "index"; // 지리정보 개체의 정렬 순서 지정
												/*
													1) index(기본값)
													- 노드의 종류에 상관없이 인덱스의 순서로 정렬을 합니다.
													2) custom
													- 라인노드, 포인트노드의 순서로 정렬을 합니다.
												*/
}
