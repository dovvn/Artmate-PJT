package com.ssafy.artmate.Dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "- 전시회 정보", description = "전시회 정보를 나타낸다.")
public class ExhibitDto{
	@ApiModelProperty(value="전시회 번호. primary key")
	private int id;
	@ApiModelProperty(value="전시회 태그 번호 리스트 (null가능)")
	private List<Integer> tagIdList;
	@ApiModelProperty(value="전시회 제목")
	private String name;
	@ApiModelProperty(value="전시회 장소")
	private String location;
	@ApiModelProperty(value="전시회 설명 (null가능)")
	private String description;
	@ApiModelProperty(value="전시회 이미지 주소")
	private String exImg;
	@ApiModelProperty(value="전시회 시작 날짜")
	private String startDate;
	@ApiModelProperty(value="전시회 종료 날짜")
	private String endDate;
	@ApiModelProperty(value="작가 (null가능)")
	private String artist;
	@ApiModelProperty(value="전시회 일련번호")
	private String seqNum;
	
	public ExhibitDto() {}

	public ExhibitDto(int id, String name, String location, String description, String exImg, String startDate,
			String endDate, String artist, String seqNum) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.description = description;
		this.exImg = exImg;
		this.startDate = startDate;
		this.endDate = endDate;
		this.artist = artist;
		this.seqNum = seqNum;
	}



	public ExhibitDto(int id, List<Integer> tagIdList, String name, String location, String description, String exImg,
			String startDate, String endDate, String artist, String seqNum) {
		super();
		this.id = id;
		this.tagIdList = tagIdList;
		this.name = name;
		this.location = location;
		this.description = description;
		this.exImg = exImg;
		this.startDate = startDate;
		this.endDate = endDate;
		this.artist = artist;
		this.seqNum = seqNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getTagIdList() {
		return tagIdList;
	}

	public void setTagIdList(List<Integer> tagIdList) {
		this.tagIdList = tagIdList;
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

	@Override
	public String toString() {
		return "ExhibitDto [id=" + id + ", tagIdList=" + tagIdList + ", name=" + name + ", location=" + location
				+ ", description=" + description + ", exImg=" + exImg + ", startDate=" + startDate + ", endDate="
				+ endDate + ", artist=" + artist + ", seqNum=" + seqNum + "]";
	}
}
