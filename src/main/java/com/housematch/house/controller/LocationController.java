package com.housematch.house.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housematch.house.model.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/location")
@Api(value = "아파트 정보 Controller")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	@ApiOperation(value = "동 조회")
	@GetMapping("/{dongCode}")
	public ResponseEntity<?> getDong(@PathVariable String dongCode){
		return ResponseEntity.ok(locationService.getDong(dongCode));
	}

	@ApiOperation(value = "동 목록 조회")
	@GetMapping("/dong/{dongCode}")
	public ResponseEntity<?> getDongList(@PathVariable String dongCode){
		return ResponseEntity.ok(locationService.getDongList(dongCode));
	}

	@ApiOperation(value = "구군 목록 조회")
	@GetMapping("/gugun/{dongCode}")
	public ResponseEntity<?> getGugunList(@PathVariable String dongCode){
		return ResponseEntity.ok(locationService.getGugunList(dongCode));
	}

	@ApiOperation(value = "시도 목록 조회")
	@GetMapping("/sido/{dongCode}")
	public ResponseEntity<?> getSidoList(@PathVariable String dongCode){
		return ResponseEntity.ok(locationService.getSidoList(dongCode));
	}
}
