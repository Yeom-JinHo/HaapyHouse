package com.ssafy.happyhouse.model;

public class NoticeDto {
	

	private int no;
	private String title;
	private String userId;
	private String nickName;
	private String socialType;
	private String description;
	private String regDate;
	public int getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getSocialType() {
		return socialType;
	}
	public void setSocialType(String socialType) {
		this.socialType = socialType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public void setNo(int no) {
		this.no = no;
	}
	@Override
	public String toString() {
		return "NoticeDto [no=" + no + ", title=" + title + ", userId=" + userId + ", nickName=" + nickName
				+ ", socialType=" + socialType + ", description=" + description + ", regDate=" + regDate + "]";
	}
	
}
