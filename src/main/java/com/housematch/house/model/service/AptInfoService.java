package com.housematch.house.model.service;

import java.util.List;

import com.housematch.house.model.dto.AptInfoDto;

public interface AptInfoService {

	List<AptInfoDto> getAptListByDong(String dongCode);

}
