package com.ssafy.artmate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ssafy.artmate.dto.ExhibitDto;
import com.ssafy.artmate.service.ExhibitService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Controller
@Api(value = "Exhibition ")
public class ExhibitController {
	
	@Autowired
	private static ExhibitService exhibitService;
	
	//내 태그와 일치하는 전시회 목록 가져오기
	@ApiOperation(value = "내 태그와 일치하는 전시회 목록 가져오기", notes = "전시회 목록 반환", response = ExhibitDto.class, responseContainer="List")
	@GetMapping("/exhibit/recommend/{userId}")
	public static String selectExhibitRecommend(@ApiParam(value="회원 아이디", required = true, example="aaaa@naver.com")@PathVariable String userId) {
		Gson gs = new Gson();
		String result = gs.toJson(exhibitService.selectExhibitMyTag(userId));
		return result;
	}
	//전체 전시회 목록 가져오기 (가나다 순, 열린 날짜 순, 마감 날짜 순)
	@ApiOperation(value = "모든 전시회 목록 가져오기", notes = "전시회 목록 반환", response = ExhibitDto.class, responseContainer="List")
	@GetMapping("/exhibit")
	public static String selectAllExhibit() {
		Gson gs = new Gson();
		String result = gs.toJson(exhibitService.selectAllExhibit());
		return result;
	}
	//선택한 전시회 상세정보 가져오기
	@ApiOperation(value = "선택한 전시회 상세정보 가져오기", notes = "전시회 상세정보 반환", response = ExhibitDto.class)
	@GetMapping("/exhibit/{id}")
	public static ExhibitDto selectOneExhibit(@ApiParam(value="전시회 아이디", required = true, example="1")@PathVariable int id) {
		return exhibitService.selectOneExhibit(id);
	}
	//주변에 위치한 전시회 목록 가져오기
	

}
