package com.housematch.house.controller;

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

import com.housematch.house.model.dto.AptReviewDto;
import com.housematch.house.model.service.AptReviewService;
import com.housematch.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/apt")
@Api(value = "리뷰 Controller")
public class AptReviewController {
	
	@Autowired
	private AptReviewService aptReviewService;
	
	@ApiOperation(value = "리뷰 목록 조회")
	@GetMapping
	public ResponseEntity<?> getAptReviewList(@RequestParam(required = false) long aptCode, @RequestParam(required = false) Integer pgno) {
		Map<String, String> conditions = new HashMap<String, String>();
		

		if (pgno != null) {
			conditions.put("pgno", Integer.toString(pgno));
		} else {
			conditions.put("pgno", "1");
		}

		List<AptReviewDto> reviews = aptReviewService.getAptReviewList(aptCode);
		PageNavigation navigation = aptReviewService.makePageNavigation(conditions);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("reviews", reviews);
		response.put("navigation", navigation);

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
			return ResponseEntity.ok("공지사항이 등록되었습니다.");
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@ApiOperation(value = "리뷰 수정")
	@PutMapping("/{no}")
	public ResponseEntity<?> modifyAptReview(@PathVariable int no, @RequestBody AptReviewDto request) {
		
		String userId = request.getUid();
		AptReviewDto aptReviewDto = request;
		
		if(no != aptReviewDto.getNo()) {
			return ResponseEntity.badRequest().build();
		}
		
		AptReviewDto review = aptReviewService.getAptReview(no);

		if (review != null) {
			
			if(!review.getUid().equals(userId)) {
				return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}
			
			boolean res = aptReviewService.modifyAptReview(aptReviewDto);

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
	public ResponseEntity<?> removeAptReview(@PathVariable int no, @RequestBody AptReviewDto request) {
		
		String userId = request.getUid();
		AptReviewDto aptReviewDto = request;
		
		
		if(no != aptReviewDto.getNo()) {
			return ResponseEntity.badRequest().build();
		}
	
		AptReviewDto review = aptReviewService.getAptReview(no);
		
		if (review != null) {

			if(!review.getUid().equals(userId) || review.getUid().equals("admin")) {
				return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
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
}
