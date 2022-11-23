package com.housematch.house.model.service;

import java.util.List;

import com.housematch.house.model.dto.LocationDto;

public interface LocationService {

	LocationDto getDong(String dongCode);
	
	LocationDto getDongDetail(String dongCode);

	List<LocationDto> getDongList(String dongCode);

	List<LocationDto> getGugunList(String dongCode);

	List<LocationDto> getSidoList(String dongCode);
	
}
