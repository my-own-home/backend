package com.housematch.house.model.service;

import java.util.List;
import java.util.Map;

import com.housematch.house.model.dto.AptDealRecordDto;
import com.housematch.house.model.dto.AptDealRecordStatDto;
import com.housematch.util.PageNavigation;

public interface AptDealRecordService {

	List<AptDealRecordDto> getAptDealRecordList(long aptCode);

	List<AptDealRecordDto> getAptDealRecordListWithPage(Map<String, Object> conditions);

	PageNavigation makePageNavigation(Map<String, Object> map);

	Map<String, List<AptDealRecordStatDto>> getAptDealRecordMonthlyAvgByArea(long aptCode);
}
