package com.housematch.admin.model.dto;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class NoticeDto {

	private int no; // 자동 생성
	@NonNull
	private String uid;
	@NonNull
	private String subject;
	@NonNull
	private String content;
	private int hit; // 자동 생성
	private String regTime; // 자동 생성
	private String upTime; // 자동 생성

}
