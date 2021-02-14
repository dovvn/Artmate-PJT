package com.ssafy.artmate.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.artmate.dto.ExhibitDto;
import com.ssafy.artmate.dto.FeedDto;

@Mapper
public interface ExhibitMapper {
	ExhibitDto selectExhibitBySeq(String seqNum);
	int insertExhibit(ExhibitDto dto);
	int insertTag(HashMap<String, Object> tagInfo);
	List<ExhibitDto> selectExhibitMyTag(String userId);
	List<ExhibitDto> selectAllExhibit(String userId);
	ExhibitDto selectOneExhibit(@Param("userId")String userId, @Param("id")int id);
	List<String> selectExhibitTags(int id);
	List<ExhibitDto> selectExhibitbyMap();
	int insertScrapbook(@Param("userId")String userId, @Param("id")int id);
	int deleteScrapbook(@Param("userId")String userId, @Param("id")int id);
	ExhibitDto selectOneScrapbook(@Param("userId")String userId, @Param("id")int id);
	List<ExhibitDto> selectAllScrapbook(String userId);
	List<ExhibitDto> selectExhibitNameWithLoc();
	List<FeedDto> selectFeeds(int id);
	ExhibitDto selectExhibitByVrLink(String vrLink);
	int insertOnlineExhibit(ExhibitDto dto);
	List<ExhibitDto> selectAllOnlineExhibit();
	int checkNaverExhibit(ExhibitDto dto);
	boolean deleteExhibition(String endDate);
}
