package com.ssafy.artmate.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.artmate.Dto.SignalDto;
import com.ssafy.artmate.mapper.SignalMapper;

@Service
public class SignalServiceImpl implements SignalService{

	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean insertSignal(SignalDto signal) {
		if(sqlSession.getMapper(SignalMapper.class).insertSignal(signal)==1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteSignal(int id) {
		if(sqlSession.getMapper(SignalMapper.class).deleteSignal(id)==1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean modifySignal(int id) {
		if(sqlSession.getMapper(SignalMapper.class).modifySignal(id)==1) {
			return true;
		}
		return false;
	}

	@Override
	public List<SignalDto> selectAllSignal(String getUserId) {
		return sqlSession.getMapper(SignalMapper.class).selectAllSignal(getUserId);
	} 
	

}
