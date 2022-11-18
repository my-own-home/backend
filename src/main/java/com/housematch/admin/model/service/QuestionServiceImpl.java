package com.housematch.admin.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housematch.admin.model.dto.QuestionDto;
import com.housematch.admin.model.mapper.QuestionMapper;
import com.housematch.util.PageNavigation;
import com.housematch.util.SizeConstant;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public List<QuestionDto> getQuestionList() {
		return questionMapper.selectQuestionList();
	}

	@Override
	public QuestionDto getQuestion(int no) {
		return questionMapper.selectQuestion(no);
	}

	@Override
	public boolean addQuestion(QuestionDto questionDto) {
		return questionMapper.insertQuestion(questionDto) > 0;
	}

	@Override
	public boolean modifyQuestion(QuestionDto questionDto) {
		return questionMapper.updateQuestion(questionDto) > 0;
	}

	@Override
	public boolean removeQuestion(int no) {
		return questionMapper.deleteQuestion(no) > 0;
	}

	@Override
	public PageNavigation makePageNavigation(Map<String, String> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();

		int naviSize = SizeConstant.NAVIGATION_SIZE;
		int sizePerPage = SizeConstant.LIST_SIZE;
		int currentPage = Integer.parseInt(map.get("pgno"));

		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
		if ("userid".equals(key))
			key = "user_id";
		param.put("key", key == null ? "" : key);
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int totalCount = questionMapper.getTotalQustionCount(param);
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
