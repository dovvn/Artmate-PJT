package com.ssafy.artmate.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.artmate.dto.MyFileDto;
import com.ssafy.artmate.dto.UserDto;
import com.ssafy.artmate.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean insertUser(UserDto user) {
		return sqlSession.getMapper(UserMapper.class).insertUser(user);
	}

	@Override
	public UserDto selectUseridAndAccesskey(String userId, String accesskey) {
		return sqlSession.getMapper(UserMapper.class).selectUseridAndAccesskey(userId, accesskey);
	}

	@Override
	public boolean modifyAccesskey(UserDto user) {
		return sqlSession.getMapper(UserMapper.class).modifyAccesskey(user);
	}

	@Override
	public UserDto selectUserId(String userId) {
		return sqlSession.getMapper(UserMapper.class).selectUserId(userId);
	}

	@Override
	public UserDto selectUserName(String userName) {
		return sqlSession.getMapper(UserMapper.class).selectUserName(userName);
	}

	@Override
	public UserDto selectWithIdAndPw(UserDto user) {
		return sqlSession.getMapper(UserMapper.class).selectWithIdAndPw(user);
	}

	@Override
	public boolean modifyUserInfo(UserDto user) {
		if(sqlSession.getMapper(UserMapper.class).modifyUserInfo(user)==1) return true;
		return false;
	}

	@Override
	public boolean deleteUser(String userId) {
		return (sqlSession.getMapper(UserMapper.class).deleteUser(userId) == 1);
	}

	@Override
	public int modifyUserPw(UserDto user) {
		return sqlSession.getMapper(UserMapper.class).modifyUserPw(user);
	}

	@Override
	public int selectFollowerCnt(String userId) {
		return sqlSession.getMapper(UserMapper.class).selectFollowerCnt(userId);
	}

	@Override
	public int selectFollowingCnt(String userId) {
		return sqlSession.getMapper(UserMapper.class).selectFollowingCnt(userId);
	}

	@Override
	public List<UserDto> selectFollower(String userId) {
		return sqlSession.getMapper(UserMapper.class).selectFollower(userId);
	}

	@Override
	public List<UserDto> selectFollowing(String userId) {
		return sqlSession.getMapper(UserMapper.class).selectFollowing(userId);
	}

	@Override
	public boolean insertFollow(String sendUserId, String getUserId) {
		if(sqlSession.getMapper(UserMapper.class).insertFollow(sendUserId, getUserId)==1) return true;
		return false;
	}

	@Override
	public boolean deleteFollow(String sendUserId, String getUserId) {
		if(sqlSession.getMapper(UserMapper.class).deleteFollow(sendUserId, getUserId)==1) return true;
		return false;
	}

	@Override
	public String selectUserImg(String userId) {
		return sqlSession.getMapper(UserMapper.class).selectUserImg(userId);
	}

	@Override
	public UserDto selectUser(String userId) {
		return sqlSession.getMapper(UserMapper.class).selectUser(userId);
	}

	@Override
	public int selectFollowState(String sendUserId, String getUserId) {
		return sqlSession.getMapper(UserMapper.class).selectFollowState(sendUserId, getUserId);
	}

	@Override
	public boolean insertMyTag(String userId, String tag) {
		if(sqlSession.getMapper(UserMapper.class).insertMyTag(userId, tag)==1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteMyTag(String userId) {
		if(sqlSession.getMapper(UserMapper.class).deleteMyTag(userId)==1) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> selectMyTag(String userId) {
		return sqlSession.getMapper(UserMapper.class).selectMyTag(userId);
	}

	@Override
	public boolean modifyUserImg(UserDto user) {
		return sqlSession.getMapper(UserMapper.class).modifyUserImg(user) == 1;
	}

	@Override
	public boolean insertKakao(UserDto user) {
		return sqlSession.getMapper(UserMapper.class).insertKakao(user) == 1;
	}

	@Override
	public boolean modifyFollow(String sendUserId, String getUserId) {
		return sqlSession.getMapper(UserMapper.class).modifyFollow(sendUserId, getUserId);
	}

	@Override
	public List<UserDto> selectAllUser() {
		return sqlSession.getMapper(UserMapper.class).selectAllUser();
	}
}
