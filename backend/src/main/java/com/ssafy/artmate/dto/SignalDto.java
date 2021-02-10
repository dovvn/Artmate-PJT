package com.ssafy.artmate.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "- 알림 정보", description = "알림의 상세 정보를 나타낸다.")
public class SignalDto {
	@ApiModelProperty(value="알림 번호. primary key")
	private int id;
	@ApiModelProperty(value="알림 받을 아이디")
	private String getUserId;
	@ApiModelProperty(value="피드 알림에서 보내는 사람 아이디(ex. 'ooo'님이 팔로우합니다)")
	private String sendUserId;
	@ApiModelProperty(value="전시회 번호")
	private int exId;
	@ApiModelProperty(value="알림 받은 날짜")
	private String sigDate;
	@ApiModelProperty(value="알림 타입(0:전시회 알림, 1:피드 알림)")
	private int sigType;
	@ApiModelProperty(value="피드 알림 서브 타입(0:팔로우 알림, 1:피드 알림, 2:좋아요 알림)")
	private int subType;
	@ApiModelProperty(value="읽음 표시(0 : 안읽음, 1: 읽음)")
	private int read;
	@ApiModelProperty(value="알림 이미지")
	private String img;
	@ApiModelProperty(value="보내는 사람 닉네임(ex. 'ooo'님이 팔로우합니다)")
	private String sendUserName;
	@ApiModelProperty(value="전시회 이름")
	private String exName;
	
	
	public SignalDto() {}
	//전시회 알림
	public SignalDto(String getUserId, int exId, int sigType, int read, String img, String sigDate, String exName) {
		super();
		this.getUserId = getUserId;
		this.exId = exId;
		this.sigType=sigType;
		this.read = read;
		this.img = img;
		this.sigDate = sigDate;
		this.exName = exName;
	}
	//피드관련 알림
	public SignalDto(String getUserId, String sendUserId, int sigType, int subType, int read, String img, String sigDate, String sendUserName) {
		super();
		this.getUserId=getUserId;
		this.sendUserId = sendUserId;
		this.sigType=sigType;
		this.subType = subType;
		this.read = read;
		this.img = img;
		this.sigDate = sigDate;
		this.sendUserName = sendUserName;
	}
	//전체
	public SignalDto(int id, String userId, String sendUserId, int exId, String sigDate, int sigType, int subType,
			int read, String img, String sendUserName, String exName) {
		super();
		this.id = id;
		this.getUserId = userId;
		this.sendUserId = sendUserId;
		this.exId = exId;
		this.sigDate = sigDate;
		this.sigType = sigType;
		this.subType = subType;
		this.read = read;
		this.img = img;
		this.sendUserName = sendUserName;
		this.exName = exName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGetUserId() {
		return getUserId;
	}
	public void setGetUserId(String getUserId) {
		this.getUserId = getUserId;
	}
	public String getSendUserId() {
		return sendUserId;
	}
	public void setSendUserId(String sendUserId) {
		this.sendUserId = sendUserId;
	}
	public int getExId() {
		return exId;
	}
	public void setExId(int exId) {
		this.exId = exId;
	}
	public String getSigDate() {
		return sigDate;
	}
	public void setSigDate(String sigDate) {
		this.sigDate = sigDate;
	}
	public int getSigType() {
		return sigType;
	}
	public void setSigType(int sigType) {
		this.sigType = sigType;
	}
	public int getSubType() {
		return subType;
	}
	public void setSubType(int subType) {
		this.subType = subType;
	}
	public int getRead() {
		return read;
	}
	public void setRead(int read) {
		this.read = read;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	
	public String getSendUserName() {
		return sendUserName;
	}
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}
	
	public String getExName() {
		return exName;
	}
	public void setExName(String exName) {
		this.exName = exName;
	}
	@Override
	public String toString() {
		return "SignalDto [id=" + id + ", getUserId=" + getUserId + ", sendUserId=" + sendUserId + ", exId=" + exId
				+ ", sigDate=" + sigDate + ", sigType=" + sigType + ", subType=" + subType + ", read=" + read + ", img="
				+ img + ", sendUserName=" + sendUserName + ", exName=" + exName + "]";
	}
	
	
}
