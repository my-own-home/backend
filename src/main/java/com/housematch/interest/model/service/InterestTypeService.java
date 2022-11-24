package com.housematch.interest.model.service;

import java.util.List;

import com.housematch.interest.model.dto.InterestTypeDto;

public interface InterestTypeService {

	List<InterestTypeDto> getInterestTypeList(String userId);

}
