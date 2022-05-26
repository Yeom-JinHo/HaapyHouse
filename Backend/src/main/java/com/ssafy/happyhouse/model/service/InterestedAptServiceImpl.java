package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.InterestedAptDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.mapper.InterestedAptMapper;

@Service
public class InterestedAptServiceImpl implements InterestedAptService {

	@Autowired
	private InterestedAptMapper interestedAptMapper;

	@Override
	public boolean registApt(InterestedAptDto apt) throws Exception {
		// TODO Auto-generated method stub
		return interestedAptMapper.registApt(apt) == 1;
	}



	@Override
	public List<InterestedAptDto> listApt(InterestedAptDto apt) throws Exception {
		// TODO Auto-generated method stub
		List<InterestedAptDto> list = interestedAptMapper.listApt(apt);
		List<InterestedAptDto> l  = new ArrayList<>();
		for(InterestedAptDto i : list) {
			l.add(interestedAptMapper.getApt(i));
		}
		return l;
	}

	@Override
	public List<CommentDto> listComment(InterestedAptDto userInfo) throws Exception {
		// TODO Auto-generated method stub
		return interestedAptMapper.listComment(userInfo);
	}

	@Override
	public List<NoticeDto> listNotice(InterestedAptDto userInfo) throws Exception {
		// TODO Auto-generated method stub
		return interestedAptMapper.listNotice(userInfo);
	}

	@Override
	public Boolean getCheck(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		
	
		return interestedAptMapper.getCheck(map) == 1;
	}



	@Override
	public boolean deleteApt(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return interestedAptMapper.deleteApt(map) == 1;
	}

}
