package com.ssafy.artmate.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.artmate.Dto.SignalDto;
import com.ssafy.artmate.Dto.UserDto;
import com.ssafy.artmate.service.AwsS3Service;
import com.ssafy.artmate.service.EmailService;
import com.ssafy.artmate.service.SignalService;
import com.ssafy.artmate.service.UserService;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
@Controller
@Api(value = "User ")
public class UserController {
	@Autowired
	private AwsS3Service awservice;
	@Autowired
	private UserService uservice;
	@Autowired
	private EmailService eservice;
	@Autowired
	private SignalService signalService;

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);

	// 닉네임 중복확인
	@ApiOperation(value = "닉네임 중복확인", notes = "닉네임 중복이면 false, 아니면 true 반환", response = Boolean.class)
	@GetMapping("/user/checkname/{userName}")
	public Boolean selectUserName(@ApiParam(value = "닉네임", required = true) @PathVariable String userName) {
		if (uservice.selectUserName(userName) == null)
			return true;
		return false;
	}

	// 회원가입
	@ApiOperation(value = "회원가입", notes = "가입 성공해서 인증 메일 보냈으면 true, 실패했으면(아이디 중복되는 경우) false 반환", response = Boolean.class)
	@PostMapping("/user")
	public Boolean insertUser(@ApiParam(value = "사용자 객체", required = true) @RequestBody UserDto user)
			throws MessagingException, javax.mail.MessagingException {
		if (uservice.selectUserId(user.getUserId()) != null)
			return false;
		user.setAccesskey(makeAccessKey()); // 랜덤 인증키 생성
		uservice.insertUser(user); // DB에 인증키 미리 넣어두기
		for(String tag:user.getMyTag()) {
			if(!uservice.insertMyTag(user.getUserId(), tag)) return false; //태그 저장 실패하면 false 리턴
		}

		// 가입인증 메일 발송
		StringBuffer emailcontent = new StringBuffer();
		emailcontent.append("<!DOCTYPE html>");
		emailcontent.append("<html>");
		emailcontent.append("<head>");
		emailcontent.append("</head>");
		emailcontent.append("<body>");
		emailcontent.append(" <div"
				+ "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #A593DF; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"
				+ "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"
				+ "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">ARTMATE</span><br />"
				+ "		<span style=\"color: #A593DF\">메일인증</span> 안내입니다." + "	</h1>\n"
				+ "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"
				+ user.getUserName() + "님 안녕하세요.<br />" + "		ARTMATE에 가입해 주셔서 진심으로 감사드립니다.<br />"
				+ "		아래 <b style=\"color: #A593DF\">'메일 인증'</b> 버튼을 클릭하여 회원가입을 완료해 주세요.<br />" + "		감사합니다."
				+ "	</p>" + "	<a style=\"color: #FFF; text-decoration: none; text-align: center;\""
				+ "	href=\"http://localhost:7777/api/user/verifyJoin?userId=" + user.getUserId() + "&accesskey="
				+ user.getAccesskey() + "\" target=\"_blank\">" + "		<p"
				+ "			style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #A593DF; line-height: 45px; vertical-align: middle; font-size: 16px;\">"
				+ "			메일 인증</p>" + "	</a>" + "	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>"
				+ " </div>");
		emailcontent.append("</body>");
		emailcontent.append("</html>");
		eservice.sendMail(user.getUserId(), "[ARTMATE] 회원가입 인증 메일입니다.", emailcontent.toString());
		return true;
	}

	// 10자리 인증키(랜덤값) 생성
	private String makeAccessKey() {
		String alpha_lower = "abcdefghijklmnopqrstuvwxyz";
		Random random = new Random();
		StringBuilder sb = new StringBuilder();

		String randomString = alpha_lower + alpha_lower.toUpperCase() + "0123456789";
		for (int i = 0; i < 10; i++)
			sb.append(randomString.charAt(random.nextInt(randomString.length())));
		return sb.toString();
	}

	// 회원 정보 수정
	@ApiOperation(value = "회원정보 수정", notes = "회원정보 수정 성공하면 이미지url, 실패하면 fail(문자열)반환 / formData에 담아서 보내주세욤", response = Boolean.class)
	@PutMapping("/user")
	public String modifyUser(
			@ApiParam(value = "사용자 객체(user)", required = true) @RequestPart(value = "user", required = true) UserDto user,
			@ApiParam(value = "프로필사진(file)", required = true) @RequestPart(value = "file", required = false) MultipartFile file)
			throws IOException {
		String url=null;
		System.out.println("file : "+ file);
		System.out.println(user.getUserImg()+ " "+uservice.selectUserImg(user.getUserId()));
		if(file != null) { //파일이 있을때만
			url = awservice.uploadObject(file, file.getOriginalFilename(), "user"); // aws s3에 이미지 업로드 후 url리턴
			user.setUserImg(url);// 주소 바꿈
			uservice.modifyUserImg(user); //유저 이미지 변경
		}else {
			if(user.getUserImg().equals(uservice.selectUserImg(user.getUserId()))) {
				url = user.getUserImg();
			}else {
				user.setUserImg(url);
				uservice.modifyUserImg(user); //유저 이미지 변경
			}
		}
		if (uservice.modifyUserInfo(user)) { //유저 정보 변경(이미지 빼고)
			if(uservice.selectMyTag(user.getUserId()).size()!=0) { //태그가 이전에 하나라도 있었으면
				if(!uservice.deleteMyTag(user.getUserId())) return "fail"; //저장되어 있는 태그들 삭제 시도 (실패하면 fail)
			}
			if(user.getMyTag() != null) {
				for(String tag:user.getMyTag()) {//입력으로 들어온 태그들 저장 시도 (실패하면 fail)
					if(!uservice.insertMyTag(user.getUserId(),tag)) return "fail";
				}
			}
			System.out.println("url : "+ url);
			return url; //db 업데이트  
		}
		return "fail";
	}

	// 회원 탈퇴
	@ApiOperation(value = "회원정보 탈퇴", notes = "회원정보 탈퇴 성공했으면 true, 실패했으면 false 반환", response = Boolean.class)
	@DeleteMapping("/user/{userId}")
	public Boolean deleteUser(@ApiParam(value = "사용자 아이디", required = true) @PathVariable String userId) {
		// 서버로부터 이미지 삭제하기 위해 이미지 주소 가져오기
		String url = uservice.selectUserImg(userId);
		awservice.deleteObject(url, "user");
		if (uservice.deleteUser(userId))
			return true;
		return false;
	}

	// 비밀번호 찾기
	@ApiOperation(value = "비밀번호 찾기", notes = "가입된 계정이면 임시 비밀번호 메일 발송 후 true 반환, 가입된 계정이 아니면 false 반환")
	@GetMapping("/user/findPw/{userId}")
	public Boolean findPw(@PathVariable("userId") String userId)
			throws javax.mail.MessagingException, MessagingException {
		System.out.println(userId);
		UserDto user = uservice.selectUserId(userId);
		if (user == null)
			return false;
		user.setUserPw(makeAccessKey()); // 랜덤 비밀번호 생성

		// db 임시비번으로 변경
		uservice.modifyUserPw(user);

		// 임시비번 메일 발송
		StringBuffer emailcontent = new StringBuffer();
		emailcontent.append("<!DOCTYPE html>");
		emailcontent.append("<html>");
		emailcontent.append("<head>");
		emailcontent.append("</head>");
		emailcontent.append("<body>");
		emailcontent.append(" <div"
				+ "	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #A593DF; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"
				+ "	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"
				+ "		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">ARTMATE</span><br />"
				+ "		<span style=\"color: #A593DF\">임시 비밀번호</span> 생성" + "	</h1>\n"
				+ "	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"
				+ user.getUserName() + "님의<br />" + "		임시 비밀번호 생성 안내를 위해 발송된 메일입니다.<br />"
				+ "임시 비밀번호는 <b style=\"color: #A593DF\">" + user.getUserPw() + "</b> 입니다.<br />"
				+ "		임시 비밀번호로 로그인 후 비밀번호를 변경해주시길 바랍니다." + "	</p></div>");
		emailcontent.append("</body>");
		emailcontent.append("</html>");
		eservice.sendMail(user.getUserId(), "[ARTMATE] 비밀번호 인증 메일입니다.", emailcontent.toString());
		return true;
	}

	@ApiOperation(value = "본인 인증 메일 처리(*axios 요청X)", notes = "가입 인증 메일로 보낸 아이디,인증키와 db에 저장된 아이디,인증키 일치하는지 확인 후 일치하면 인증키 y로 바꿈")
	@GetMapping(value = "/user/verifyJoin")
	@Transactional
	public void checkJoinMail(HttpServletRequest request, HttpServletResponse response, UserDto user)
			throws MessagingException, IOException {
		response.setContentType("text/html; charset=euc-kr");
		response.setCharacterEncoding("euc-kr");
		PrintWriter out = response.getWriter();
		String content = "";

		if (uservice.selectUseridAndAccesskey(user.getUserId(), user.getAccesskey()) != null) { // 찾았다면
			user.setAccesskey("y");
			uservice.modifyAccesskey(user); // 본인인증완료 표시로 인증키 y 업데이트
			System.out.println("==================본인 인증 완료!==================");

			// 팝업창 띄우기
			content = "본인 인증이 완료되었습니다. 서비스로 돌아가 로그인 해주세요";

		} else {
			System.out.println("================== 만료된 인증키 접근 ==================");
			content = "이미 만료된 인증키입니다.";
		}
		out.println("<script>alert('" + content + "'); window.close();</script>");
		out.flush();
	}

	// 팔로우 하기
	@ApiOperation(value = "팔로우(* 마이피드에서 사용)", notes = "팔로우에 성공하면 true, 팔로우에 실패하면 false 반환")
	@PutMapping(value = "/user/follow/{sendUserId}/{getUserId}")
	public Boolean insertFollow(
			@ApiParam(value = "로그인 된 사용자 아이디", required = true, example = "aaaa@naver.com") @PathVariable("sendUserId") String sendUserId,
			@ApiParam(value = "팔로우 할 아이디", required = true) @PathVariable("getUserId") String getUserId) {
		if(sendUserId.equals(getUserId)) return false; //둘다 같은 아이디가 왔다면 false리턴
		if(uservice.selectFollowState(sendUserId, getUserId)) return false; //이미 팔로우중이면 false리턴
		return uservice.insertFollow(sendUserId, getUserId); //팔로우 추가
	}

	// 언팔로우 하기
	@ApiOperation(value = "언팔로우(* 마이피드에서 사용)", notes = "언팔로우에 성공하면 true, 팔로우에 실패하면 false 반환")
	@DeleteMapping(value = "/user/follow/{sendUserId}/{getUserId}")
	public Boolean deleteFollow(
			@ApiParam(value = "로그인 된 사용자 아이디", required = true, example = "aaaa@naver.com") @PathVariable("sendUserId") String sendUserId,
			@ApiParam(value = "언팔로우 할 아이디", required = true) @PathVariable("getUserId") String getUserId) {
		if(sendUserId.equals(getUserId)) return false; //둘다 같은 아이디가 왔다면 false리턴
		if(!uservice.selectFollowState(sendUserId, getUserId)) return false; //아직 팔로우하지 않았다면 false리턴
		return uservice.deleteFollow(sendUserId, getUserId);
	}

	// 팔로워 리스트 가져오기
	@ApiOperation(value = "팔로워 리스트 반환(* 마이피드에서 사용)", notes = "나를 팔로우한 팔로워들의 리스트 반환", response = UserDto.class, responseContainer = "List")
	@GetMapping(value = "/user/follower/{userId}")
	public List<UserDto> selectFollower(
			@ApiParam(value = "로그인 된 사용자 아이디", required = true, example = "aaaa@naver.com") @PathVariable("userId") String userId) {
		return uservice.selectFollower(userId);
	}

	// 팔로잉 리스트 가져오기
	@ApiOperation(value = "팔로잉 리스트 반환(* 마이피드에서 사용)", notes = "내가 팔로우한 팔로잉들의 리스트 반환", response = UserDto.class, responseContainer = "List")
	@GetMapping(value = "/user/following/{userId}")
	public List<UserDto> selectFollowing(
			@ApiParam(value = "로그인 된 사용자 아이디", required = true, example = "aaaa@naver.com") @PathVariable("userId") String userId) {
		return uservice.selectFollowing(userId);
	}

	// 회원 정보 가져오기
	@ApiOperation(value = "현재 아이디의 회원정보 반환(* 마이피드에서 사용)", notes = "회원정보 리스트 반환", response = UserDto.class, responseContainer = "List")
	@GetMapping(value = "/user/{userId}")
	public UserDto selectUser(
			@ApiParam(value = "로그인 된 사용자 아이디", required = true, example = "aaaa@naver.com") @PathVariable("userId") String userId) {
		UserDto user = uservice.selectUser(userId);
		user.setMyTag(uservice.selectMyTag(user.getUserId()));
		return user;
	}

	// 팔로우했는지 상태 가져오기
	@ApiOperation(value = "현재 로그인한 회원(sendUserId)이 이 회원(getUserId)을 팔로우했는지 상태 확인하기(* 마이피드에서 사용)", notes = "팔로우 중이면 true, 안했으면 false 반환", response = UserDto.class, responseContainer = "List")
	@GetMapping(value = "/user/follow/{sendUserId}/{getUserId}")
	public boolean selectFollowState(
			@ApiParam(value = "로그인 된 사용자 아이디", required = true, example = "aaaa@naver.com") @PathVariable("sendUserId") String sendUserId, @ApiParam(value = "현재 피드의 사용자 아이디", required = true, example = "unni2@naver.com") @PathVariable("getUserId") String getUserId) {
		if(sendUserId.equals(getUserId)) return false; //둘다 같은 아이디가 왔다면 false리턴
		return uservice.selectFollowState(sendUserId, getUserId);
	}
	
	//회원 이미지 기본으로 변경하기
	@ApiOperation(value = "회원 프로필 이미지 기본(null)으로 변경하기", notes = "프로필 이미지 변경(null)에 성공하면 true, 실패하면 false 반환", response = Boolean.class)
	@PutMapping(value="/user/{userId}")
	public boolean initUserImg(@ApiParam(value = "로그인 된 사용자 아이디", required = true, example = "aaaa@naver.com") @PathVariable("userId") String userId) {
		UserDto user = uservice.selectUser(userId);
		user.setUserImg(null); //이미지 null로 지정
		return uservice.modifyUserImg(user);
	}
	
}
