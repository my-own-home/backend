package com.housematch.interest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class InterestAptDto {

	@NonNull
	private String id;
	@NonNull
	private String aptCode;
	private String regTime;

}
