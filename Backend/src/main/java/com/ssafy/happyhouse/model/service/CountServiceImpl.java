package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.CountDto;
import com.ssafy.happyhouse.model.mapper.CountMapper;

@Service
public class CountServiceImpl implements CountService {

	@Autowired
	private CountMapper countMapper;

	// 렌더링용
	@Override
	public List<CountDto> countList() throws Exception {
		return countMapper.countList();
	}

	@Override
	public void rankUpdate() throws Exception {
		// TODO Auto-generated method stub
		List<CountDto> list = countMapper.preCountList();

		for (int i = 0; i < list.size(); i++) {
			// ( preCount를 count로 업데이트 )
			list.get(i).setPreCount(list.get(i).getCount());
			// 현재 index - preRank 를 rankChange에 업데이트
			list.get(i).setRankChange(i + 1 - list.get(i).getPreRank());
			// preRank는 현재 index로 업데이트
			list.get(i).setPreRank(i + 1);
		}
		countMapper.rankUpdate(list);
	}

	// 업데이트 용
	@Override
	public List<CountDto> preCountList() throws Exception {
		// TODO Auto-generated method stub
		return countMapper.preCountList();
	}

	@Override
	public void visitApt(String aptCode) throws Exception {
		countMapper.visitApt(aptCode);
		
	}
}
