package com.kh.maproot.dto.tmap;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TmapResponseDto {
	String type;
	List<TmapFeatureDto> features;
}
