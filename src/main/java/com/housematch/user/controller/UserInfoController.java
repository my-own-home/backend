package com.housematch.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housematch.interest.model.service.InterestTypeService;
import com.housematch.user.model.dto.UserDto;
import com.housematch.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/user/info")
@Api(value = "사용자 Controller")
public class UserInfoController {

	@Autowired
	private UserService userService;

	@Autowired
	private InterestTypeService interestTypeService;

	@ApiOperation(value = "사용자 상세 조회")
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable String userId) {

		UserDto user = userService.getUser(userId);

		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "사용자 상세 조회")
	@GetMapping("/{userId}/check")
	public ResponseEntity<?> checkIdDuplicate(@PathVariable String userId) {

		UserDto user = userService.getUser(userId);

		if (user != null) {
			return ResponseEntity.ok("success");
		} else {
			return ResponseEntity.ok("fail");
		}
	}

	@ApiOperation(value = "사용자 생성")
	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {
		boolean res = userService.addUser(userDto);

		if (res) {

			Map<String, Object> request = new HashMap<>();
			request.put("id", userDto.getId());
			request.put("types", userDto.getTypes());
			interestTypeService.addInterestTypeList(request);
			
			return ResponseEntity.ok(userDto);
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@ApiOperation(value = "사용자 상세 수정")
	@PutMapping("/{userId}")
	public ResponseEntity<?> modifyUser(@PathVariable String userId, @RequestBody UserDto userDto) {

		if (!userId.equals(userDto.getId())) {
			return ResponseEntity.badRequest().build();
		}

		UserDto user = userService.getUser(userId);

		if (user != null) {

			boolean res = userService.modifyUser(userDto);

			if (res) {
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.internalServerError().build();
			}

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "사용자 삭제")
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> removeUser(@PathVariable String userId) {

		UserDto user = userService.getUser(userId);

		if (user != null) {
			boolean res = userService.removeUser(userId);

			if (res) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.internalServerError().build();
			}

		} else {
			return ResponseEntity.notFound().build();
		}

	}

}
