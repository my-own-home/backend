package com.housematch.house.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housematch.house.model.dto.AptDetailDto;
import com.housematch.house.model.mapper.AptDetailMapper;

@Service
@Transactional
public class AptDetailServiceImpl implements AptDetailService {

	@Autowired
	private AptDetailMapper aptDetailMapper;
	
	@Override
	public AptDetailDto getAptDetail(long aptCode) {
		return aptDetailMapper.selectAptDetail(aptCode);
	}

}
