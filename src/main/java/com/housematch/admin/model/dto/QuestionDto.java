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
public class QuestionDto {

	private int no; // 자동 생성
	@NonNull
	private String uid;
	@NonNull
	private String subject;
	private String content;
	private String regTime; // 자동 생성
	private String upTime; // 자동 생성

}
