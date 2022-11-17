package com.housematch.interest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestAptDto {

	@NonNull
	private String id;
	@NonNull
	private String aptCode;
}
