package com.housematch.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.house.model.dto.LocationDto;

@Mapper
public interface LocationMapper {

	LocationDto selectDong(String dongCode);
	
	List<LocationDto> selectDongList(String dongCode);

	List<LocationDto> selectGugunList(String dongCode);

	List<LocationDto> selectSidoList(String dongCode);

}
