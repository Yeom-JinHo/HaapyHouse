package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.CountDto;
import com.ssafy.happyhouse.model.service.CountService;


@RestController
@RequestMapping("/count")
public class CountController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	
	@Autowired
	private CountService countService;
	
	@GetMapping("/search")
	public ResponseEntity<List<CountDto>> countList() throws Exception {
		List<CountDto> list = countService.countList();
		return new ResponseEntity<List<CountDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/update")
	public ResponseEntity<?> countUpdate() throws Exception {
		countService.rankUpdate();
		return new ResponseEntity<String>("업데이트 완료", HttpStatus.OK);
	}
	
	@PutMapping("/visit")
	public ResponseEntity<?> visitApt(@RequestParam ("aptCode") String aptCode) throws Exception{
		countService.visitApt(aptCode);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
}
