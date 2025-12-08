package com.kh.maproot.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.maproot.service.KakaoMapService;
import com.kh.maproot.vo.kakaomap.KakaoMapLocationVO;
import com.kh.maproot.vo.kakaomap.KakaoMapMultyRequestVO;
import com.kh.maproot.vo.kakaomap.KakaoMapRequestVO;
import com.kh.maproot.vo.kakaomap.KakaoMapResponseVO;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin @Slf4j
@RestController @RequestMapping("/kakaoMap")
public class KakaoMapRestController {
	
	@Autowired
	private KakaoMapService kakaoMapService;
	
	@PostMapping("/search")
	public KakaoMapResponseVO search(@RequestBody List<KakaoMapLocationVO> location, @RequestParam String priority) {
		String origin = location.getFirst().getLngLat();
		String destination =location.getLast().getLngLat();
		
		KakaoMapRequestVO requestVO = KakaoMapRequestVO.builder()
					.origin(origin)
					.destination(destination)
//					.waypoints(null)
					.priority(priority)
//					.avoid(null)
					.roadevent(2)
					.alternatives(true)
//					.roadDetails(null)
//					.carType(null)
//					.carFuel(null)
//					.carHipass(null)
					.summary(false)
				.build();
		
		return kakaoMapService.direction(requestVO);
	}
	
	@PostMapping("/searchAll")
	public KakaoMapResponseVO searchAll(@RequestBody List<KakaoMapLocationVO> location) {
		KakaoMapMultyRequestVO requestVO = KakaoMapMultyRequestVO.builder()
					.origin(location.removeFirst())
					.destination(location.removeLast())
					.waypoints(location)
					.roadevent(2)
					.alternatives(true)
					.summary(false)
					.priority("RECOMMEND")
//					.avoid(null)
//					.roadDetails(null)
//					.carType(null)
					.carFuel("GASOLINE")
//					.carHipass(null)
				.build();
		
		return kakaoMapService.directionMulty(requestVO);
	}
}
