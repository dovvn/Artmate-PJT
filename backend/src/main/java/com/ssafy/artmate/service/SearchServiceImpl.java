package com.ssafy.artmate.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.artmate.dto.SearchDto;
import com.ssafy.artmate.dto.UserDto;
import com.ssafy.artmate.mapper.SearchMapper;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SqlSession sqlSession;


	@Override
	public SearchDto selectKeyword(String keyword) {
		return sqlSession.getMapper(SearchMapper.class).selectKeyword(keyword);
	}

	@Override
	public boolean insertKeyword(String keyword) {
		return (sqlSession.getMapper(SearchMapper.class).insertKeyword(keyword) == 1);
	}

	@Override
	public boolean modifyKeywordCnt(SearchDto search) {
		return (sqlSession.getMapper(SearchMapper.class).modifyKeywordCnt(search) == 1);

	}
	
	@Override
	public List<UserDto> selectKeywordUser(String keyword) {
		return sqlSession.getMapper(SearchMapper.class).selectKeywordUser(keyword);
	}

	@Override
	public List<String> selectPopular() {
		return sqlSession.getMapper(SearchMapper.class).selectPopular();

	}

	@Override
	public List<String> selectAllKeywords() {
		return sqlSession.getMapper(SearchMapper.class).selectAllKeywords();

	}
}
