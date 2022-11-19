package com.housematch.interest.model.service;

import java.util.List;
import java.util.Map;

import com.housematch.interest.model.dto.InterestAptDto;
import com.housematch.interest.model.dto.InterestDongDto;
import com.housematch.util.PageNavigation;

public interface InterestDongService {
	
	List<InterestDongDto> getInterestDongList(Map<String, Object> conditions);

	InterestDongDto getInterestDong(InterestDongDto interestDongDto);
	
	boolean checkDuplicateInterestDong(InterestDongDto interestDongDto);

	boolean addInterestDong(InterestDongDto interestDongDto);

	boolean removeInterestDong(InterestDongDto interestDongDto);

	PageNavigation makePageNavigation(Map<String, Object> map);
	
}
