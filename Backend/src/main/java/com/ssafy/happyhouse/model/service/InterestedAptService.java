package com.ssafy.happyhouse.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.InterestedAptDto;
import com.ssafy.happyhouse.model.NoticeDto;

public interface InterestedAptService {

	boolean registApt(InterestedAptDto apt) throws Exception;

	boolean deleteApt(Map<String, Object> map)throws Exception;

	List<InterestedAptDto> listApt(InterestedAptDto apt) throws Exception;

	List<CommentDto> listComment(InterestedAptDto userInfo) throws Exception;

	List<NoticeDto> listNotice(InterestedAptDto userInfo) throws Exception;

	Boolean getCheck(Map<String, Object> map) throws Exception;

}
