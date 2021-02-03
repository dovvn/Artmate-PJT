package com.ssafy.artmate.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "- 댓글 정보", description = "댓글의 상세 정보를 나타낸다.")
public class CommentDto {
	@ApiModelProperty(value="댓글 번호. primary key")
	private int id;
	@ApiModelProperty(value="피드 번호")
	private int feedId;
	@ApiModelProperty(value="아이디")
	private String userId; 
	@ApiModelProperty(value="닉네임")
	private String userName;
	@ApiModelProperty(value="댓글 내용")
	private String content; 
	@ApiModelProperty(value="작성 날짜")
	private String writeDate;
	@ApiModelProperty(value="댓글 작성자 프로필사진")
	private String userImg;
	

	public CommentDto() {
	}
	
	

	public CommentDto(int id, int feedId, String userId, String userName, String content, String writeDate,
			String userImg) {
		super();
		this.id = id;
		this.feedId = feedId;
		this.userId = userId;
		this.userName = userName;
		this.content = content;
		this.writeDate = writeDate;
		this.userImg = userImg;
	}



	public CommentDto(int id, int feedId, String userId, String userName, String content, String writeDate) {
		super();
		this.id = id;
		this.feedId = feedId;
		this.userId = userId;
		this.userName = userName;
		this.content = content;
		this.writeDate = writeDate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFeedId() {
		return feedId;
	}
	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}
	
	

	public String getUserImg() {
		return userImg;
	}



	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}



	@Override
	public String toString() {
		return "CommentDto [id=" + id + ", feedId=" + feedId + ", userId=" + userId + ", userName=" + userName
				+ ", content=" + content + ", writeDate=" + writeDate + ", userImg=" + userImg + "]";
	}
}
