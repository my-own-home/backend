package com.housematch.admin.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.admin.model.dto.QuestionDto;

@Mapper
public interface QuestionMapper {

	List<QuestionDto> selectQuestionList();

	QuestionDto selectQuestion(int no);

	int insertQuestion(QuestionDto questionDto);

	int updateQuestion(QuestionDto questionDto);

	int deleteQuestion(int no);
}
