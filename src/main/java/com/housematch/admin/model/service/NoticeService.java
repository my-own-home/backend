package com.housematch.admin.model.service;

import java.util.List;
import java.util.Map;

import com.housematch.admin.model.dto.NoticeDto;
import com.housematch.util.PageNavigation;

public interface NoticeService {

	List<NoticeDto> getNoticeList(Map<String, Object> conditions);

	PageNavigation makePageNavigation(Map<String, Object> conditions);

	NoticeDto getNotice(String userId, int no);

	boolean addNotice(NoticeDto noticeDto);

	boolean modifyNotice(NoticeDto noticeDto);

	boolean removeNotice(int no);

}
