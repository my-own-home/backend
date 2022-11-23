package com.housematch.house.model.service;

import java.util.List;
import java.util.Map;

import com.housematch.house.model.dto.AptReviewDto;
import com.housematch.house.model.dto.AptReviewStatDto;
import com.housematch.util.PageNavigation;

public interface AptReviewService {
	
	List<AptReviewDto> getAptReviewList(long aptCode);
	
	List<AptReviewDto> getUserReviewList(String uid);

	AptReviewDto getAptReview(int no);

	boolean addAptReview(AptReviewDto aptReviewDto);

	boolean modifyAptReview(AptReviewDto aptReviewDto);

	boolean removeAptReview(int no);

	PageNavigation makePageNavigation(Map<String, String> map);

	AptReviewStatDto getAvgAptReview(long aptCode);

}
