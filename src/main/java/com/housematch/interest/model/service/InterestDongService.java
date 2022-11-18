package com.housematch.interest.model.service;

import java.util.List;
import java.util.Map;

import com.housematch.interest.model.dto.InterestDongDto;
import com.housematch.util.PageNavigation;

public interface InterestDongService {
	
	List<InterestDongDto> getInterestDongList(String id);

	boolean addInterestDong(InterestDongDto interestDongDto);

	boolean removeInterestDong(InterestDongDto interestDongDto);

	PageNavigation makePageNavigation(Map<String, String> map);
	
}
