package com.housematch.interest.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.interest.model.dto.InterestTypeDto;

@Mapper
public interface InterestTypeMapper {

	List<InterestTypeDto> selectInterestTypes(String userId);
}
