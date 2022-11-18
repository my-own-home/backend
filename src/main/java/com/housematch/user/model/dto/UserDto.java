package com.housematch.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDto {

	@NonNull
	private String id;
	@NonNull
	private String pw;
	@NonNull
	private String name;
	@NonNull
	private String email;
	private String regTime; // 자동 생성

}
