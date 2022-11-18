package com.housematch.house.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class LocationDto {
	
	@NonNull
	private String dongCode;
	private String sido;
	private String gugun;
	private String dong;
	private String lat;
	private String lng;
}
