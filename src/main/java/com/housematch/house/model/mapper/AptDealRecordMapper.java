package com.housematch.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.house.model.dto.AptDealRecordDto;

@Mapper
public interface AptDealRecordMapper {
	
	List<AptDealRecordDto> selectAptDealRecordList(long aptCode);
}
