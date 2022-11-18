package com.housematch.interest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestDongDto {

	@NonNull
	private String id;
	@NonNull
	private String dongCode;
}
