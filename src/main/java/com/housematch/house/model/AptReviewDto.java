package com.housematch.house.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AptReviewDto {
	
	private int	no;
	private long aptCode;
	@NonNull
	private String userId;
	private int	score;
	private int scoreTransport;
	private int	scoreNature;
	private int	scoreEdu;
	private int	scoreLife;
	@NonNull
	private String content;
	private String regTime;
	private String updateTime;
	private String delTime;
	
}
