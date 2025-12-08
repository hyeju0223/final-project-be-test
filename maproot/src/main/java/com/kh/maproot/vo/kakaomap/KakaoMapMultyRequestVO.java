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
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class KakaoMapMultyRequestVO {
	// 설명 https://developers.kakaomobility.com/docs/navi-api/directions/
	private KakaoMapLocationVO origin;
	private KakaoMapLocationVO destination;
	private List<KakaoMapLocationVO> waypoints;
	private String priority;
	private List<String> avoid;
	private Integer roadevent;
	private Boolean alternatives;
	private Boolean roadDetails;
	private Integer carType;
	private String carFuel;
	private Boolean carHipass;
	private Boolean summary;
}
