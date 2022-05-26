package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.service.CommentService;


import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/comment")
public class CommentController {
	
	private final Logger logger = LoggerFactory.getLogger(HouseMapController.class);
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	CommentService commentService;

	@ApiOperation(value = "notice_no에 해당하는 게시글의 댓글을 반환한다.", response = List.class)
	@GetMapping("{notice_no}") 
	public ResponseEntity<?> listComment(@PathVariable("notice_no") String notice_no) {
		logger.debug("listComment - 호출");
		return new ResponseEntity<List<CommentDto>>(commentService.list(notice_no), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createComment(@RequestBody CommentDto comment) {
		logger.debug("createComment - 호출");
		if(commentService.create(comment)) {
			return new ResponseEntity<List<CommentDto>>(commentService.list(comment.getNotice_no()), HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<?> modifyComment(@RequestBody CommentDto comment) {

		if(commentService.modify(comment)) {

			return new ResponseEntity<List<CommentDto>>(commentService.list(comment.getNotice_no()), HttpStatus.OK);

		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PutMapping("/delete")
	public ResponseEntity<?> deleteBook(@RequestBody CommentDto comment) {

		if(commentService.delete(comment)) {
			return new ResponseEntity<List<CommentDto>>(commentService.list(comment.getNotice_no()), HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
