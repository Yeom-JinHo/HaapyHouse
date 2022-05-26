package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="회원정보", description = "회원의 아이디, ㄴㅇㄹㄴㅁ")
public class MemberDto {

	@ApiModelProperty(value="sdfd")
	private String userId;
	private String nickName;
	private String userPwd;
	private String email;
	private String joinDate;
	private String profileImg;
	private String refreshToken;
	private String socialType;
	@Override
	public String toString() {
		return "MemberDto [userId=" + userId + ", nickName=" + nickName + ", userPwd=" + userPwd + ", email=" + email
				+ ", joinDate=" + joinDate + ", profileImg=" + profileImg + ", refreshToken=" + refreshToken
				+ ", socialType=" + socialType + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getSocialType() {
		return socialType;
	}
	public void setSocialType(String socialType) {
		this.socialType = socialType;
	}

	

}
