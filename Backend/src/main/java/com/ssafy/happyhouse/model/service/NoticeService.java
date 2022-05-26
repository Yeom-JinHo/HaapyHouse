package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.NoticeDto;

public interface NoticeService {

	List<NoticeDto> searchAllNotice() throws Exception;

	List<NoticeDto> searchByNickNameNotice(String nickName) throws Exception;
	
	List<NoticeDto> searchByNoNotice(int no) throws Exception;
	
	List<NoticeDto> searchByTitleNotice(String title) throws Exception;
	
	int registNotice(NoticeDto notice) throws Exception;

	int updateNotice(NoticeDto notice) throws Exception;

	int deleteNotice(String no) throws Exception;

	List<NewsDto> listNews() throws Exception;
}
