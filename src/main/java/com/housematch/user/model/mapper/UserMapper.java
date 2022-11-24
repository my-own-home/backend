package com.housematch.user.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.housematch.user.model.dto.UserDto;

@Mapper
public interface UserMapper {
	
	UserDto selectUser(String id);
	
	UserDto selectUserWithType(String id);

	int insertUser(UserDto user);
	
	int updateUser(UserDto user);
	
	int deleteUser(String id);
	
	UserDto login(UserDto userDto);
}
