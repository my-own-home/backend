package com.housematch.interest.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housematch.interest.model.dto.InterestTypeDto;
import com.housematch.interest.model.mapper.InterestTypeMapper;

@Service
public class InterestTypeServiceImpl implements InterestTypeService {

	@Autowired
	private InterestTypeMapper interestTypeMapper;

	private static String[] typeNames = { "", "교육", "환경", "안전", "교통", "생활" };

	@Override
	public List<InterestTypeDto> getInterestTypeList(String userId) {

		List<InterestTypeDto> list = interestTypeMapper.selectInterestTypes(userId);

		for (InterestTypeDto type : list) {
			type.setTypeName(typeNames[type.getType()]);
		}

		return list;
	}

}
