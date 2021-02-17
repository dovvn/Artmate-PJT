package com.ssafy.artmate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.artmate.dto.FeedDto;
@Mapper
public interface FeedMapper {
	int insertFeed(FeedDto feed); //피드 글 작성
	List<FeedDto> selectAllMyFeed(String userId); //내 피드 목록 가져오기
	List<FeedDto> selectAllNewsFeed(String userId); //내가 팔로우한 회원들 피드 목록 가져오기
	FeedDto selectOneFeed(@Param("userId")String userId, @Param("id")int id); //피드 하나 가져오기
	int modifyFeed(FeedDto feed);// 피드 글 수정
	int deleteFeed(int id);//피드 글 삭제
	int insertBookmark(@Param("userId")String userId, @Param("feedId")int feedId);//북마크에 피드 추가
	int deleteBookmark(@Param("userId")String userId, @Param("feedId")int feedId);//북마크에 피드 삭제
	List<FeedDto> selectMyBookmark(String userId);//북마크 목록 가져오기
	FeedDto selectOneBookmark(@Param("userId")String userId, @Param("feedId")int feedId);//북마크에 있는지 확인
	int insertLikemark(@Param("userId")String userId, @Param("feedId")int feedId);//좋아요 추가
	int deleteLikemark(@Param("userId")String userId, @Param("feedId")int feedId);//좋아요 삭제
	FeedDto selectOneLikemark(@Param("userId")String userId, @Param("feedId")int feedId);//좋아요에 있는지 확인
	String selectFeedImg(int id); //피드에서 이미지 가져오기
	int modifyLikeCnt(FeedDto feed); //피드 좋아요 수 수정
	int modifyFeedImg(FeedDto feed); //피드 이미지 변경
	List<FeedDto> selectAllFeed(String userId); //전체 피드목록 반환
	int insertFeedExhibit(@Param("userId")String userId, @Param("feedId")int feedId); //전시할 피드 저장
	int deleteFeedExhibit(@Param("userId")String userId, @Param("feedId")int feedId); //전시에서 피드 삭제
	List<FeedDto> selectFeedExhibit(String userId); //전시한 피드 정보 가져오기
	int checkFeedExhibit(@Param("userId")String userId, @Param("feedId")int feedId); //전시할 피드에 저장했는지 확인
	int modifyFeedTheme(@Param("userId")String userId, @Param("theme") int theme); //유저 피드테마 번호 업데이트
	int selectFeedTheme(@Param("userId") String userId); //유저 피드테마 번호 가져오기
	List<FeedDto> selectPopularFeed();//인기 순으로 피드 가져오기
}
