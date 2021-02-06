package com.ssafy.artmate.controller;

import java.awt.List;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.ssafy.artmate.dto.UserDto;
import com.ssafy.artmate.service.JwtService;
import com.ssafy.artmate.service.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/user")
public class LoginController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@ApiOperation(value = "로그인 정보를 반환한다.", response = List.class)
	@PostMapping("/loginzz")
	public ResponseEntity<Map<String, Object>> login(
			@ApiParam(value = "사용자 객체", required = true) @RequestBody UserDto user, HttpServletResponse response,
			HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.selectWithIdAndPw(user);
			if (loginUser != null) {
				if (loginUser.getAccesskey().equals("y")) { // 인증키 확인 완료
					/*
					 * jwt.io에서 확인 로그인 성공했다면 토큰을 생성한다.
					 */
					String token = jwtService.create(loginUser);
					logger.trace("로그인 토큰정보 : {}", token);
					// 토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
					response.setHeader("auth-token", token);
					resultMap.put("auth-token", token); // 토큰 값
					resultMap.put("status", true);
					loginUser.setMyTag(userService.selectMyTag(user.getUserId()));
					resultMap.put("user", loginUser); // 회원 정보
					resultMap.put("message", "로그인 성공"); // 메세지
				} else { // 인증키 확인 불가
					resultMap.put("message", "로그인 실패 : 메일 인증을 진행해주세요.");
				}
			} else { // 비번이 틀렸거나 가입 내역이 없음
				loginUser = userService.selectUserId(user.getUserId()); // 아이디로 회원 찾기
				if (loginUser != null) { // 비밀번호가 틀린 것
					resultMap.put("message", "로그인 실패 : 비밀번호가 틀렸습니다.");
				} else {
					resultMap.put("message", "로그인 실패 : 가입하지 않은 아이디입니다.");
				}
			}
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 카카오로 로그인
	@PostMapping("/login/kakao")
	public ResponseEntity<Map<String, Object>> kakaoLogin(@RequestBody String json, HttpServletResponse response) {
		Gson gson = new Gson();
		UserDto loginUser = gson.fromJson(json, UserDto.class);
		System.out.println(json);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		loginUser.setAccesskey("y");
		if (userService.selectUserId(loginUser.getUserId()) == null) { // 아직 db에 없다면 회원가입 시킴
			userService.insertKakao(loginUser);
		} else { // db에서 회원정보 가져오기
			loginUser = userService.selectUser(loginUser.getUserId());
		}

		String token = jwtService.create(loginUser);
		logger.trace("로그인 토큰정보 : {}", token);
		// 토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
		response.setHeader("auth-token", token);
		resultMap.put("auth-token", token); // 토큰 값
		resultMap.put("status", true);
		loginUser.setMyTag(userService.selectMyTag(loginUser.getUserId()));
		resultMap.put("user", loginUser); // 회원 정보
		resultMap.put("message", "로그인 성공했습니다."); // 메세지
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 구글로 로그인
	@PostMapping("/login/google")
	public ResponseEntity<Map<String, Object>> googleLogin(@RequestBody String json, HttpServletResponse response) {
		Gson gson = new Gson();
		UserDto loginUser = gson.fromJson(json, UserDto.class);
		System.out.println(json);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		loginUser.setAccesskey("y");
		if (userService.selectUserId(loginUser.getUserId()) == null) { // 아직 db에 없다면 회원가입 시킴
			userService.insertKakao(loginUser);
		} else { // db에서 회원정보 가져오기
			loginUser = userService.selectUser(loginUser.getUserId());
		}

		String token = jwtService.create(loginUser);
		logger.trace("로그인 토큰정보 : {}", token);
		// 토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
		response.setHeader("auth-token", token);
		resultMap.put("auth-token", token); // 토큰 값
		resultMap.put("status", true);
		loginUser.setMyTag(userService.selectMyTag(loginUser.getUserId()));
		resultMap.put("user", loginUser); // 회원 정보
		resultMap.put("message", "로그인 성공했습니다."); // 메세지
		status = HttpStatus.ACCEPTED;
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
}
