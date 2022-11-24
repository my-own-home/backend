package com.housematch.house.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housematch.house.model.dto.AptDealJisuDto;
import com.housematch.house.model.mapper.AptDealJisuMapper;

@Service
@Transactional
public class AptDealJisuServiceImpl implements AptDealJisuService {

	@Autowired
	private AptDealJisuMapper aptDealJisuMapper;
	
	@Override
	public AptDealJisuDto getAptDealJisuList(String loc) {
		return aptDealJisuMapper.selectAptDealJisu(loc);
	}

}
