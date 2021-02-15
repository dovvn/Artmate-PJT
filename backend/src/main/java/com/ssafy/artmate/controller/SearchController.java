package com.ssafy.artmate.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.artmate.dto.SearchDto;
import com.ssafy.artmate.service.SearchService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Controller
public class SearchController {

	@Autowired
	private SearchService service;
	
	@ApiOperation(value = "전체 유저/전시회 리스트 리턴", notes = "전체 유저 검색 리스트, 전시회 검색 리스트 리턴(전시회는 일단 무조건 null)", response = Map.class)
	@GetMapping("/search/{keyword}")
	public  Map<String, Object> searchKeyword(@ApiParam(value="검색 키워드", required=true, example="인물화") @PathVariable String keyword) {
		Map<String, Object> resutMap = new HashMap<>();
		
		//1. 현재 키워드 검색 횟수 하나 카운팅
		SearchDto search = service.selectKeyword(keyword);
		if(search == null) {
			service.insertKeyword(keyword); //현재 테이블에 없는 키워드이면 넣어주고
			search = new SearchDto(keyword, 0);
		}
		search.setCnt(search.getCnt()+1);
		service.modifyKeywordCnt(search);//검색 횟수 업데이트
		
		
		//2. 유저 & 전시회 검색(★전시회 Sub3때 구현)
		resutMap.put("userResult", service.selectKeywordUser("%"+keyword+"%"));
		resutMap.put("exhibitResult", service.selectKeywordExhibit("%"+keyword+"%"));
		return resutMap;
	}
	
	@ApiOperation(value = "검색 페이지에서 인기 검색어 목록 출력", notes = "검색 리스트로 최대 5개까지 반환(위에서부터 검색횟수 내림차순)",  response = SearchDto.class, responseContainer="List")
	@GetMapping("/search/popular")
	public List<String> getPopularKeyword() {
		return service.selectPopular();
	}
	
	@ApiOperation(value = "db에 저장된 전체 검색어 리스트 출력", notes = "검색 리스트로 반환", response = SearchDto.class, responseContainer="List")
	@GetMapping("/search")
	public List<String> getAllKeywords() {
		return service.selectAllKeywords();
	}
}
