package com.ssafy.artmate.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "- 사용자  정보", description = "사용자 정보를 나타낸다.")
public class UserDto implements Serializable{
	@ApiModelProperty(value="아이디(이메일). primary key *필수")
	private String userId; 
	@ApiModelProperty(value="비밀번호. *필수")
	private String userPw;
	@ApiModelProperty(value="닉네임 *필수")
	private String userName;
	@ApiModelProperty(value="피드명")
	private String feedName;
	@ApiModelProperty(value="소개글")
	private String introduction;
	@ApiModelProperty(value="프로필 사진 (서버에 저장한 이미지 url)")
	private String userImg;
	@ApiModelProperty(value="인증키 (본인인증 전:랜덤10자리, 인증 후:y)")
	private String accesskey; 
	@ApiModelProperty(value="선택한 관심태그 리스트")
	private List<String> myTag;
	@ApiModelProperty(value="작성한 피드 수")
	private int feedCnt;
	@ApiModelProperty(value="팔로워 수 : 나를 팔로우한 사람 수")
	private int followerCnt;
	@ApiModelProperty(value="팔로잉 수 : 내가 팔로우한 사람 수")
	private int followingCnt;

	
	public UserDto() {}
	
	public UserDto(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
	}


	public UserDto(String userId, String userName, String userImg) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userImg = userImg;
	}


	public UserDto(String userId, String userPw, String userName, String feedName, String introduction, String userImg,
			String accesskey, List<String> myTag, int feedCnt, int followerCnt, int followingCnt) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.feedName = feedName;
		this.introduction = introduction;
		this.userImg = userImg;
		this.accesskey = accesskey;
		this.myTag = myTag;
		this.feedCnt = feedCnt;
		this.followerCnt = followerCnt;
		this.followingCnt = followingCnt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFeedName() {
		return feedName;
	}

	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getAccesskey() {
		return accesskey;
	}

	public void setAccesskey(String accesskey) {
		this.accesskey = accesskey;
	}

	public List<String> getMyTag() {
		return myTag;
	}

	public void setMyTag(List<String> myTag) {
		this.myTag = myTag;
	}

	public int getFeedCnt() {
		return feedCnt;
	}

	public void setFeedCnt(int feedCnt) {
		this.feedCnt = feedCnt;
	}

	public int getFollowerCnt() {
		return followerCnt;
	}

	public void setFollowerCnt(int followerCnt) {
		this.followerCnt = followerCnt;
	}

	public int getFollowingCnt() {
		return followingCnt;
	}

	public void setFollowingCnt(int followingCnt) {
		this.followingCnt = followingCnt;
	}

	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", feedName=" + feedName
				+ ", introduction=" + introduction + ", userImg=" + userImg + ", accesskey=" + accesskey + ", myTag="
				+ myTag + ", feedCnt=" + feedCnt + ", followerCnt=" + followerCnt + ", followingCnt=" + followingCnt
				+ "]";
	}
}
