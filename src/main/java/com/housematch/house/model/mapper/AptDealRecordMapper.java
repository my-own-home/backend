package com.housematch.house.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.house.model.dto.AptDealRecordDto;
import com.housematch.house.model.dto.AptDealRecordStatDto;

@Mapper
public interface AptDealRecordMapper {
	
	List<AptDealRecordDto> selectAptDealRecordList(long aptCode);
	
	List<AptDealRecordDto> selectAptDealRecordListWithPage(Map<String, Object> param);

	int getAptDealRecordCount(long aptCode);
	
	List<String> getAptAreas(long aptCode);
	
	List<AptDealRecordStatDto> selectAptDealMonthlyAvgByArea(long aptCode);
	
}
