package com.housematch.admin.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.admin.model.dto.NoticeDto;

@Mapper
public interface NoticeMapper {
	
	List<NoticeDto> selectNoticeList();
	
	NoticeDto selectNotice(int no);
	
	int insertNotice(NoticeDto noticeDto);
	
	int updateNotice(NoticeDto noticeDto);

	int updateHit(int hit);
	
	int deleteNotice(int no);
	
}
 