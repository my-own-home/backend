package com.housematch.admin.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.admin.model.dto.QuestionReplyDto;

@Mapper
public interface QuestionReplyMapper {

	List<QuestionReplyDto> selectQuestionReplyList(int qNo); // 질문 번호

	int insertQuestionReply(QuestionReplyDto questionReplyDto);

	int modifyQuestionReply(QuestionReplyDto questionReplyDto);

	int deleteQuestionReply(int rNo); // 질문 대답 번호

}
