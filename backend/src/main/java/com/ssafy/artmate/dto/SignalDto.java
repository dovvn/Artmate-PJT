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
	@ApiModelProperty(value="메세지에 참조된 번호(피드번호, 전시회번호)")
	private int messageId;
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
	@ApiModelProperty(value="전시회 설명")
	private String description;
	@ApiModelProperty(value="전시회 시작 날짜")
	private String startDate;
	@ApiModelProperty(value="전시회 끝나는 날짜")
	private String endDate;
	
	
	public SignalDto() {}
	//팔로우 알림
	public SignalDto(String getUserId, String sendUserId,String sigDate, int sigType, int subType, int read) {
		super();
		this.getUserId = getUserId;
		this.sendUserId = sendUserId;
		this.sigType=sigType;
		this.read = read;
		this.sigDate = sigDate;
		this.subType = subType;
	}
	//피드 관련 알림
	public SignalDto(String getUserId, String sendUserId, int messageId,String sigDate, int sigType, int subType, int read) {
		super();
		this.getUserId = getUserId;
		this.sendUserId = sendUserId;
		this.messageId = messageId;
		this.sigType=sigType;
		this.read = read;
		this.sigDate = sigDate;
		this.subType = subType;
	}
	//전시회 알림
		public SignalDto(String getUserId, int messageId,String sigDate, int sigType, int subType, int read) {
			super();
			this.getUserId = getUserId;
			this.messageId = messageId;
			this.sigType=sigType;
			this.read = read;
			this.sigDate = sigDate;
			this.subType = subType;
		}
	//전체
	public SignalDto(int id, String getUserId, String sendUserId, int messageId, String sigDate, int sigType,
			int subType, int read, String img, String sendUserName, String exName, String description, String startDate,
			String endDate) {
		super();
		this.id = id;
		this.getUserId = getUserId;
		this.sendUserId = sendUserId;
		this.messageId = messageId;
		this.sigDate = sigDate;
		this.sigType = sigType;
		this.subType = subType;
		this.read = read;
		this.img = img;
		this.sendUserName = sendUserName;
		this.exName = exName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
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
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int exId) {
		this.messageId = exId;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "SignalDto [id=" + id + ", getUserId=" + getUserId + ", sendUserId=" + sendUserId + ", messageId="
				+ messageId + ", sigDate=" + sigDate + ", sigType=" + sigType + ", subType=" + subType + ", read="
				+ read + ", img=" + img + ", sendUserName=" + sendUserName + ", exName=" + exName + ", description="
				+ description + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
}
