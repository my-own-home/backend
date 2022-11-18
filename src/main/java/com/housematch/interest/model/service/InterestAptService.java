package com.housematch.interest.model.service;

import java.util.List;
import java.util.Map;

import com.housematch.interest.model.dto.InterestAptDto;
import com.housematch.util.PageNavigation;

public interface InterestAptService {
	
	List<InterestAptDto> getInterestAptList(String id);

	boolean addInterestApt(InterestAptDto interestAptDto);

	boolean removeInterestApt(InterestAptDto interestAptDto);

	PageNavigation makePageNavigation(Map<String, String> map);
}
