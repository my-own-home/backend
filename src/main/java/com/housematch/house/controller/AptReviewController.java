package com.housematch.house.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.housematch.house.model.dto.AptInfoDto;
import com.housematch.house.model.dto.AptReviewDto;
import com.housematch.house.model.dto.AptReviewStatDto;
import com.housematch.house.model.service.AptInfoService;
import com.housematch.house.model.service.AptReviewService;
import com.housematch.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/apts/reviews")
@Api(value = "리뷰 Controller")
public class AptReviewController {

	@Autowired
	private AptReviewService aptReviewService;
	@Autowired
	private AptInfoService aptInfoService;

	@ApiOperation(value = "리뷰 목록 조회")
	@GetMapping
	public ResponseEntity<?> getAptReviewList(@RequestParam(required = false) long aptCode,
			@RequestParam(required = false) Integer pgno) {
		Map<String, String> conditions = new HashMap<>();

		if (pgno != null) {
			conditions.put("pgno", Integer.toString(pgno));
		} else {
			conditions.put("pgno", "1");
		}

		List<AptReviewDto> reviews = aptReviewService.getAptReviewList(aptCode);
		PageNavigation navigation = aptReviewService.makePageNavigation(conditions);

		Map<String, Object> response = new HashMap<>();
		response.put("reviews", reviews);
		response.put("navigation", navigation);

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "사용자 리뷰 목록 조회")
	@GetMapping("/user")
	public ResponseEntity<?> getUserReviewList(@RequestParam(required = false) String uid,
			@RequestParam(required = false) Integer pgno) {
		Map<String, String> conditions = new HashMap<>();

		if (pgno != null) {
			conditions.put("pgno", Integer.toString(pgno));
		} else {
			conditions.put("pgno", "1");
		}

		List<AptReviewDto> reviews = aptReviewService.getUserReviewList(uid);
		List<String> aptNames = new ArrayList<>();

		for (AptReviewDto review : reviews) {
			AptInfoDto aptInfo = aptInfoService.getAptInfo(review.getAptCode());
			String aptName = aptInfo.getAptName();

			aptNames.add(aptName);
		}

		PageNavigation navigation = aptReviewService.makePageNavigation(conditions);

		Map<String, Object> response = new HashMap<>();
		response.put("reviews", reviews);
		response.put("navigation", navigation);
		response.put("aptNames", aptNames);

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "리뷰 상세 조회")
	@GetMapping("/{no}")
	public ResponseEntity<?> getAptReview(@PathVariable int no) {

		AptReviewDto review = aptReviewService.getAptReview(no);

		if (review != null)
			return ResponseEntity.ok(review);
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "리뷰 등록")
	@PostMapping
	public ResponseEntity<?> addAptReview(@RequestBody AptReviewDto aptReviewDto) {

		boolean res = aptReviewService.addAptReview(aptReviewDto);

		if (res) {
			return ResponseEntity.ok("success");
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@ApiOperation(value = "리뷰 수정")
	@PutMapping("/{no}")
	public ResponseEntity<?> modifyAptReview(@PathVariable int no, @RequestBody AptReviewDto request) {

		String userId = request.getUid();

		if (no != request.getNo()) {
			return ResponseEntity.badRequest().build();
		}

		AptReviewDto review = aptReviewService.getAptReview(no);

		if (review != null) {

			if (!review.getUid().equals(userId)) {
				return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}

			boolean res = aptReviewService.modifyAptReview(request);

			if (res) {
				return ResponseEntity.ok(review);
			} else {
				return ResponseEntity.internalServerError().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "리뷰 삭제")
	@DeleteMapping("/{no}")
	public ResponseEntity<?> removeAptReview(@PathVariable int no, @RequestParam AptReviewDto request) {

		String userId = request.getUid();

		if (no != request.getNo()) {
			return ResponseEntity.badRequest().build();
		}

		AptReviewDto review = aptReviewService.getAptReview(no);

		if (review != null) {

			if (!review.getUid().equals(userId) || review.getUid().equals("admin")) {
				return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}

			boolean res = aptReviewService.removeAptReview(no);

			if (res) {
				return ResponseEntity.noContent().build();
			} else {
				return ResponseEntity.internalServerError().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "리뷰 목록 조회")
	@GetMapping("/avg")
	public ResponseEntity<?> getAvgAptReview(@RequestParam long aptCode) {
		AptInfoDto aptInfo = aptInfoService.getAptInfo(aptCode);

		if (aptInfo != null) {
			AptReviewStatDto avg = aptReviewService.getAvgAptReview(aptCode);
			Map<String, Double> response = new HashMap<>();

			if (avg != null) {
				response.put("교육", avg.getScoreEdu());
				response.put("환경", avg.getScoreEdu());
				response.put("안전", avg.getScoreEdu());
				response.put("생활", avg.getScoreEdu());
				response.put("교통", avg.getScoreEdu());
				return ResponseEntity.ok(response);
			} else {
				response.put("교육", (double) 0);
				response.put("환경", (double) 0);
				response.put("안전", (double) 0);
				response.put("생활", (double) 0);
				response.put("교통", (double) 0);
				return ResponseEntity.ok(response);
			}

		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
