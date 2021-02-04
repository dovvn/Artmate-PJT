package com.ssafy.artmate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.artmate.dto.CommentDto;

@Mapper
public interface CommentMapper {
	public List<CommentDto> selectCommentList(int feedId);
	public int modifyComment(CommentDto comment);
	public int deleteComment(int id);
	public int insertComment(CommentDto comment);
}
