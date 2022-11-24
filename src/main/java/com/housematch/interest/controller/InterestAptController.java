package com.housematch.interest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.housematch.house.model.dto.AptDealRecordDto;
import com.housematch.house.model.dto.AptInfoDto;
import com.housematch.house.model.service.AptDealRecordService;
import com.housematch.house.model.service.AptInfoService;
import com.housematch.interest.model.dto.InterestAptDto;
import com.housematch.interest.model.service.InterestAptService;
import com.housematch.user.model.dto.UserDto;
import com.housematch.user.model.service.UserService;
import com.housematch.util.PageNavigation;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user/interest/apts")
public class InterestAptController {

	@Autowired
	private UserService userService;
	@Autowired
	private InterestAptService interestAptService;
	@Autowired
	private AptInfoService aptInfoService;
	@Autowired
	private AptDealRecordService aptDealRecordService;

	@ApiOperation(value = "관심 아파트 목록 조회")
	@GetMapping
	public ResponseEntity<?> getInterestAptList(@RequestParam(required = false) Integer pgno,
			@RequestParam String userId) {

		UserDto user = userService.getUser(userId);

		if (user != null) {

			Map<String, Object> conditions = new HashMap<String, Object>();
			conditions.put("userId", userId);

			if (pgno != null) {
				conditions.put("pgno", pgno);
			} else {
				conditions.put("pgno", 1);
			}

			List<InterestAptDto> interestApts = interestAptService.getInterestAptList(conditions);
			Map<Integer,AptInfoDto > aptInfos = new HashMap<Integer, AptInfoDto>();
			
			List<Integer> recentPrice = new ArrayList<>();
			
			for (int i = 0; i < interestApts.size(); i++) {
				InterestAptDto interestApt = interestApts.get(i);
				AptInfoDto aptInfo = aptInfoService.getAptInfo(interestApt.getAptCode());
				
				AptDealRecordDto aptDealRecord = aptDealRecordService.getAptDealRecordList(interestApt.getAptCode()).get(0);
				recentPrice.add(Integer.parseInt(aptDealRecord.getDealAmount()));
				
				if(aptInfo != null) {
					aptInfos.put(i+1,aptInfo);
				}
			}
			
			PageNavigation navigation = interestAptService.makePageNavigation(conditions);
			
			Map<String, Object> response = new HashMap<String, Object>();
			response.put("interestApts", interestApts);
			response.put("navigation", navigation);
			response.put("aptInfos", aptInfos);
			response.put("recentPrice", recentPrice);

			return ResponseEntity.ok(response);

		} else {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "관심 아파트 추가")
	@PostMapping("/{aptCode}")
	public ResponseEntity<?> addInterestApt(@PathVariable long aptCode, @RequestParam String userId) {

		UserDto user = userService.getUser(userId);

		if (user != null) {
			InterestAptDto interestApt = new InterestAptDto(userId, aptCode);

			if (interestAptService.checkDuplicateInterestApt(interestApt)) {
				return ResponseEntity.badRequest().build();
			}

			boolean res = interestAptService.addInterestApt(interestApt);

			if (res) {
				return ResponseEntity.ok(interestAptService.getInterestApt(interestApt));
			} else {
				return ResponseEntity.internalServerError().build();
			}

		} else {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "관심 아파트 삭제")
	@DeleteMapping("/{aptCode}")
	public ResponseEntity<?> removeInterestApt(@PathVariable long aptCode, @RequestParam String userId) {

		InterestAptDto interestApt = interestAptService.getInterestApt(new InterestAptDto(userId, aptCode));

		if (interestApt != null) {

			boolean res = interestAptService.removeInterestApt(interestApt);

			if (res) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.internalServerError().build();
			}

		} else {
			return ResponseEntity.badRequest().build();
		}
	}

}
