package com.ssafy.artmate.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.artmate.Dto.MyFileDto;
import com.ssafy.artmate.Dto.UserDto;

public interface UserMapper {
	boolean insertUser(UserDto user);
	UserDto selectUseridAndAccesskey(String userId, String accesskey);
	boolean modifyAccesskey(UserDto user);
	UserDto selectUserId(String userId);
	UserDto selectUserName(String userName);
	UserDto selectWithIdAndPw(UserDto user);
	int modifyUserInfo(UserDto user);
	int deleteUser(String userId);
	int modifyUserPw(UserDto user);
	int selectFollowerCnt(String userId);
	int selectFollowingCnt(String userId);
	List<UserDto> selectFollower(String userId);
	List<UserDto> selectFollowing(String userId);
	int insertFollow(String sendUserId, String getUserId);
	int deleteFollow(String sendUserId, String getUserId);
	int modifyUserImg(UserDto user);
	String selectUserImg(String userId);
	UserDto selectUser(String userId);
	int selectFollowState(String sendUserId, String getUserId);
	int insertMyTag(@Param("userId")String userId, @Param("tag")String tag);
	int deleteMyTag(String userId);
	List<String> selectMyTag(String userId);
	int insertKakao(UserDto user);
}
