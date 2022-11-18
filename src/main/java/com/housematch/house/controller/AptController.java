package com.housematch.house.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.housematch.house.model.service.AptDealRecordService;
import com.housematch.house.model.service.AptDetailService;
import com.housematch.house.model.service.AptInfoService;

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
	public ResponseEntity<?> getAptListByDong(@RequestParam(required = true) String dongCode) {

		return ResponseEntity.ok(aptInfoService.getAptListByDong(dongCode));

	}

	@ApiOperation(value = "아파트 상세 정보 조회")
	@GetMapping("/{aptCode}")
	public ResponseEntity<?> getAptDetail(@PathVariable long aptCode) {

		return ResponseEntity.ok(aptDetailService.getAptDetail(aptCode));

	}

	
	
	@ApiOperation(value = "아파트 실거래가 목록 조회")
	@GetMapping("/{aptCode}/deals")
	public ResponseEntity<?> getAptDealRecordList(@PathVariable long aptCode) {

		return ResponseEntity.ok(aptDealRecordService.getAptDealRecordList(aptCode));

	}

}