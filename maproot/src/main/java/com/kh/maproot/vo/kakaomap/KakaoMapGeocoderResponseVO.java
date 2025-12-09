package com.kh.maproot.vo.kakaomap;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class KakaoMapGeocoderResponseVO {
	private KakaoMapGeocoderMetaVO meta;
	private List<KakaoMapGeocoderDocumentVO> documents;
}
