package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.MemberDto;

public interface MemberService {
	
	public boolean insertUser(MemberDto user)  throws Exception;
	public List<MemberDto> getAllUsers() throws Exception;
	public MemberDto getUserByUserId(Map<String, String> userMap)  throws Exception;
	public boolean updateUserPw(MemberDto user) throws Exception;
	public boolean deleteUser(String userId)  throws Exception;
	public MemberDto login(MemberDto userDto) throws Exception;
	public boolean idCheck(Map<String, String> resultMap)  throws Exception;
	
	/////////////////////
	
	public void saveRefreshToken(String userId, String refreshToken) throws Exception;
	public String getRefreshToken(String userId) throws Exception;
	public void deleteRefreshToken(String userid) throws Exception;

	
	
//	MemberDto login(Map<String, String> map) throws Exception;
//	
//	int idCheck(String checkId) throws Exception;
//	void registerMember(MemberDto memberDto) throws Exception;
//	
//	List<MemberDto> listMember() throws Exception;
//	MemberDto getMember(String userId) throws Exception;
//	void updateMember(MemberDto memberDto) throws Exception;
//	void deleteMember(String userId) throws Exception;
	
}
