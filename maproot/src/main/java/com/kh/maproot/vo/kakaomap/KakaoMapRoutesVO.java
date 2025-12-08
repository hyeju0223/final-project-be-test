package com.kh.maproot.vo.kakaomap;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class KakaoMapRoutesVO {
	private Integer resultCode;
	private String resultMsg;
	private KakaoMapSummaryVO summary;
	private List<KakaoMapSectionVO> sections; // 구간별 경로 정보
}
