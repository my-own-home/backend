package com.housematch.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housematch.admin.model.dto.NoticeDto;
import com.housematch.admin.model.mapper.NoticeMapper;
import com.housematch.util.PageNavigation;
import com.housematch.util.SizeConstant;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<NoticeDto> getNoticeList(Map<String, Object> conditions) {

		Map<String, Object> param = new HashMap<>();
		param.put("word", conditions.get("word") == null ? "" : conditions.get("word"));

		int pgNo = (int) conditions.get("pgno");
		int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;

		param.put("start", start);
		param.put("listsize", SizeConstant.LIST_SIZE);
		
		return noticeMapper.selectNoticeList(param);
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
		param.put("word", conditions.get("word") == null ? "" : conditions.get("word"));

		int totalCount = noticeMapper.getTotalNoticeCount(param);
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
	public NoticeDto getNotice(String userId, int no) {

		NoticeDto notice = noticeMapper.selectNotice(no);

		if (notice != null && !notice.getUid().equals(userId))
			noticeMapper.updateHit(no);

		return notice;
	}

	@Override
	public boolean addNotice(NoticeDto noticeDto) {
		return noticeMapper.insertNotice(noticeDto) > 0;
	}

	@Override
	public boolean modifyNotice(NoticeDto noticeDto) {
		return noticeMapper.updateNotice(noticeDto) > 0;
	}

	@Override
	public boolean removeNotice(int no) {
		return noticeMapper.deleteNotice(no) > 0;
	}

}
