package com.housematch.house.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housematch.house.model.dto.AptDealRecordDto;
import com.housematch.house.model.dto.AptDealRecordStatDto;
import com.housematch.house.model.mapper.AptDealRecordMapper;
import com.housematch.util.PageNavigation;
import com.housematch.util.SizeConstant;

@Service
@Transactional
public class AptDealRecordServiceImpl implements AptDealRecordService {

	@Autowired
	private AptDealRecordMapper aptDealRecordMapper;

	@Override
	public List<AptDealRecordDto> getAptDealRecordList(long aptCode) {
		return aptDealRecordMapper.selectAptDealRecordList(aptCode);
	}

	@Override
	public List<AptDealRecordDto> getAptDealRecordListWithPage(Map<String, Object> conditions) {
		Map<String, Object> param = new HashMap<>();
		int pgNo = (int) conditions.get("pgno");
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;

		param.put("aptCode", conditions.get("aptCode"));
		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);

		return aptDealRecordMapper.selectAptDealRecordListWithPage(param);
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, Object> conditions) {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = (int) conditions.get("pgno");

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);

		int totalCount = aptDealRecordMapper.getAptDealRecordCount((long) conditions.get("aptCode"));
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

	@Override
	public Map<String, List<AptDealRecordStatDto>> getAptDealRecordMonthlyAvgByArea(long aptCode) {
		List<AptDealRecordStatDto> records = aptDealRecordMapper.selectAptDealMonthlyAvgByArea(aptCode);
		List<String> areas = aptDealRecordMapper.getAptAreas(aptCode);

		Map<String, List<AptDealRecordStatDto>> ordered = new HashMap<>();

		for (String area : areas) {
			ordered.put(area, new ArrayList<>());
		}

		for (AptDealRecordStatDto record : records) {
			ordered.get(record.getArea()).add(record);
		}

		return ordered;
	}

}
