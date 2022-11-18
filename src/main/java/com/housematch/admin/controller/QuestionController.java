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

import com.housematch.admin.model.dto.QuestionDto;
import com.housematch.admin.model.service.QuestionService;
import com.housematch.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/qnas")
@Api(value = "q&a 질문 Controller")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@ApiOperation(value = "qna 질문 목록 조회")
	@GetMapping
	public ResponseEntity<?> getQuestionList(@RequestParam(required = false) Integer pgno){
		
		Map<String, Object> conditions = new HashMap<String, Object>();
		
		if (pgno != null) {
			conditions.put("pgno", pgno);
		} else {
			conditions.put("pgno", 1);
		}
		
		List<QuestionDto> questions = questionService.getQuestionList(conditions);
		PageNavigation navigation = questionService.makePageNavigation(conditions);
		
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("questions", questions);
		response.put("navigation", navigation);
		
		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "qna 질문 상세 조회")
	@GetMapping("/{no}")
	public ResponseEntity<?> getQuestion(@PathVariable int no) {
		
		QuestionDto question = questionService.getQuestion(no);
		
		if (question != null)
			return ResponseEntity.ok(question);
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "qna 질문 등록")
	@PostMapping
	public ResponseEntity<?> addQuestion(@RequestBody QuestionDto questionDto) {
		
		boolean res = questionService.addQuestion(questionDto);

		if (res) {
			return ResponseEntity.ok("질문이 등록되었습니다.");
		} else {
			return ResponseEntity.internalServerError().build();
		}
		
	}
	
	@ApiOperation(value = "qna 질문 수정")
	@PutMapping("/{no}")
	public ResponseEntity<?> modifyQuestion(@PathVariable int no, @RequestBody QuestionDto request) {
		
		String userId = request.getUid();
		QuestionDto questionDto = request;
		
		if(no != questionDto.getNo()) {
			return ResponseEntity.badRequest().build();
		}
		QuestionDto question = questionService.getQuestion(no);

		if (question != null) {
			
			if(!question.getUid().equals(userId)) {
				return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}
			
			boolean res = questionService.modifyQuestion(questionDto);

			if (res) {
				return ResponseEntity.ok(question);
			} else {
				return ResponseEntity.internalServerError().build();
			}
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "qna 질문 삭제")
	@DeleteMapping("/{no}")
	public ResponseEntity<?> removeAptReview(@PathVariable int no, @RequestBody QuestionDto request) {
		
		String userId = request.getUid();
		QuestionDto questionDto = request;
		
		System.out.println(no);
		System.out.println(questionDto.getNo());
		if(no != questionDto.getNo()) {
			return ResponseEntity.badRequest().build();
		}
	
		QuestionDto question = questionService.getQuestion(no);
		
		if (question != null) {

			if(!question.getUid().equals(userId) || question.getUid().equals("admin")) {
				return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}
			
			boolean res = questionService.removeQuestion(no);

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
/*


@Override
public boolean modifyQuestion(QuestionDto questionDto) {
	return questionMapper.updateQuestion(questionDto) > 0;
}

@Override
public boolean removeQuestion(int no) {
	return questionMapper.deleteQuestion(no) > 0;
}
 */
 