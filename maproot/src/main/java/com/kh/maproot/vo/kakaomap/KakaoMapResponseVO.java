package com.kh.maproot.vo.kakaomap;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder @Data @NoArgsConstructor @AllArgsConstructor
public class KakaoMapResponseVO {
	private String transId; // 경로 요청 ID
	private List<KakaoMapRoutesVO> routes; // 경로 정보

}
