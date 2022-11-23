package com.housematch.house.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housematch.house.model.dto.LocationDto;
import com.housematch.house.model.mapper.LocationMapper;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {
	
	private LocationMapper locationMapper;

	@Autowired
	public LocationServiceImpl(LocationMapper locationMapper) {
		this.locationMapper = locationMapper;
	}

	@Override
	public LocationDto getDong(String dongCode) {
		return locationMapper.selectDong(dongCode);
	}
	
	@Override
	public LocationDto getDongDetail(String dongCode) {
		return locationMapper.selectDongDetail(dongCode);
	}

	@Override
	public List<LocationDto> getDongList(String dongCode) {
		return locationMapper.selectDongList(dongCode);
	}

	@Override
	public List<LocationDto> getGugunList(String dongCode) {
		return locationMapper.selectGugunList(dongCode);
	}

	@Override
	public List<LocationDto> getSidoList(String dongCode) {
		return locationMapper.selectSidoList(dongCode);
	}

}
