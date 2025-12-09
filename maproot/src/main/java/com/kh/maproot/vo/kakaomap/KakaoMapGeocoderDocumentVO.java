package com.kh.maproot.vo.kakaomap;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class KakaoMapGeocoderDocumentVO {
	private KakaoMapGeocoderAddressVO address;
	private KakaoMapGeocoderRoadAddressVO roadAddress;
}
