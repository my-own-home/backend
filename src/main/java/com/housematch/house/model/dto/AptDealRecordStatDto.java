package com.housematch.house.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AptDealRecordStatDto {

	private long aptCode;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private String area;
	private String min;
	private String max;
}
