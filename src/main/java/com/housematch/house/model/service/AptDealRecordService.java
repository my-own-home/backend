package com.housematch.house.model.service;

import java.util.List;

import com.housematch.house.model.dto.AptDealRecordDto;

public interface AptDealRecordService {

	List<AptDealRecordDto> getAptDealRecordList(long aptCode);	
	
}
