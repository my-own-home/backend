package com.housematch.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.admin.model.dto.NoticeDto;

@Mapper
public interface NoticeMapper {

	List<NoticeDto> selectNoticeList(Map<String, Object> param);

	int getTotalNoticeCount(Map<String, Object> map);

	NoticeDto selectNotice(int no);

	int insertNotice(NoticeDto noticeDto);

	int updateNotice(NoticeDto noticeDto);

	int updateHit(int no);

	int deleteNotice(int no);

}
