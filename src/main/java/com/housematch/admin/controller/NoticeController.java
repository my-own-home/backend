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

import com.housematch.admin.model.dto.NoticeDto;
import com.housematch.admin.model.service.NoticeService;
import com.housematch.util.PageNavigation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/notices")
@Api(value = "공지사항 Controller")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@ApiOperation(value = "공지사항 목록 조회")
	@GetMapping
	public ResponseEntity<?> getNoticeList(@RequestParam(required = false) String subject,
			@RequestParam(required = false) Integer pgno) {

		Map<String, Object> conditions = new HashMap<String, Object>();

		if (subject != null) {
			conditions.put("word", subject);
		}

		if (pgno != null) {
			conditions.put("pgno", pgno);
		} else {
			conditions.put("pgno", 1);
		}

		List<NoticeDto> notices = noticeService.getNoticeList(conditions);
		PageNavigation navigation = noticeService.makePageNavigation(conditions);

		Map<String, Object> response = new HashMap<String, Object>();
		response.put("notices", notices);
		response.put("navigation", navigation);

		return ResponseEntity.ok(response);
	}

	@ApiOperation(value = "공지사항 상세 조회")
	@GetMapping("/{no}")
	public ResponseEntity<?> getNotice(@PathVariable int no, @RequestBody String userId) {

		NoticeDto notice = noticeService.getNotice(userId, no);

		if (notice != null)
			return ResponseEntity.ok(notice);
		else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "공지사항 추가")
	@PostMapping
	public ResponseEntity<?> addNotice(@RequestBody NoticeDto noticeDto) {

		boolean res = noticeService.addNotice(noticeDto);

		if (res) {
			return ResponseEntity.ok("공지사항이 등록되었습니다.");
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@ApiOperation(value = "공지사항 수정")
	@PutMapping("/{no}")
	public ResponseEntity<?> modifyNotice(@PathVariable int no, @RequestBody Map<String, Object> request) {

		String userId = (String) request.get("userId");
		NoticeDto noticeDto = (NoticeDto) request.get("notice");

		if (no != noticeDto.getNo()) {
			return ResponseEntity.badRequest().build();
		}

		NoticeDto notice = noticeService.getNotice(userId, noticeDto.getNo());

		if (notice != null) {

			if (!notice.getUid().equals(userId)) {
				return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}

			boolean res = noticeService.modifyNotice(noticeDto);

			if (res) {
				return ResponseEntity.ok(notice);
			} else {
				return ResponseEntity.internalServerError().build();
			}

		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@ApiOperation(value = "공지사항 삭제")
	@DeleteMapping("/{no}")
	public ResponseEntity<?> removeNotice(@PathVariable int no, @RequestBody String userId) {

		NoticeDto notice = noticeService.getNotice(userId, no);

		if (notice != null) {

			if (!notice.getUid().equals(userId)) {
				return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
			}

			boolean res = noticeService.removeNotice(no);

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
