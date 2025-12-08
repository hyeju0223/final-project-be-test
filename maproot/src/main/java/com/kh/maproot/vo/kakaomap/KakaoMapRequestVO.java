package com.kh.maproot.vo.kakaomap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class KakaoMapRequestVO {
	// 설명 https://developers.kakaomobility.com/docs/navi-api/directions/
	private String origin;
	private String destination;
	private String waypoints;
	private String priority;
	private String avoid;
	private Integer roadevent;
	private Boolean alternatives;
	private Boolean roadDetails;
	private Integer carType;
	private String carFuel;
	private Boolean carHipass;
	private Boolean summary;
	
}
