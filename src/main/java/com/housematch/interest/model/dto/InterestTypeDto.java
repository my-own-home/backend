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
public class InterestTypeDto {

	@NonNull
	private String id;
	@NonNull
	private Integer type;
	private String typeName;
	private Integer priority;

}
