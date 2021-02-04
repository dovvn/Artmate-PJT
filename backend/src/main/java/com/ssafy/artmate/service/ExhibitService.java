package com.ssafy.artmate.service;

import java.util.HashMap;

import com.ssafy.artmate.dto.ExhibitDto;

public interface ExhibitService {
	ExhibitDto selectExhibitBySeq(String seq); //일련번호에 해당하는 전시회 찾기
	boolean insertExhibit(ExhibitDto dto); //전시회 데이터 넣기
	boolean insertTag(HashMap<String, Integer> tagInfo); //전시회 태그정보 넣기

}
