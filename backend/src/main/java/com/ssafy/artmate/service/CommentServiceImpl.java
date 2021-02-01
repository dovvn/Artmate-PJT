package com.ssafy.artmate.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.artmate.Dto.CommentDto;
import com.ssafy.artmate.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<CommentDto> selectCommentList(int feedId) {
		return sqlSession.getMapper(CommentMapper.class).selectCommentList(feedId);
	}

	@Override
	public boolean modifyComment(CommentDto comment) {
		return sqlSession.getMapper(CommentMapper.class).modifyComment(comment) == 1;
	}

	@Override
	public Boolean deleteComment(int id) {
		return sqlSession.getMapper(CommentMapper.class).deleteComment(id) == 1;
	}

	@Override
	public Boolean insertComment(CommentDto comment) {
		return sqlSession.getMapper(CommentMapper.class).insertComment(comment) == 1;

	}
}
