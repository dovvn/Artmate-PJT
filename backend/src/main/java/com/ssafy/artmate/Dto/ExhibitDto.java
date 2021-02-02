package com.ssafy.artmate.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "- 전시회 정보", description = "전시회 정보를 나타낸다.")
public class ExhibitDto{
	@ApiModelProperty(value="전시회 번호. exhibit key")
	private int id;
	
}
