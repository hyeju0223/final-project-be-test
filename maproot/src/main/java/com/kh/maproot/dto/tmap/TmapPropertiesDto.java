package com.kh.maproot.dto.tmap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TmapPropertiesDto {
	int index;
	String description;
	
	String name;
	String roadName;
	int distance;
	int time;
	
	int pointIndex;
	String guidePointName;
	int turnType;
	String pointType;
}
