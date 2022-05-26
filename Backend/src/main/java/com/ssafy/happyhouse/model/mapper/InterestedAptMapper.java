package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.InterestedAptDto;
import com.ssafy.happyhouse.model.NoticeDto;

public interface InterestedAptMapper {

	int registApt(InterestedAptDto apt) throws SQLException;

	int deleteApt(Map<String, Object> map) throws SQLException;

	List<InterestedAptDto> listApt(InterestedAptDto apt) throws SQLException;
	InterestedAptDto getApt(InterestedAptDto aptCode) throws SQLException;

	List<CommentDto> listComment(InterestedAptDto userInfo) throws SQLException;

	List<NoticeDto> listNotice(InterestedAptDto userInfo) throws SQLException;


	int getCheck(Map<String, Object> map) throws SQLException;
}
