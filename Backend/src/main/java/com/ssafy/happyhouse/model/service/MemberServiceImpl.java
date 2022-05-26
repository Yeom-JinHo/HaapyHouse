package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.mapper.MemberMapper;


@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public boolean insertUser(MemberDto user) throws Exception {
		return memberMapper.insertUser(user) == 1;
	}
	@Override
	public List<MemberDto> getAllUsers() throws Exception{
		return memberMapper.getAllUsers();
	}
	@Override
	public MemberDto getUserByUserId(Map<String, String> userMap) throws Exception {
		return memberMapper.getUserByUserId(userMap);
	}
	@Override
	public boolean updateUserPw(MemberDto user) throws Exception {
		return memberMapper.updateUserPw(user) == 1;
	}
	@Override
	public boolean deleteUser(String userId) throws Exception {
		return memberMapper.deleteUser(userId) == 1;
	}
	
	@Override
	public boolean idCheck(Map<String, String> resultMap) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.idCheck(resultMap) == 0;
	}

	@Override
	public MemberDto login(MemberDto userDto) throws Exception {
		// TODO Auto-generated method stub
		if(userDto.getUserId() == null || userDto.getUserPwd() == null) {
			return null;
		}
		return memberMapper.login(userDto);
	}
	
	
	
	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userid);
		map.put("refreshToken", refreshToken);
		memberMapper.saveRefreshToken(map);
		
	}

	@Override
	public String getRefreshToken(String userid) throws Exception {
		// TODO Auto-generated method stub
		return memberMapper.getRefreshToken(userid);
	}

	@Override
	public void deleteRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userid);
		map.put("refreshToken", null);
		memberMapper.deleteRefreshToken(map);
		
	}


}
