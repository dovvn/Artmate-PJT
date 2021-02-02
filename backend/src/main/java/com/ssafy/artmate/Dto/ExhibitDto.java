package com.ssafy.artmate.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "- 전시회 정보", description = "전시회 정보를 나타낸다.")
public class ExhibitDto{
	@ApiModelProperty(value="전시회 번호. primary key")
	private int id;
	@ApiModelProperty(value="전시회 제목")
	private String name;
	@ApiModelProperty(value="전시회 장소")
	private String location;
	@ApiModelProperty(value="전시회 설명")
	private String description;
	@ApiModelProperty(value="전시회 이미지")
	private String exImg;
	@ApiModelProperty(value="전시회 시작 날짜")
	private String startDate;
	@ApiModelProperty(value="전시회 종료 날짜")
	private String endDate;
}
