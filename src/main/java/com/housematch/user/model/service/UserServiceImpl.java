package com.housematch.user.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housematch.user.model.dto.UserDto;
import com.housematch.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDto getUser(String id) {
		return userMapper.selectUser(id);
	}

	@Override
	public boolean addUser(UserDto user) {
		return userMapper.insertUser(user) > 0;
	}

	@Override
	public boolean modifyUser(UserDto user) {
		return userMapper.updateUser(user) > 0;
	}

	@Override
	public boolean removeUser(String id) {
		return userMapper.deleteUser(id) > 0;
	}
	
	@Override
	public UserDto login(UserDto userDto) {
		if(userDto.getId() == null || userDto.getPw() == null)
			return null;
		return sqlSession.getMapper(UserMapper.class).login(userDto);
	}

}
