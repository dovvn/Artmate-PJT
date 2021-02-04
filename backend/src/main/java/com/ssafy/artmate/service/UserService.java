package com.ssafy.artmate.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.artmate.dto.UserDto;

public interface UserService {
	boolean insertUser(UserDto user); //가입
	UserDto selectWithIdAndPw(UserDto user); //로그인 (아이디와 비밀번호로 가져오기)
	UserDto selectUseridAndAccesskey(String userId, String accesskey); //메일로 받은 아이디,인증키에 해당하는 유저 찾기
	boolean modifyAccesskey(UserDto user); //인증키 'Y'로 업데이트
	UserDto selectUserId(String userId); //아이디 중복 확인
	UserDto selectUserName(String userName); //닉네임 중복 확인
	boolean modifyUserInfo(UserDto user);//회원 정보 수정 (닉네임, 프로필사진, 비밀번호, 피드명, 소개글)
	boolean deleteUser(String userId); //회원 탈퇴
	int modifyUserPw(UserDto user); //임시 비밀번호 변경
	int selectFollowerCnt(String userId);//팔로워 수 가져오기
	int selectFollowingCnt(String userId);//팔로잉 수 가져오기
	List<UserDto> selectFollower(String userId);//팔로워 회원 정보 가져오기
	List<UserDto> selectFollowing(String userId);//팔로잉 회원 정보 가져오기
	boolean insertFollow(String sendUserId, String getUserId); //팔로우
	boolean deleteFollow(String sendUserId, String getUserId); //언팔로우
	String selectUserImg(String userId); //db에서 이미지 주소 가져오기
	UserDto selectUser(String userId); //회원 정보 가져오기
	boolean selectFollowState(@Param("sendUserId")String sendUserId, @Param("getUserId")String getUserId); //팔로우 상태 가져오기
	boolean insertMyTag(String userId, String tag);//태그 추가하기
	boolean deleteMyTag(String userId); //태그 삭제하기
	List<String> selectMyTag(String userId); //태그 가져오기
	boolean modifyUserImg(UserDto user); //프로필 이미지 수정하기
	boolean insertKakao(UserDto user); //카카오로 회원가입
}
