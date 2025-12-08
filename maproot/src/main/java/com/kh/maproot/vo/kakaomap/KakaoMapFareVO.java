package com.kh.maproot.vo.kakaomap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class KakaoMapFareVO {
	private Integer taxi; // 택시요금(원)
	private Integer toll; // 통행 요금(원)
}
