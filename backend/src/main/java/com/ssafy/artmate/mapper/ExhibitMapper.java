package com.ssafy.artmate.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.artmate.dto.ExhibitDto;

@Mapper
public interface ExhibitMapper {
	ExhibitDto selectExhibitBySeq(String seqNum);
	int insertExhibit(ExhibitDto dto);
	int insertTag(HashMap<String, Object> tagInfo);
}
