package com.housematch.house.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AptReviewStatDto {

	private long aptCode;
	private double score;
	private double scoreSafety;
	private double scoreTransport;
	private double scoreNature;
	private double scoreEdu;
	private double scoreLife;

}
