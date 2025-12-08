package com.kh.maproot.vo.kakaomap;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class KakaoMapSummaryVO {
	private KakaoMapLocationVO origin;
	private KakaoMapLocationVO destination;
	private List<KakaoMapLocationVO> waypoint;
	private String priority;
	private KakaoMapBoundVO bound;
	private KakaoMapFareVO fare;
	private Integer distance;
	private Integer duration;
}
