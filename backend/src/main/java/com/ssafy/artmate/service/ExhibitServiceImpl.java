package com.ssafy.artmate.service;


import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.artmate.dto.ExhibitDto;
import com.ssafy.artmate.mapper.ExhibitMapper;

@Service
public class ExhibitServiceImpl implements ExhibitService{
	@Autowired
	private SqlSession sqlSession; 
	
	@Override
	public ExhibitDto selectExhibitBySeq(String seqNum) {
		return sqlSession.getMapper(ExhibitMapper.class).selectExhibitBySeq(seqNum);
	}

	@Override
	public boolean insertExhibit(ExhibitDto dto) {
		return sqlSession.getMapper(ExhibitMapper.class).insertExhibit(dto) == 1;
	}

	@Override
	public boolean insertTag(HashMap<String, Integer> tagInfo) {
		return sqlSession.getMapper(ExhibitMapper.class).insertTag(tagInfo) == 1;
	}

	@Override
	public List<ExhibitDto> selectExhibitMyTag(String userId) {
		return sqlSession.getMapper(ExhibitMapper.class).selectExhibitMyTag(userId);
	}

	@Override
	public List<ExhibitDto> selectAllExhibit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExhibitDto selectOneExhibit(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
