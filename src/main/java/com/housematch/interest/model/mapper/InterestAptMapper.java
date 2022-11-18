package com.housematch.interest.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.interest.model.dto.InterestAptDto;

@Mapper
public interface InterestAptMapper {

	List<InterestAptDto> selectInterestAptList(String id);

	int insertInterestApt(InterestAptDto interestAptDto);

	int deleteInterestApt(InterestAptDto interestAptDto);
	
	int getTotalInterestAptCount(Map<String, Object> map);

}
