package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.HouseDealDto;
import com.ssafy.happyhouse.model.InterestedAptDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.InterestedAptService;

@RestController
@RequestMapping("/profile")
public class InterestedAptController {

	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	InterestedAptService interestedAptService;

	@PostMapping("/apt/regist")
	public ResponseEntity<?> registApt(@RequestBody InterestedAptDto apt) throws Exception {
		if (interestedAptService.registApt(apt)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/apt")
	public ResponseEntity<?> deleteApt(@RequestParam("aptCode") String aptCode, @RequestParam("userId") String userId, @RequestParam("socialType") String socialType) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aptCode", aptCode);
		map.put("userId", userId);
		map.put("socialType", socialType);
		
		
		if (interestedAptService.deleteApt(map)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
		}
	} 

	@PostMapping("/apt")
	public ResponseEntity<?> listApt(@RequestBody InterestedAptDto userInfo) throws Exception {
		return new ResponseEntity<List<InterestedAptDto>>(interestedAptService.listApt(userInfo), HttpStatus.OK);
	}
	
	@GetMapping("/apt/check")
	public ResponseEntity<?> aptDealList(@RequestParam("aptCode") String aptCode, @RequestParam("userId") String userId, @RequestParam("socialType") String socialType ) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("aptCode", aptCode);
		map.put("userId", userId);
		map.put("socialType", socialType);
		
		
		return new ResponseEntity<Boolean>(interestedAptService.getCheck(map), HttpStatus.OK);
	}
	

	
	////////////////////////////////////////////////////////////////////////////////////////////
	
	@PostMapping("/comment")
	public ResponseEntity<?> listComment(@RequestBody InterestedAptDto userInfo) throws Exception {
		return new ResponseEntity<List<CommentDto>>(interestedAptService.listComment(userInfo), HttpStatus.OK);
	}
	
	
	@PostMapping("/notice")
	public ResponseEntity<?> listNotice(@RequestBody InterestedAptDto userInfo) throws Exception {
		return new ResponseEntity<List<NoticeDto>>(interestedAptService.listNotice(userInfo), HttpStatus.OK);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
}
