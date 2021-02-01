package com.ssafy.artmate.mapper;

import java.util.List;

import com.ssafy.artmate.Dto.SearchDto;
import com.ssafy.artmate.Dto.UserDto;

public interface SearchMapper {

	SearchDto selectKeyword(String keyword);
	int insertKeyword(String keyword);
	int modifyKeywordCnt(SearchDto search);
	List<UserDto> selectKeywordUser(String keyword);
	List<String> selectPopular();
	List<String> selectAllKeywords();
}
