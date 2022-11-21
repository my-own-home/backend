package com.housematch.user.model.service;

import com.housematch.user.model.dto.UserDto;

public interface UserService {

	UserDto getUser(String id);

	boolean addUser(UserDto user);

	boolean modifyUser(UserDto user);

	boolean removeUser(String id);
	
	UserDto login(UserDto userDto);

}
