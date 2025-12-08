package com.kh.maproot.vo.kakaomap;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class KakaoMapRoadVO {
	private String name; // 도로명
	private Integer distance; // 도로 길이(미터)
	private Integer duration; // 예상 이동 시간(초)
	private Double trafficSpeed; // 현재 교통 정보 속도(km/h)
	private Integer trafficState; // 현재 교통 정보 상태 https://developers.kakaomobility.com/docs/navi-api/reference/
	private List<Double> vertexes; // X, Y 좌표로 구성된 1차원 배열(Data = 위도,경도)
}
