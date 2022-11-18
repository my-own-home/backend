package com.housematch.admin.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.housematch.admin.model.dto.QuestionReplyDto;
import com.housematch.admin.model.service.QuestionReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/qnas/reply")
@Api(value = "q&a 대답 Controller")
public class QuestionReplyController {
	
	@Autowired
	private QuestionReplyService questionReplyService;
	
	@ApiOperation(value = "q&a 대답 조회")
	@GetMapping
	public ResponseEntity<?> getquestionReplyList(@RequestParam(required = true) int qNo) {

		List<QuestionReplyDto> replies = questionReplyService.getQuestionReplyList(qNo);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("replies", replies);

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "q&a 대답 한 개 조회")
	@GetMapping("/{qNo}")
	public ResponseEntity<?> getquestionReply(@PathVariable int qNo) {

		QuestionReplyDto reply = questionReplyService.getQuestionReply(qNo);

		if (reply != null)
			return ResponseEntity.ok(reply);
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "q&a 대답 등록")
	@PostMapping
	public ResponseEntity<?> addQuestionReply(@RequestBody QuestionReplyDto questionReplyDto) {

		boolean res = questionReplyService.addQuestionReply(questionReplyDto);

		if (res) {
			return ResponseEntity.ok("답변이 등록되었습니다.");
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}
	

	@ApiOperation(value = "q&a 대답 수정")
	@PutMapping("/{qNo}")
	public ResponseEntity<?> modifyAptReview(@PathVariable int qNo, @RequestBody QuestionReplyDto request) {
		
		String userId = request.getUid();
		QuestionReplyDto questionReplyDto = request;
		
		if(qNo != questionReplyDto.getQNo()) {
			return ResponseEntity.badRequest().build();
		}
		
		QuestionReplyDto reply = questionReplyService.getQuestionReply(qNo);

		if (reply != null) {
			
			if(!reply.getUid().equals(userId)) {
				return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}
			
			boolean res = questionReplyService.modifyQuestionReply(questionReplyDto);

			if (res) {
				return ResponseEntity.ok(reply);
			} else {
				return ResponseEntity.internalServerError().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "q&a 대답 삭제")
	@DeleteMapping("/{qNo}")
	public ResponseEntity<?> removeQuestionReply(@PathVariable int qNo, @RequestBody QuestionReplyDto request) {
		
		String userId = request.getUid();
		QuestionReplyDto questionReplyDto = request;
		
		if(qNo != questionReplyDto.getQNo()) {
			return ResponseEntity.badRequest().build();
		}
		
		QuestionReplyDto reply = questionReplyService.getQuestionReply(qNo);

		if (reply != null) {
			
			if(!reply.getUid().equals(userId)) {
				return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}
			
			boolean res = questionReplyService.removeQuestionReply(reply.getRNo());

			if (res) {
				return ResponseEntity.ok(reply);
			} else {
				return ResponseEntity.internalServerError().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
