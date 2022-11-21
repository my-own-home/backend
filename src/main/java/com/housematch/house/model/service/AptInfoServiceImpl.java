package com.housematch.house.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housematch.house.model.dto.AptInfoDto;
import com.housematch.house.model.mapper.AptInfoMapper;

@Service
@Transactional
public class AptInfoServiceImpl implements AptInfoService {

	@Autowired
	private AptInfoMapper aptInfoMapper;

	@Override
	public List<AptInfoDto> getAptListByDong(String dongCode) {
		return aptInfoMapper.selectAptListByDong(dongCode);
	}

	@Override
	public AptInfoDto getAptInfo(long aptCode) {
		return aptInfoMapper.getAptInfo(aptCode);
	}

}
