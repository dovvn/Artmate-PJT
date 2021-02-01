package com.ssafy.artmate.mapper;

import java.util.List;

import com.ssafy.artmate.Dto.CommentDto;

public interface CommentMapper {
	public List<CommentDto> selectCommentList(int feedId);
	public int modifyComment(CommentDto comment);
	public int deleteComment(int id);
	public int insertComment(CommentDto comment);
}
