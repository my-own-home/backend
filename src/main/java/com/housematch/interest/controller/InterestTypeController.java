package com.housematch.interest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housematch.interest.model.service.InterestTypeService;
import com.housematch.user.model.dto.UserDto;
import com.housematch.user.model.service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user/interest/types")
public class InterestTypeController {

	@Autowired
	private UserService userService;
	@Autowired
	private InterestTypeService interestTypeService;

	@ApiOperation(value = "관심사 목록 조회")
	@GetMapping("/{userId}")
	public ResponseEntity<?> getInterestAptList(@PathVariable String userId) {

		UserDto user = userService.getUser(userId);

		if (user != null) {
			return ResponseEntity.ok(interestTypeService.getInterestTypeList(userId));
		} else {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		}

	}

}
