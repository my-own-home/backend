package com.housematch.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.house.model.dto.AptInfoDto;

@Mapper
public interface AptInfoMapper {

	List<AptInfoDto> selectAptListByDong(String dongCode);
	
}
