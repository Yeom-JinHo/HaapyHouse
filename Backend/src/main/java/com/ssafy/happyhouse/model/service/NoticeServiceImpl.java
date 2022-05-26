package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeMapper noticeMapper;

	@Override
	public List<NoticeDto> searchAllNotice() throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.searchAllNotice();
	}
	@Override
	public List<NoticeDto> searchByNickNameNotice(String nickName) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.searchByNickNameNotice(nickName);
	}
	
	@Override
	public List<NoticeDto> searchByNoNotice(int no) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.searchByNoNotice(no);
	}
	
	@Override
	public List<NoticeDto> searchByTitleNotice(String title) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.searchByTitleNotice(title);
	}
	@Override
	public int registNotice(NoticeDto notice) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.registNotice(notice);
	}

	@Override
	public int updateNotice(NoticeDto notice) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.updateNotice(notice);
	}

	@Override
	public int deleteNotice(String no) throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.deleteNotice(no);
	}
	@Override
	public List<NewsDto> listNews() throws Exception {
		// TODO Auto-generated method stub
		return noticeMapper.listNews();
	}

	

}
