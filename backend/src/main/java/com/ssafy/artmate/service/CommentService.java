package com.ssafy.artmate.service;

import java.util.List;

import com.ssafy.artmate.Dto.CommentDto;

public interface CommentService {
	List<CommentDto> selectCommentList(int feedId);
	boolean modifyComment(CommentDto comment);
	Boolean deleteComment(int id);
	Boolean insertComment(CommentDto comment);

}
