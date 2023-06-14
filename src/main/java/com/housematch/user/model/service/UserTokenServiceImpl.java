package com.housematch.user.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.housematch.interest.model.dto.InterestTypeDto;
import com.housematch.user.model.dto.UserDto;
import com.housematch.user.model.mapper.UserMapper;
import com.housematch.user.model.mapper.UserTokenMapper;

@Service
public class UserTokenServiceImpl implements UserTokenService {

	@Autowired
	private SqlSession sqlSession;

	private static final String[] typeNames = { "", "교육", "환경", "안전", "교통", "생활" };

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		if (userDto.getId() == null || userDto.getPw() == null)
			return null;

		UserDto response = sqlSession.getMapper(UserTokenMapper.class).login(userDto);
		if (response != null) {
			UserDto ans = sqlSession.getMapper(UserMapper.class).selectUserWithType(response.getId());
			for (InterestTypeDto type : ans.getTypes()) {
				type.setTypeName(typeNames[type.getType()]);
			}
			System.out.println(ans);
			return ans;
		}

		return response;
	}

	@Override
	public UserDto userInfo(String userid) {
		return sqlSession.getMapper(UserMapper.class).selectUserWithType(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(UserTokenMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) {
		return sqlSession.getMapper(UserTokenMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) {
		Map<String, String> map = new HashMap<>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(UserTokenMapper.class).deleteRefreshToken(map);
	}

}
