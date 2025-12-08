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
public class KakaoMapSectionVO {
	private Integer distance; // 섹션 거리(미터)
	private Integer duration; // 총 이동시간(초)
	private KakaoMapBoundVO bound; // 모든 경로을 포함하는 사각형의 바운딩 박스
	private List<KakaoMapRoadVO> roads; // 도로 정보 (summary == false)
	private List<KakaoMapGuideVO> guides; //안내 정보 (summary == false)
}
