package com.ssafy.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.NewsDto;
import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.service.NoticeService;

@RestController
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@GetMapping("")
	public ResponseEntity<?> searchNotice(@RequestParam(required = false) String no,@RequestParam(required = false) String nickName,
			@RequestParam(required = false) String title) {
		System.out.println("ID : " + nickName);
		System.out.println("TITLE : " + title);
		System.out.println("No : " + no);
		try {
			List<NoticeDto> result=null;
			if (nickName == null && title == null &&no==null) {
				result=noticeService.searchAllNotice();
//				return new ResponseEntity<List<NoticeDto>>(noticeService.searchAllNotice(), HttpStatus.OK);
			} else if (nickName != null) {
				result=noticeService.searchByNickNameNotice(nickName);
//				return new ResponseEntity<List<NoticeDto>>(noticeService.searchByIdNotice(id), HttpStatus.OK);
			} else if (title != null) {
				result=noticeService.searchByTitleNotice(title);
//				return new ResponseEntity<List<NoticeDto>>(noticeService.searchByTitleNotice(title), HttpStatus.OK);
			}else if(no!=null){
				result=noticeService.searchByNoNotice(Integer.parseInt(no));
//				return new ResponseEntity<List<NoticeDto>>(noticeService.searchByNoNotice(Integer.parseInt(no)), HttpStatus.OK);
			}
			if(result==null) {
				return new ResponseEntity<String>("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
			}else if(result.size()==0) {
				return new ResponseEntity<String>("조회된 내용이 없습니다.", HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<List<NoticeDto>>(result, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<String>("조회 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("")
	public ResponseEntity<?> registNotice(@RequestBody NoticeDto notice) {
		System.out.println("등록 : " + notice);
		try {
			int result = noticeService.registNotice(notice);
			if (result == 1) {
				return new ResponseEntity<String>("등록 성공", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("등록 실패", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("")
	public ResponseEntity<?> updateNotice(@RequestBody NoticeDto notice) {
		try {
			int result = noticeService.updateNotice(notice);
//			List<NoticeDto> lists=noticeService.searchAllNotice();
			if (result == 1) {
				return new ResponseEntity<String>("수정 성공", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("수정 실패", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("")
	public ResponseEntity<?> deleteNotice(@RequestParam String no) {
		try {
			int result = noticeService.deleteNotice(no);
			
			if (result == 1) {
				return new ResponseEntity<String>("삭제 성공", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("삭제 실패", HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
	
	
	@GetMapping("/news")
	public ResponseEntity<?> listNews() {

		try {
			List<NewsDto> result = noticeService.listNews();
			if(result==null) {
				return new ResponseEntity<String>("잘못된 요청입니다.", HttpStatus.BAD_REQUEST);
			}else if(result.size()==0) {
				return new ResponseEntity<String>("조회된 내용이 없습니다.", HttpStatus.NO_CONTENT);
			}else {
				return new ResponseEntity<List<NewsDto>>(result, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			return new ResponseEntity<String>("조회 실패", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
