package com.ssafy.artmate.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "- 피드 정보", description = "피드의 상세 정보를 나타낸다.")
public class FeedDto {
	@ApiModelProperty(value="피드 번호. primary key")
	private int id;
	@ApiModelProperty(value="피드 작성자 닉네임")
	private String userName;
	@ApiModelProperty(value="피드 작성자 프로필사진")
	private String userImg;
	@ApiModelProperty(value="피드 이미지")
	private String feedImg;
	@ApiModelProperty(value="피드 내용")
	private String feedText;
	@ApiModelProperty(value="피드에 참조된 전시회 번호")
	private int exId;
	@ApiModelProperty(value="피드 좋아요 수")
	private int likeCnt;
	@ApiModelProperty(value="피드 댓글 수")
	private int commentCnt;
	@ApiModelProperty(value="피드 작성 일자")
	private String writeDate;
	@ApiModelProperty(value="피드에 참조된 장소")
	private String location;
	@ApiModelProperty(value="피드 작성자 아이디")
	private String userId;
	@ApiModelProperty(value="좋아요 여부(눌렀으면 1, 안눌렀으면 0)")
	private int likemark;
	@ApiModelProperty(value="북마크 여부(눌렀으면 1, 안눌렀으면 0)")
	private int bookmark;
	@ApiModelProperty(value="전시회 이름")
	private String exName;
	
	public FeedDto() {}
	
	public FeedDto(int id, String feedImg) {
		super();
		this.id = id;
		this.feedImg = feedImg;
	}

	public FeedDto(int id, String userName, String userImg, String feedImg, String feedText, int exId, int likeCnt,
			int commentCnt, String writeDate, String location, String userId, int likemark, int bookmark,String exName) {
		super();
		this.id = id;
		this.userName = userName;
		this.userImg = userImg;
		this.feedImg = feedImg;
		this.feedText = feedText;
		this.exId = exId;
		this.likeCnt = likeCnt;
		this.commentCnt = commentCnt;
		this.writeDate = writeDate;
		this.location = location;
		this.userId = userId;
		this.likemark = likemark;
		this.bookmark = bookmark;
		this.exName = exName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getFeedImg() {
		return feedImg;
	}

	public void setFeedImg(String feedImg) {
		this.feedImg = feedImg;
	}

	public String getFeedText() {
		return feedText;
	}

	public void setFeedText(String feedText) {
		this.feedText = feedText;
	}

	public int getExId() {
		return exId;
	}

	public void setExId(int exId) {
		this.exId = exId;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public int getCommentCnt() {
		return commentCnt;
	}

	public void setCommentCnt(int commentCnt) {
		this.commentCnt = commentCnt;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getLikemark() {
		return likemark;
	}

	public void setLikemark(int likemark) {
		this.likemark = likemark;
	}

	public int getBookmark() {
		return bookmark;
	}

	public void setBookmark(int bookmark) {
		this.bookmark = bookmark;
	}

	public String getExName() {
		return exName;
	}

	public void setExName(String exName) {
		this.exName = exName;
	}

	@Override
	public String toString() {
		return "FeedDto [id=" + id + ", userName=" + userName + ", userImg=" + userImg + ", feedImg=" + feedImg
				+ ", feedText=" + feedText + ", exId=" + exId + ", likeCnt=" + likeCnt + ", commentCnt=" + commentCnt
				+ ", writeDate=" + writeDate + ", location=" + location + ", userId=" + userId + ", likemark="
				+ likemark + ", bookmark=" + bookmark + ", exName=" + exName + "]";
	}

}
