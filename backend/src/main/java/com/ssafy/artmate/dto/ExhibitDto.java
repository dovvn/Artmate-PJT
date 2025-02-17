package com.ssafy.artmate.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "- 전시회 정보", description = "전시회 정보를 나타낸다.")
public class ExhibitDto{
	@ApiModelProperty(value="전시회 번호. primary key")
	private int id;
	@ApiModelProperty(value="전시회 태그이름 리스트 (문자열, null가능)")
	private List<String> tagList;
	@ApiModelProperty(value="전시회 제목[*온라인]")
	private String name;
	@ApiModelProperty(value="전시회 장소")
	private String location;
	@ApiModelProperty(value="전시회 설명 (null가능) [*온라인]")
	private String description;
	@ApiModelProperty(value="전시회 이미지 주소 [*온라인]")
	private String exImg;
	@ApiModelProperty(value="전시회 시작 날짜")
	private String startDate;
	@ApiModelProperty(value="전시회 종료 날짜")
	private String endDate;
	@ApiModelProperty(value="작가 (null가능) [*온라인]")
	private String artist;
	@ApiModelProperty(value="전시회 일련번호")
	private String seqNum;
	@ApiModelProperty(value="스크랩 수")
	private int scrapCnt;
	@ApiModelProperty(value="스크랩 표시")
	private int scrapmark;
	@ApiModelProperty(value="이 전시회에 작성된 피드 수")
	private int feedCnt;
	@ApiModelProperty(value="vr링크(온라인전시회) [*온라인]")
	private String vrLink;
	
	public ExhibitDto() {}
	
	public ExhibitDto(String name, String description, String exImg, String artist, String vrLink) {
		super();
		this.name = name;
		this.description = description;
		this.exImg = exImg;
		this.artist = artist;
		this.vrLink = vrLink;
	}
	
	public ExhibitDto(int id, List<String> tagList, String name, String location, String description, String exImg,
			String startDate, String endDate, String artist, String seqNum, int scrapCnt, int scrapmark, int feedCnt) {
		super();
		this.id = id;
		this.tagList = tagList;
		this.name = name;
		this.location = location;
		this.description = description;
		this.exImg = exImg;
		this.startDate = startDate;
		this.endDate = endDate;
		this.artist = artist;
		this.seqNum = seqNum;
		this.scrapCnt = scrapCnt;
		this.scrapmark = scrapmark;
		this.feedCnt = feedCnt;
	}
	
	

	public ExhibitDto(int id, List<String> tagList, String name, String location, String description, String exImg,
			String startDate, String endDate, String artist, String seqNum, int scrapCnt, int scrapmark, int feedCnt,
			String vrLink) {
		super();
		this.id = id;
		this.tagList = tagList;
		this.name = name;
		this.location = location;
		this.description = description;
		this.exImg = exImg;
		this.startDate = startDate;
		this.endDate = endDate;
		this.artist = artist;
		this.seqNum = seqNum;
		this.scrapCnt = scrapCnt;
		this.scrapmark = scrapmark;
		this.feedCnt = feedCnt;
		this.vrLink = vrLink;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExImg() {
		return exImg;
	}

	public void setExImg(String exImg) {
		this.exImg = exImg;
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

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getSeqNum() {
		return seqNum;
	}

	public void setSeqNum(String seqNum) {
		this.seqNum = seqNum;
	}
	
	public int getScrapCnt() {
		return scrapCnt;
	}

	public void setScrapCnt(int scrapCnt) {
		this.scrapCnt = scrapCnt;
	}

	public int getScrapmark() {
		return scrapmark;
	}

	public void setScrapmark(int scrapmark) {
		this.scrapmark = scrapmark;
	}
	
	public int getFeedCnt() {
		return feedCnt;
	}

	public void setFeedCnt(int feedCnt) {
		this.feedCnt = feedCnt;
	}
	
	

	public String getVrLink() {
		return vrLink;
	}

	public void setVrLink(String vrLink) {
		this.vrLink = vrLink;
	}

	@Override
	public String toString() {
		return "ExhibitDto [id=" + id + ", tagList=" + tagList + ", name=" + name + ", location=" + location
				+ ", description=" + description + ", exImg=" + exImg + ", startDate=" + startDate + ", endDate="
				+ endDate + ", artist=" + artist + ", seqNum=" + seqNum + ", scrapCnt=" + scrapCnt + ", scrapmark="
				+ scrapmark + ", feedCnt=" + feedCnt + ", vrLink=" + vrLink + "]";
	}
}
