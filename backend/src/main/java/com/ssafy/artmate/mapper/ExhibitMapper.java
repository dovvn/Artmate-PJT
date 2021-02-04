package com.ssafy.artmate.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.artmate.dto.ExhibitDto;

@Mapper
public interface ExhibitMapper {
	ExhibitDto selectExhibitBySeq(String seqNum);
	int insertExhibit(ExhibitDto dto);
<<<<<<< HEAD
	int insertTag(HashMap<String, Integer> tagInfo);
	List<ExhibitDto> selectExhibitMyTag(String userId);
	List<ExhibitDto> selectAllExhibit();
	ExhibitDto selectOneExhibit(int id);
=======
	int insertTag(HashMap<String, Object> tagInfo);
>>>>>>> b130404681492332219519ef5007a00719effe78
}
