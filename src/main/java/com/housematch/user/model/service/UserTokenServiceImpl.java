package com.housematch.user.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housematch.user.model.dto.UserDto;
import com.housematch.user.model.mapper.UserMapper;
import com.housematch.user.model.mapper.UserTokenMapper;

@Service
public class UserTokenServiceImpl implements UserTokenService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		if (userDto.getId() == null || userDto.getPw() == null)
			return null;
		System.out.println("UserTokenService login");
		System.out.println(userDto);
		
		return sqlSession.getMapper(UserTokenMapper.class).login(userDto);
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).selectUserWithType(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(UserTokenMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return sqlSession.getMapper(UserTokenMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(UserTokenMapper.class).deleteRefreshToken(map);
	}

}

