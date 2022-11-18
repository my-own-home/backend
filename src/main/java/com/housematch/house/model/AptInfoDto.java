package com.housematch.house.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AptInfoDto {

	private long aptCode;
	private String aptName;
	private String dongCode;
	private int buildYear;
	private String fullRoadAddress;
	private String fullJibunAddress;
	private String lat, lng;

}
