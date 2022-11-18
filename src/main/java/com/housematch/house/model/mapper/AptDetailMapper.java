package com.housematch.house.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.house.model.dto.AptDetailDto;

@Mapper
public interface AptDetailMapper {
	
	AptDetailDto selectAptDetail(long aptCode);
	
}
