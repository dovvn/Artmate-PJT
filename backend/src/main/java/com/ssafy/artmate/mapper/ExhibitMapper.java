package com.ssafy.artmate.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.artmate.dto.ExhibitDto;

@Mapper
public interface ExhibitMapper {
	ExhibitDto selectExhibitBySeq(String seqNum);
	int insertExhibit(ExhibitDto dto);
	int insertTag(HashMap<String, Object> tagInfo);
	List<ExhibitDto> selectExhibitMyTag(String userId);
	List<ExhibitDto> selectAllExhibit();
	ExhibitDto selectOneExhibit(int id);
	List<String> selectExhibitTags(int id);
	List<ExhibitDto> selectExhibitbyMap();
}
