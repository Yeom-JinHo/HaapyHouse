package com.ssafy.happyhouse.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.MemberDto;

@Mapper
public interface MemberMapper {

	public int insertUser(MemberDto user)  throws Exception;
	public List<MemberDto> getAllUsers() throws Exception;
	public MemberDto getUserByUserId(Map<String, String> userMap)  throws Exception;
	public int updateUserPw(MemberDto user) throws Exception;
	public int deleteUser(String userId)  throws Exception;
	public MemberDto login(MemberDto user) throws Exception;
	public int idCheck(Map<String, String> resultMap) throws Exception;

	
	/////////////////////////////
	
	public void saveRefreshToken(Map<String, String> map) throws Exception;
	public String getRefreshToken(String userid) throws Exception;
	public void deleteRefreshToken(Map<String, String> map) throws Exception;

	
	
//	MemberDto login(Map<String, String> map) throws Exception;
//	
//	int idCheck(String checkId) throws Exception;
//	void registerMember(MemberDto memberDto) throws Exception;
//	List<MemberDto> listMember() throws Exception;
//	MemberDto getMember(String userId) throws Exception;
//	void updateMember(MemberDto memberDto) throws Exception;
//	void deleteMember(String userId) throws Exception;
	
}
