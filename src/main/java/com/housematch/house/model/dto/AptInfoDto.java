package com.housematch.house.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class AptInfoDto {

	private long aptCode;
	@NonNull
	private String aptName;
	@NonNull
	private String dongCode;
	private int buildYear;
	private String fullRoadAddress;
	private String fullJibunAddress;
	private String lat;
	private String lng;
}
