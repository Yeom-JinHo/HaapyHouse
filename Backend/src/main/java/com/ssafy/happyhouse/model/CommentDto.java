package com.ssafy.happyhouse.model;

import java.util.List;
import java.util.Map;

public class CommentDto {
	private int comment_no;
	private String nickName;
	private String userId;
	private String comment;
	private String comment_time;
	private String notice_no;
	private int commentDepth;
	private int commentGroup;
	private String socialtype;
	private boolean deleted;
	
	private List< CommentDto> children;
	
	public List< CommentDto> getChildren() {
		return children;
	}
	public void setChildren(List< CommentDto> children) {
		this.children = children;
	}


	
	public boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment_time() {
		return comment_time;
	}
	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}
	public String getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(String notice_no) {
		this.notice_no = notice_no;
	}
	
	
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getCommentDepth() {
		return commentDepth;
	}
	public void setCommentDepth(int commentDepth) {
		this.commentDepth = commentDepth;
	}
	public int getCommentGroup() {
		return commentGroup;
	}
	public void setCommentGroup(int commentGroup) {
		this.commentGroup = commentGroup;
	}
	
	public String getSocialtype() {
		return socialtype;
	}
	public void setSocialtype(String socialtype) {
		this.socialtype = socialtype;
	}

	
	
}
