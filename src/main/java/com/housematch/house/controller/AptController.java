package com.housematch.house.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.housematch.house.model.dto.AptDealRecordDto;
import com.housematch.house.model.service.AptDealRecordService;
import com.housematch.house.model.service.AptDetailService;
import com.housematch.house.model.service.AptInfoService;
import com.housematch.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/apts")
@Api(value = "아파트 정보 Controller")
public class AptController {

	@Autowired
	private AptInfoService aptInfoService;
	@Autowired
	private AptDetailService aptDetailService;
	@Autowired
	private AptDealRecordService aptDealRecordService;

	@ApiOperation(value = "동별 아파트 목록 조회")
	@GetMapping
	public ResponseEntity<?> getAptListByDong(@RequestParam String dongCode) {
		return ResponseEntity.ok(aptInfoService.getAptListByDong(dongCode));
	}

	@ApiOperation(value = "아파트 기본 정보 조회")
	@GetMapping("/{aptCode}")
	public ResponseEntity<?> getAptInfo(@PathVariable long aptCode) {
		return ResponseEntity.ok(aptInfoService.getAptInfo(aptCode));
	}

	@ApiOperation(value = "아파트 상세 정보 조회")
	@GetMapping("/{aptCode}/detail")
	public ResponseEntity<?> getAptDetail(@PathVariable long aptCode) {
		return ResponseEntity.ok(aptDetailService.getAptDetail(aptCode));
	}

	@ApiOperation(value = "아파트 실거래가 목록 조회")
	@GetMapping("/{aptCode}/deals")
	public ResponseEntity<?> getAptDealRecordList(@PathVariable long aptCode,
			@RequestParam(required = false) Integer pgno) {

		Map<String, Object> conditions = new HashMap<>();

		if (pgno != null) {
			conditions.put("pgno", pgno);
		} else {
			conditions.put("pgno", 1);
		}

		conditions.put("aptCode", aptCode);
		
		List<AptDealRecordDto> deals = aptDealRecordService.getAptDealRecordListWithPage(conditions);
		PageNavigation navigation = aptDealRecordService.makePageNavigation(conditions);
		
		Map<String, Object> response = new HashMap<>();
		response.put("deals", deals);
		response.put("navigation", navigation);

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "basic + detail")
	@GetMapping("/{aptCode}/all")
	public ResponseEntity<?> getAptAll(@PathVariable long aptCode) {
		Map<String, Object> apt = new HashMap<>();
		apt.put("basic", aptInfoService.getAptInfo(aptCode));
		apt.put("detail", aptDetailService.getAptDetail(aptCode));
//		apt.put("deals", aptDealRecordService.getAptDealRecordList(aptCode));		

		return ResponseEntity.ok(apt);
	}

	@ApiOperation(value = "Monthly Average")
	@GetMapping("/{aptCode}/deals/monthly-avg")
	public ResponseEntity<?> getAptDealRecordAvg(@PathVariable long aptCode) {
		return ResponseEntity.ok(aptDealRecordService.getAptDealRecordMonthlyAvgByArea(aptCode));
	}

	
}
