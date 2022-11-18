package com.housematch.admin.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.housematch.admin.model.dto.QuestionReplyDto;
import com.housematch.admin.model.mapper.QuestionReplyMapper;

@Service
@Transactional
public class QuestionReplyServiceImpl implements QuestionReplyService {

	@Autowired
	private QuestionReplyMapper questionReplyMapper;

	@Override
	public List<QuestionReplyDto> getQuestionReplyList(int qNo) {
		return questionReplyMapper.selectQuestionReplyList(qNo);
	}
	
	@Override
	public QuestionReplyDto getQuestionReply(int qNo) {
		return questionReplyMapper.selectQuestionReply(qNo);
	}

	@Override
	public boolean addQuestionReply(QuestionReplyDto questionReplyDto) {
		return questionReplyMapper.insertQuestionReply(questionReplyDto) > 0;
	}

	@Override
	public boolean modifyQuestionReply(QuestionReplyDto questionReplyDto) {
		return questionReplyMapper.updateQuestionReply(questionReplyDto) > 0;
	}

	@Override
	public boolean removeQuestionReply(int rNo) {
		return questionReplyMapper.deleteQuestionReply(rNo) > 0;
	}

}
