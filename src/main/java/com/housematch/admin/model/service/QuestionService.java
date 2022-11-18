package com.housematch.admin.model.service;

import java.util.List;
import java.util.Map;

import com.housematch.admin.model.dto.QuestionDto;
import com.housematch.util.PageNavigation;

public interface QuestionService {

	List<QuestionDto> getQuestionList();

	QuestionDto getQuestion(int no);

	boolean addQuestion(QuestionDto questionDto);

	boolean modifyQuestion(QuestionDto questionDto);

	boolean removeQuestion(int no);

	PageNavigation makePageNavigation(Map<String, String> map) throws Exception;
	
}
