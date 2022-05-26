package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeMapper {

	List<NoticeDto> searchAllNotice() throws SQLException;
	
	List<NoticeDto> searchByNickNameNotice(String nickName) throws SQLException;
	
	List<NoticeDto> searchByNoNotice(int no) throws SQLException;
	
	List<NoticeDto> searchByTitleNotice(String title) throws SQLException;
	
	int registNotice(NoticeDto notice) throws SQLException;

	int updateNotice(NoticeDto notice) throws SQLException;

	int deleteNotice(String no) throws SQLException;

	List<NewsDto> listNews() throws SQLException;
}
