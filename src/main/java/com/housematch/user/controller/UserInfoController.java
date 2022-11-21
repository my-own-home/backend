package com.housematch.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.housematch.admin.model.service.JwtServiceImpl;
import com.housematch.user.model.dto.UserDto;
import com.housematch.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/user/info")
@Api(value = "사용자 Controller")
public class UserInfoController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtServiceImpl jwtService;

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

	@ApiOperation(value = "사용자 생성")
	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody UserDto userDto) {

		boolean res = userService.addUser(userDto);

		if (res) {
			return ResponseEntity.ok().build();
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
	
	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) UserDto userDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			System.out.println("2222222222222222222");
			UserDto loginUser = userService.login(userDto);
			System.out.println(loginUser);
			if (loginUser != null) {
				String token = jwtService.create("userid", loginUser.getId(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
