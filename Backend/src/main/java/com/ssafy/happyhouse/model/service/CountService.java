package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.CountDto;

public interface CountService {
	List<CountDto> countList() throws Exception;
	List<CountDto> preCountList() throws Exception;
	void rankUpdate() throws Exception;
	void visitApt(String aptCode) throws Exception;
}
