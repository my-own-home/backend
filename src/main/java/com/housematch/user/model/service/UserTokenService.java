package com.housematch.user.model.service;

import com.housematch.user.model.dto.UserDto;

public interface UserTokenService {

	UserDto login(UserDto userDto) throws Exception;

	UserDto userInfo(String userid) throws Exception;

	void saveRefreshToken(String userid, String refreshToken) throws Exception;

	Object getRefreshToken(String userid) throws Exception;

	void deleRefreshToken(String userid) throws Exception;

}
