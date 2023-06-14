package com.housematch.interest.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housematch.interest.model.dto.InterestDongDto;
import com.housematch.interest.model.mapper.InterestDongMapper;
import com.housematch.util.PageNavigation;
import com.housematch.util.SizeConstant;

@Service
@Transactional
public class InterestDongServiceImpl implements InterestDongService {

	private InterestDongMapper interestDongMapper;

	@Autowired
	public InterestDongServiceImpl(InterestDongMapper interestDongMapper) {
		super();
		this.interestDongMapper = interestDongMapper;
	}

	@Override
	public List<InterestDongDto> getInterestDongList(Map<String, Object> conditions) {

		Map<String, Object> param = new HashMap<>();

		String id = (String) conditions.get("userId");
		int pgNo = (int) conditions.get("pgno");
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;

		param.put("id", id);
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return interestDongMapper.selectInterestDongList(param);
	}

	@Override
	public InterestDongDto getInterestDong(InterestDongDto interestDongDto) {
		return interestDongMapper.selectInterestDong(interestDongDto);
	}

	@Override
	public boolean checkDuplicateInterestDong(InterestDongDto interestDongDto) {
		InterestDongDto check = interestDongMapper.selectInterestDong(interestDongDto);

		return check != null;
	}

	@Override
	public boolean addInterestDong(InterestDongDto interestDongDto) {
		return interestDongMapper.insertInterestDong(interestDongDto) > 0;
	}

	@Override
	public boolean removeInterestDong(InterestDongDto interestDongDto) {
		return interestDongMapper.deleteInterestDong(interestDongDto) > 0;
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, Object> conditions) {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = (int) conditions.get("pgno");

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);

		Map<String, Object> param = new HashMap<>();
//		String key = map.get("key");
//		if ("userid".equals(key))
//			key = "user_id";
//		param.put("key", key == null ? "" : key);
//		param.put("word", map.get("word") == null ? "" : map.get("word"));

		int totalCount = interestDongMapper.getTotalInterestDongCount(param);
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
