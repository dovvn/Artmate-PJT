package com.ssafy.artmate.service;

import java.util.List;

import com.ssafy.artmate.Dto.FeedDto;

public interface FeedService{
	boolean insertFeed(FeedDto feed); //피드 글 작성
	List<FeedDto> selectAllMyFeed(String userId); //내 피드 목록 가져오기
	List<FeedDto> selectAllNewsFeed(String userId); //내가 팔로우한 회원들 피드 목록 가져오기
	FeedDto selectOneFeed(String userId, int id); //피드 하나 가져오기
	boolean modifyFeed(FeedDto feed);// 피드 글 수정
	boolean deleteFeed(int id);//피드 글 삭제
	boolean insertBookmark(String userId, int feedId);//북마크에 피드 추가
	boolean deleteBookmark(String userId, int feedId);//북마크에 피드 삭제
	List<FeedDto> selectMyBookmark(String userId);//북마크 목록 가져오기
	FeedDto selectOneBookmark(String userId, int feedId);//북마크에 있는지 확인
	boolean insertLikemark(String userId, int feedId);//좋아요 추가
	boolean deleteLikemark(String userId, int feedId);//좋아요 삭제
	FeedDto selectOneLikemark(String userId, int feedId);//좋아요에 있는지 확인
	String selectFeedImg(int id); //피드에서 이미지 가져오기
	boolean modifyLikeCnt(FeedDto feed); //피드 좋아요 수 수정
	boolean modifyFeedImg(FeedDto feed); //피드 이미지 변경
}
