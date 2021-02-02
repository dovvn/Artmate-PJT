package com.ssafy.artmate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ssafy.artmate.Dto.SignalDto;
import com.ssafy.artmate.service.SignalService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value="Signal")
public class SignalController {
	
	@Autowired
	private SignalService signalService;
	
	@ApiOperation(value="알림 삭제", notes="알림 삭제 성공시 true, 알림 전송 실패시 false 반환", response = Boolean.class)
	@DeleteMapping(value="/signal/{id}")
	public boolean deleteSignal(@ApiParam(value="알림 아이디", required=true, example="1")@PathVariable("id") int id) {
		return signalService.deleteSignal(id);
	}
	
	@ApiOperation(value="전체 알림 목록 반환", notes="알림 리스트 반환",  response = SignalDto.class, responseContainer="List")
	@GetMapping(value="/signal/{getUserId}", produces = "text/json; charset=utf8")
	public String seletctAllSignal(@ApiParam(value="회원 아이디", required=true, example="aaaa@naver.com")@PathVariable("getUserId") String getUserId) {
		Gson gs = new Gson();
		String result = gs.toJson(signalService.selectAllSignal(getUserId));
		return result;
	}
	
	@ApiOperation(value="알림 확인 (read 값 변경)", notes="알림 확인 성공시 true, 알림 확인 실패시 false 반환", response = Boolean.class)
	@PutMapping(value="/signal/{id}")
	public boolean checkSignal(@ApiParam(value="알림 아이디", required=true, example="1")@PathVariable("id") int id) {
		return signalService.modifySignal(id);
	}
	
	
}
