package com.housematch.interest.controller;

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

import com.housematch.house.model.dto.LocationDto;
import com.housematch.house.model.service.LocationService;
import com.housematch.interest.model.dto.InterestDongDto;
import com.housematch.interest.model.service.InterestDongService;
import com.housematch.user.model.dto.UserDto;
import com.housematch.user.model.service.UserService;
import com.housematch.util.PageNavigation;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user/interest/locations")
public class InterestDongController {

	@Autowired
	private UserService userService;
	@Autowired
	private InterestDongService interestDongService;
	@Autowired
	private LocationService locationService;

	@ApiOperation(value = "관심 지역 목록 조회")
	@GetMapping
	public ResponseEntity<?> getInterestDongList(@RequestParam(required = false) Integer pgno,
			@RequestParam String userId) {

		UserDto user = userService.getUser(userId);

		if (user != null) {

			Map<String, Object> conditions = new HashMap<>();
			conditions.put("userId", userId);

			if (pgno != null) {
				conditions.put("pgno", pgno);
			} else {
				conditions.put("pgno", 1);
			}

			List<InterestDongDto> interestLocs = interestDongService.getInterestDongList(conditions);
			Map<Integer, LocationDto> dongInfos = new HashMap<>();
			
			for (int i = 0; i < interestLocs.size(); i++) {
				InterestDongDto interestLoc = interestLocs.get(i);
				LocationDto dongInfo = locationService.getDong(interestLoc.getDongCode());
				
				if(dongInfo != null) {
					dongInfos.put(i+1,dongInfo);
				}
			}
			
			
			PageNavigation navigation = interestDongService.makePageNavigation(conditions);

			Map<String, Object> response = new HashMap<>();
			response.put("interestLocs", interestLocs);
			response.put("navigation", navigation);
			response.put("dongInfos", dongInfos);

			return ResponseEntity.ok(response);

		} else {
			return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "관심 지역 추가")
	@PostMapping("/{dongCode}")
	public ResponseEntity<?> addInterestDong(@PathVariable String dongCode, @RequestBody String userId) {

		UserDto user = userService.getUser(userId);

		if (user != null) {
			InterestDongDto interestDong = new InterestDongDto(userId, dongCode);

			if (interestDongService.checkDuplicateInterestDong(interestDong)) {
				return ResponseEntity.badRequest().build();
			}

			boolean res = interestDongService.addInterestDong(interestDong);

			if (res) {
				return ResponseEntity.ok(interestDongService.getInterestDong(interestDong));
			} else {
				return ResponseEntity.internalServerError().build();
			}

		} else {
			return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "관심 지역 삭제")
	@DeleteMapping("/{dongCode}")
	public ResponseEntity<?> removeInterestDong(@PathVariable String dongCode, @RequestParam String userId) {

		InterestDongDto interestDong = interestDongService.getInterestDong(new InterestDongDto(userId, dongCode));

		if (interestDong != null) {

			boolean res = interestDongService.removeInterestDong(interestDong);

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
