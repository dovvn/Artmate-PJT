package com.ssafy.artmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	public String selectExhibitRecommend(@ApiParam(value="로그인 된 아이디", required = true, example="jdaun.dev@gmail.com")@PathVariable String userId) {
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
	@GetMapping(value="/exhibit/{userId}", produces = "text/json; charset=utf8")
	public String selectAllExhibit(@ApiParam(value="로그인 된 아이디", required = true, example="jdaun.dev@gmail.com")@PathVariable String userId) {
		Gson gs = new Gson();
		String result = gs.toJson(exhibitService.selectAllExhibit(userId));
		return result;
	}
	//선택한 전시회 상세정보 가져오기
	@ApiOperation(value = "선택한 전시회 상세정보 가져오기", notes = "전시회 상세정보 반환", response = ExhibitDto.class)
	@GetMapping(value="/exhibit/{userId}/{id}")
	public ExhibitDto selectOneExhibit(@ApiParam(value="로그인 된 아이디", required = true, example="jdaun.dev@gmail.com")@PathVariable String userId, @ApiParam(value="전시회 아이디", required = true, example="1")@PathVariable int id) {
		ExhibitDto result = exhibitService.selectOneExhibit(userId, id);
		result.setTagList(exhibitService.selectExhibitTags(id));
		return result;
	}
	
	//지도에서 보여줄 모든 전시회 정보 가져오기
	@ApiOperation(value = "지도에서 보여줄 모든 전시회 정보(전시회아이디, 이름, 장소, 이미지, 시작날짜, 종료날짜) 전달", notes = "전시회 상세정보 반환", response = ExhibitDto.class, responseContainer="List")
	@GetMapping(value="/exhibit/map", produces = "text/json; charset=utf8")
	public String selectExhibitbyMap() {
		Gson gs = new Gson();
		String result = gs.toJson(exhibitService.selectExhibitbyMap());
		return result;
	}
	
	//스크랩북에 전시회 추가하기
	@ApiOperation(value = "선택한 전시회 스크랩북에 추가하기", notes = "성공적으로 추가하면 true, 실패하면 false", response = Boolean.class)
	@PutMapping(value="/scrapbook/{userId}/{id}")
	public boolean insertScrapbook(@ApiParam(value="로그인 된 아이디", required = true, example="jdaun.dev@gmail.com")@PathVariable String userId, @ApiParam(value="전시회 아이디", required = true, example="1")@PathVariable int id) {
		if(exhibitService.selectOneScrapbook(userId, id)!=null) return false; //이미 스크랩했으면 실패
		return exhibitService.insertScrapbook(userId, id);
	}
	//스크랩북에서 전시회 삭제하기
	@ApiOperation(value = "선택한 전시회 스크랩북에 삭제하기", notes = "성공적으로 삭제하면 true, 실패하면 false", response = Boolean.class)
	@DeleteMapping(value="/scrapbook/{userId}/{id}")
	public boolean deleteScrapbook(@ApiParam(value="로그인 된 아이디", required = true, example="jdaun.dev@gmail.com")@PathVariable String userId, @ApiParam(value="전시회 아이디", required = true, example="1")@PathVariable int id) {
		if(exhibitService.selectOneScrapbook(userId, id)==null) return false; //이미 스크랩에 없으면
		return exhibitService.deleteScrapbook(userId, id);
	}
	//내 스크랩북 목록 가져오기
	@ApiOperation(value = "스크랩북 목록 가져오기", notes = "전시회 리스트 반환", response = ExhibitDto.class, responseContainer="List")
	@GetMapping(value="/scrapbook/{userId}", produces = "text/json; charset=utf8")
	public String selectAllScrapbook(@ApiParam(value="로그인 된 아이디", required = true, example="jdaun.dev@gmail.com")@PathVariable String userId) {
		Gson gs = new Gson();
		String result = gs.toJson(exhibitService.selectAllScrapbook(userId));
		return result;
	}
	
}
