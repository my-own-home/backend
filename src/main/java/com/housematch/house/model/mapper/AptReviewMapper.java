package com.housematch.house.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.house.model.dto.AptReviewDto;

@Mapper
public interface AptReviewMapper {

	List<AptReviewDto> selectAptReviewList(long aptCode);

	AptReviewDto selectAptReview(int no);

	int insertAptReview(AptReviewDto aptReviewDto);

	int updateAptReview(AptReviewDto aptReviewDto);

	int deleteAptReview(int no);

}