package com.housematch.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.house.model.dto.AptDealJisuDto;

@Mapper
public interface AptDealJisuMapper {

	AptDealJisuDto selectAptDealJisu(String loc);
	
}
