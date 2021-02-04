package com.ssafy.artmate.controller;

import java.util.List;

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
	private ExhibitService exhibitService;
	
	//내 태그와 일치하는 전시회 목록 가져오기
	@ApiOperation(value = "내 태그와 일치하는 전시회 목록 가져오기(recommend)", notes = "전시회 목록 반환", response = ExhibitDto.class, responseContainer="List")
	@GetMapping(value="/exhibit/recommend/{userId}", produces = "text/json; charset=utf8")
	public String selectExhibitRecommend(@ApiParam(value="회원 아이디", required = true, example="aaaa@naver.com")@PathVariable String userId) {
		List<ExhibitDto> exhibits = exhibitService.selectExhibitMyTag(userId);
		for(ExhibitDto e:exhibits) {
			e.setTagList(exhibitService.selectExhibitTags(e.getId()));
		}
		Gson gs = new Gson();
		String result = gs.toJson(exhibits);
		return result;
	}
	//전체 전시회 목록 가져오기 (가나다 순, 열린 날짜 순, 마감 날짜 순)
	@ApiOperation(value = "전체 전시회 목록 가져오기", notes = "전시회 목록 반환", response = ExhibitDto.class, responseContainer="List")
	@GetMapping(value="/exhibit", produces = "text/json; charset=utf8")
	public String selectAllExhibit() {
		Gson gs = new Gson();
		String result = gs.toJson(exhibitService.selectAllExhibit());
		return result;
	}
	//선택한 전시회 상세정보 가져오기
	@ApiOperation(value = "선택한 전시회 상세정보 가져오기", notes = "전시회 상세정보 반환", response = ExhibitDto.class)
	@GetMapping(value="/exhibit/{id}")
	public ExhibitDto selectOneExhibit(@ApiParam(value="전시회 아이디", required = true, example="1")@PathVariable int id) {
		ExhibitDto result = exhibitService.selectOneExhibit(id);
		result.setTagList(exhibitService.selectExhibitTags(id));
		return result;
	}
	//주변에 위치한 전시회 목록 가져오기
	

}
