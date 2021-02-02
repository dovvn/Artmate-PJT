package com.ssafy.artmate.mapper;

import java.util.HashMap;

import com.ssafy.artmate.Dto.ExhibitDto;

public interface ExhibitMapper {
	ExhibitDto selectExhibitBySeq(String seqNum);
	int insertExhibit(ExhibitDto dto);
	int insertTag(HashMap<String, Integer> tagInfo);
}
