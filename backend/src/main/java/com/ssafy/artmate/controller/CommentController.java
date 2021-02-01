package com.ssafy.artmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.ssafy.artmate.Dto.CommentDto;
import com.ssafy.artmate.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api")
@Api(value="Comment")
public class CommentController {

	@Autowired
	private CommentService cservice;

	// 댓글 작성
	@ApiOperation(value="댓글 작성", notes="댓글 작성 성공시 true, 댓글 작성 실패시 false 반환", response = Boolean.class)
	@PostMapping(value = "/comment")
	public Boolean insertComment (@ApiParam(value="댓글 객체", required=true) @RequestBody CommentDto comment) {
		return cservice.insertComment(comment);
	}

	// 댓글 목록
	@ApiOperation(value="현재 게시물 번호의 모든 댓글 목록을 반환", notes="댓글 리스트 반환", response = CommentDto.class, responseContainer="List")
	@GetMapping(value = "/comment/{feedId}", produces = "text/json; charset=utf8")
	public String selectCommentList(@ApiParam(value="페이지 번호", required = true, example="1") @PathVariable("feedId") int feedId) {
		List<CommentDto> commentList = cservice.selectCommentList(feedId);
		Gson gs = new Gson();
		String result = gs.toJson(commentList);
		return result;
	}

	// 댓글 수정
	@ApiOperation(value="번호에 해당하는 댓글 내용 수정", notes="댓글 수정 성공시 true, 댓글 수정 실패시 false 반환", response = Boolean.class)
	@PutMapping("/comment")
	public Boolean modifyComment(@ApiParam(value="댓글 객체", required=true) @RequestBody CommentDto comment) {
		return cservice.modifyComment(comment);
	}

	// 댓글 삭제
	@ApiOperation(value = "번호에 해당하는 댓글 삭제", notes="댓글 삭제 성공시 ture, 댓글 삭제 실패시 false 반환", response = Boolean.class)
	@DeleteMapping("/comment/{id}")
	public Boolean deleteComment(@ApiParam(value="댓글 번호", required=true, example="1") @PathVariable int id){
		return cservice.deleteComment(id);
	}

}
