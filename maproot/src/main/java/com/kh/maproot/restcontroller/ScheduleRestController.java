package com.kh.maproot.restcontroller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.maproot.dao.ScheduleDao;
import com.kh.maproot.dao.ScheduleTagDao;
import com.kh.maproot.dao.TagDao;
import com.kh.maproot.dto.ScheduleDto;
import com.kh.maproot.dto.ScheduleTagDto;
import com.kh.maproot.dto.TagDto;
import com.kh.maproot.schedule.vo.ScheduleCreateRequestVO;

@CrossOrigin
@RestController
@RequestMapping("/schedule")
public class ScheduleRestController {
	
	@Autowired
	private TagDao tagDao;
	@Autowired
	private ScheduleDao scheduleDao;
	@Autowired
	private ScheduleTagDao scheduleTagDao;
	
	@GetMapping("/tagList")
	public List<TagDto> tagList() {
		return tagDao.selectAll();
	}
	
	@PostMapping("/insert")
	public ScheduleDto insert(@RequestBody ScheduleCreateRequestVO scheduleVO) {
		
		//일정 등록
		ScheduleDto scheduleDto = ScheduleDto.builder()
						.scheduleName(scheduleVO.getScheduleName())
						.scheduleOwner(scheduleVO.getScheduleOwner())
						.scheduleWtime(Timestamp.valueOf(LocalDateTime.now()))
						.scheduleStartDate(scheduleVO.getScheduleStartDate())
						.scheduleEndDate(scheduleVO.getScheduleEndDate())
						.build();
		
		int sequence = scheduleDao.insert(scheduleDto);
		
		//태그 등록
		for(String tagName : scheduleVO.getTagNoList()) {
			ScheduleTagDto scheduleTagDto = ScheduleTagDto.builder()
					.scheduleNo(sequence)
					.tagName(tagName)
					.build();			
			
			scheduleTagDao.insert(scheduleTagDto);
		}
		return scheduleDto;
	}


}
