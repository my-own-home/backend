package com.housematch.house.model.dto;

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
	private String uid;
	
	private int	score;
	private int scoreSafety;
	private int scoreTransport;
	private int	scoreNature;
	private int	scoreEdu;
	private int	scoreLife;
	
	@NonNull
	private String content;
	private String regTime;
	private String upTime;
	private int likeCount;
	
}
