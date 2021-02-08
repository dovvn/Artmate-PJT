package com.ssafy.artmate.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.artmate.dto.ExhibitDto;
import com.ssafy.artmate.dto.FeedDto;

public interface ExhibitService {
	ExhibitDto selectExhibitBySeq(String seq); //일련번호에 해당하는 전시회 찾기
	boolean insertExhibit(ExhibitDto dto); //전시회 데이터 넣기
	boolean insertTag(HashMap<String, Object> tagInfo); //전시회 태그정보 넣기(전시회번호, 태그이름)
	List<ExhibitDto> selectExhibitMyTag(String userId); //내 태그 들어간 전시회 목록
	List<ExhibitDto> selectAllExhibit(String userId); //모든 전시회 목록
	ExhibitDto selectOneExhibit(String userId, int id); //선택한 전시회 상세 정보
	List<String> selectExhibitTags(int id);//선택한 전시회 태그들 가져오기
	List<ExhibitDto> selectExhibitbyMap(); //지도에서 보여줄 모든 전시회 정보 가져오기
	boolean insertScrapbook(String userId, int id); //스크랩북에 전시회 추가
	boolean deleteScrapbook(String userId, int id); //스크랩북에서 전시회 삭제
	ExhibitDto selectOneScrapbook(String userId, int id); //스크랩북에서 하나 선택
	List<ExhibitDto> selectAllScrapbook(String userId); //스크랩북 목록 가져오기
	List<ExhibitDto> selectExhibitNameWithLoc(); //전시회 아이디,이름,장소 가져오기
	List<FeedDto> selectFeeds(int id); //전시회 관련 피드 리스트 가져오기
}
