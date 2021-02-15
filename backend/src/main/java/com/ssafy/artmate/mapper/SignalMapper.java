package com.ssafy.artmate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.artmate.dto.SignalDto;
@Mapper
public interface SignalMapper {
	int insertSignal(SignalDto signal);// 알림 전송
	int deleteSignal(int id);// 알림 삭제
	int modifySignal(int id);// 알림 확인
	List<SignalDto> selectAllSignal(String getUserId);// 알림 목록 가져오기
	int countNewSignal(String userId); //확인 안한 알림 갯수 반환
	List<SignalDto> selectExhibitSignal(String getUserId);// 전시회 알림 목록 가져오기
	List<SignalDto> selectFeedSignal(String getUserId);// 피드 알림 목록 가져오기
}
