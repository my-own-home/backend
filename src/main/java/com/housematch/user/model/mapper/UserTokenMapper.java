package com.housematch.user.model.mapper;

import java.util.Map;

import com.housematch.user.model.dto.UserDto;

public interface UserTokenMapper {

	UserDto login(UserDto userDto);

	UserDto selectUser(String userid);

	void saveRefreshToken(Map<String, String> map);

	Object getRefreshToken(String userid);

	void deleteRefreshToken(Map<String, String> map);
}
