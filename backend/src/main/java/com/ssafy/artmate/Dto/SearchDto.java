package com.ssafy.artmate.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "- 검색 정보", description = "검색 정보를 나타낸다.")
public class SearchDto {
	@ApiModelProperty(value="검색 고유 번호. primary key")
	private int id; //검색 고유번호
	@ApiModelProperty(value="검색 키워드.")
	private String keyword; //검색 키워드
	@ApiModelProperty(value="검색 횟수.")
	private int cnt; //검색 횟수
	
	public SearchDto() {}
	
	public SearchDto(String keyword, int cnt) {
		super();
		this.keyword = keyword;
		this.cnt = cnt;
	}
	
	public SearchDto(int id, String keyword, int cnt) {
		super();
		this.id = id;
		this.keyword = keyword;
		this.cnt = cnt;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "SearchDto [id=" + id + ", keyword=" + keyword + ", cnt=" + cnt + "]";
	}
}
