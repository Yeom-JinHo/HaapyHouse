package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.CommentDto;
import com.ssafy.happyhouse.model.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;
	
	@Override
	public List<CommentDto> list(String notice_no) {
		List<CommentDto> l = commentMapper.list(notice_no);
		List<CommentDto> newList = new ArrayList<>();
		
		
		for(int i = 0 ; i < l.size();i++) {
			CommentDto now =  l.get(i);
			if(now.getCommentDepth() == 0 && now.getCommentGroup() ==0) {
				newList.add(findChilren(l,i));
			}
		}

		return newList;
	}

	@Override
	public boolean create(CommentDto comment) {
		return commentMapper.create(comment) == 1;
	}

	@Override
	public boolean modify(CommentDto comment) {
		return commentMapper.modify(comment) == 1;
	}

	@Override
	public boolean delete(CommentDto comment) {
		return commentMapper.delete(comment) == 1;
	}
	
	
	
	
	public CommentDto findChilren(List<CommentDto> l,int copy) {
		CommentDto now =  l.get(copy);
		List<CommentDto> children = new ArrayList<>();
		
		CommentDto next = new CommentDto();
		//next.setChildren(now.getChildren());
		next.setComment(now.getComment());
		next.setComment_no(now.getComment_no());
		next.setComment_time(now.getComment_time());
		next.setCommentDepth(now.getCommentDepth());
		next.setCommentGroup(now.getCommentGroup());
		next.setNotice_no(now.getNotice_no());
		next.setUserId(now.getUserId());
		next.setNickName(now.getNickName());
		next.setSocialtype(now.getSocialtype());
		next.setDeleted(now.getDeleted());
		
		for(int j = copy ; j < l.size()  ; j++) {
			CommentDto ch = l.get(j);
			
			if(ch.getCommentDepth() == next.getCommentDepth() + 1 && ch.getCommentGroup() == next.getComment_no()) {
			//if(ch.getCommentDepth().equals("1") && ch.getCommentGroup().equals(now.getComment_no())) {
				children.add(findChilren(l,j));
				l.remove(j);
				j--;
			}
		}
		
		next.setChildren(children);
		
		return next;
		
		
	}
}
