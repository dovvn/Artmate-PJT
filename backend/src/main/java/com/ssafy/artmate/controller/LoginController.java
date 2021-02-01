package com.ssafy.artmate.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.artmate.Dto.UserDto;
import com.ssafy.artmate.service.JwtService;
import com.ssafy.artmate.service.UserService;

<<<<<<< HEAD
=======
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

>>>>>>> develop
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/user")
public class LoginController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@ApiParam(value="사용자 객체", required = true)@RequestBody UserDto user, HttpServletResponse response, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = userService.selectWithIdAndPw(user);
			
			if(loginUser != null) {
				if(loginUser.getAccesskey().equals("y")) { // 인증키 확인 완료
					/* jwt.io에서 확인
					로그인 성공했다면 토큰을 생성한다.*/
					String token = jwtService.create(loginUser);
					logger.trace("로그인 토큰정보 : {}", token);
					//토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
					response.setHeader("auth-token", token);
					resultMap.put("auth-token", token); 
					resultMap.put("user-id", loginUser.getUserId());
					resultMap.put("user-name", loginUser.getUserName());
					resultMap.put("status", true);
<<<<<<< HEAD
					resultMap.put("data", loginUser);
=======
					loginUser.setMyTag(userService.selectMyTag(user.getUserId()));
					resultMap.put("user", loginUser); //회원 정보
					resultMap.put("message","로그인 성공"); //메세지
>>>>>>> develop
				}else { // 인증키 확인 불가
					resultMap.put("message", "로그인 실패 : 메일 인증을 진행해주세요.");
				}
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "로그인 실패 : 회원 정보를 찾을 수 없습니다.");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

}
