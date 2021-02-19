package com.ssafy.artmate.dto;

public class MyFileDto {
	private int fno; //파일 번호
	private String origin; // s3 서버쪽에 저장된 url
	
	public MyFileDto(){}
	
	public MyFileDto(String origin) {
		this.origin = origin;
	}

	public MyFileDto(int fno, String origin) {
		super();
		this.fno = fno;
		this.origin = origin;
	}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
}
