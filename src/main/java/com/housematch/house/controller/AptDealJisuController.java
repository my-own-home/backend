package com.housematch.house.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.housematch.house.model.dto.AptDealJisuDto;
import com.housematch.house.model.service.AptDealJisuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/apts/dealjisu")
@Api(value = "매매지수 Controller")
public class AptDealJisuController {
	
	@Autowired
	private AptDealJisuService aptDealJisuService;
	
	@ApiOperation(value = "리뷰 목록 조회")
	@GetMapping("/{loc}")
	public ResponseEntity<?> getAptDealJisuList(@PathVariable String loc) {

		AptDealJisuDto dealJisu = aptDealJisuService.getAptDealJisuList(loc);

		Map<String, Object> response = new HashMap<>();
		response.put("dealJisu", dealJisu);

		return ResponseEntity.ok(response);
	}

	
}
