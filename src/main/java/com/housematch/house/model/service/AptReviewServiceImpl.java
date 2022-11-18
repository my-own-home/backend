package com.housematch.house.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housematch.house.model.dto.AptReviewDto;
import com.housematch.house.model.mapper.AptReviewMapper;
import com.housematch.util.PageNavigation;
import com.housematch.util.SizeConstant;

@Service
@Transactional
public class AptReviewServiceImpl implements AptReviewService {
	
	private AptReviewMapper aptReviewMapper;
	
	@Autowired
	public AptReviewServiceImpl(AptReviewMapper aptReviewMapper) {
		this.aptReviewMapper = aptReviewMapper;
	}
	
	@Override
	public List<AptReviewDto> getAptReviewList(long aptCode) {
		return aptReviewMapper.selectAptReviewList(aptCode);
	}

	@Override
	public AptReviewDto getAptReview(int no) {

		return aptReviewMapper.selectAptReview(no);
	}

	@Override
	public boolean addAptReview(AptReviewDto aptReviewDto) {
		return aptReviewMapper.insertAptReview(aptReviewDto) > 0;
	}

	@Override
	public boolean modifyAptReview(AptReviewDto aptReviewDto) {
		return aptReviewMapper.updateAptReview(aptReviewDto) > 0;
	}

	@Override
	public boolean removeAptReview(int no) {
		return aptReviewMapper.deleteAptReview(no) > 0;
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
//		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "user_id";
//		param.put("key", key == null ? "" : key);
//		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = aptReviewMapper.getTotalReviewCount(param);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount - 1) / sizePerPage + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();

		return pageNavigation;
	}

}
