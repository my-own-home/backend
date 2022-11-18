package com.housematch.admin.model.service;

import java.util.List;

import com.housematch.admin.model.dto.QuestionReplyDto;

public interface QuestionReplyService {
	
	List<QuestionReplyDto> getQuestionReplyList(int qNo); 

	boolean addQuestionReply(QuestionReplyDto questionReplyDto);

	boolean modifyQuestionReply(QuestionReplyDto questionReplyDto);

	boolean removeQuestionReply(int rNo); 
}
