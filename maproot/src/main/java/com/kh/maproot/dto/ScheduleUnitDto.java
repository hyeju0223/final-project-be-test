package com.kh.maproot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class ScheduleUnitDto {
	
    // PK
    private Integer scheduleUnitNo;      // schedule_unit_no

    // 부모 스케줄 번호
    private Integer scheduleNo;          // schedule_no

    // 세부 일정 내용 (설명)
    private String scheduleUnitContent;  // schedule_unit_content

    // 소요 시간 (단위: 시간/분 등, 정의에 맞춰 사용)
    private Integer scheduleUnitTime;    // schedule_unit_time

    // 위도 / 경도
    private Double scheduleUnitLat;      // schedule_unit_lat
    private Double scheduleUnitLng;      // schedule_unit_lng
    
    // 순서 (같은 스케줄 안에서의 정렬 순서)
    private Integer scheduleUnitPosition; // schedule_unit_position

    // 장소 이름
    private String scheduleUnitName;     // schedule_unit_name

    // 여행 일차 (1일차, 2일차…)
    private Integer scheduleUnitDay;     // schedule_unit_day

}
