package com.housematch.admin.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class QuestionReplyDto {
	
	private int rNo; // 자동 생성
	private int qNo; // 자동 가져옴
	@NonNull
	private String uid;
	@NonNull
	private String content;
	private String regTime; // 자동 생성
	private String upTime; // 자동 생성

}
