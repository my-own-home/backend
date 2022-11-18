package com.housematch.admin.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.admin.model.dto.QuestionDto;

@Mapper
public interface QuestionMapper {

	List<QuestionDto> selectQuestionList();

	QuestionDto selectQuestion(int no);

	int insertQuestion(QuestionDto questionDto);

	int updateQuestion(QuestionDto questionDto);

	int deleteQuestion(int no);
	
	int getTotalQustionCount(Map<String, Object> map);
}
