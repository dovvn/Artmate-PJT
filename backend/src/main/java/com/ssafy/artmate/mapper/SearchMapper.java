package com.ssafy.artmate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.artmate.dto.ExhibitDto;
import com.ssafy.artmate.dto.SearchDto;
import com.ssafy.artmate.dto.UserDto;
@Mapper
public interface SearchMapper {

	SearchDto selectKeyword(String keyword);
	int insertKeyword(String keyword);
	int modifyKeywordCnt(SearchDto search);
	List<UserDto> selectKeywordUser(String keyword);
	List<String> selectPopular();
	List<String> selectAllKeywords();
	List<ExhibitDto> selectKeywordExhibit(String keyword);
}
