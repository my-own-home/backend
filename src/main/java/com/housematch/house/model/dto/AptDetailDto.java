package com.housematch.house.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AptDetailDto {

	private long aptCode;

	private String corridorType;
	private String heatType;

	private int dongCount;
	private int householdCount;

	private String constructorName;
	private String implementerName;

	private String approveDate;
	private String recycleType;

	private String adminTel;
	private String adminFax;

	private int cctvCount;

	private int groundParkCount;
	private int underParkCount;
	private int totalParkCount;

	private String facilitiesStr;

}
