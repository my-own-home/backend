package com.housematch.house.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AptDealRecordDto {

	private long id;
	private long apt_code;
	private String deal_amount;
	private int deal_year;
	private int deal_month;
	private int deal_day;
	private String area;
	private String floor;
	private String cancel_deal_type;

}
