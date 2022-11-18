package com.housematch.house.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housematch.house.model.dto.AptDealRecordDto;
import com.housematch.house.model.mapper.AptDealRecordMapper;

@Service
@Transactional
public class AptDealRecordServiceImpl implements AptDealRecordService {

	@Autowired
	private AptDealRecordMapper aptDealRecordMapper;
	
	@Override
	public List<AptDealRecordDto> getAptDealRecordList(long aptCode) {
		return aptDealRecordMapper.selectAptDealRecordList(aptCode);
	}

}
