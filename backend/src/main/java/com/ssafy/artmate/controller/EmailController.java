package com.ssafy.artmate.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.artmate.Dto.UserDto;
import com.ssafy.artmate.service.EmailService;
import com.ssafy.artmate.service.UserService;
import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Controller
public class EmailController {
	@Autowired
	private EmailService eservice;
	@Autowired
	private UserService uservice;
	
	//아이디,인증키 가지고 메일 발송
	@PostMapping("/email/send")
	public void sendMail(@RequestBody UserDto user) throws javax.mail.MessagingException, MessagingException{
		System.out.println("메일발송: "+user.toString());
		StringBuffer emailcontent = new StringBuffer();
		emailcontent.append("<!DOCTYPE html>");
		emailcontent.append("<html>");
		emailcontent.append("<head>");
		emailcontent.append("</head>");
		emailcontent.append("<body>");
		emailcontent.append(
				" <div" 																																																	+ 
				"	style=\"font-family: 'Apple SD Gothic Neo', 'sans-serif' !important; width: 400px; height: 600px; border-top: 4px solid #A593DF; margin: 100px auto; padding: 30px 0; box-sizing: border-box;\">"		+ 
				"	<h1 style=\"margin: 0; padding: 0 5px; font-size: 28px; font-weight: 400;\">"																															+ 
				"		<span style=\"font-size: 15px; margin: 0 0 10px 3px;\">ARTMATE</span><br />"																													+ 
				"		<span style=\"color: #A593DF\">메일인증</span> 안내입니다."																																				+ 
				"	</h1>\n"																																																+ 
				"	<p style=\"font-size: 16px; line-height: 26px; margin-top: 50px; padding: 0 5px;\">"																													+ 
				user.getUserName()																																																+
				"		님 안녕하세요.<br />"																																													+ 
				"		ARTMATE에 가입해 주셔서 진심으로 감사드립니다.<br />"																																						+ 
				"		아래 <b style=\"color: #A593DF\">'메일 인증'</b> 버튼을 클릭하여 회원가입을 완료해 주세요.<br />"																													+ 
				"		감사합니다."																																															+ 
				"	</p>"																																																	+ 
				"	<a style=\"color: #FFF; text-decoration: none; text-align: center;\""																																	+
				"	href=\"http://localhost:7777/user/email/verify?userId=" + user.getUserId() + "&accesskey=" + user.getAccesskey() + "\" target=\"_blank\">"														+ 
				"		<p"																																																	+
				"			style=\"display: inline-block; width: 210px; height: 45px; margin: 30px 5px 40px; background: #A593DF; line-height: 45px; vertical-align: middle; font-size: 16px;\">"							+ 
				"			메일 인증</p>"																																														+ 
				"	</a>"																																																	+
				"	<div style=\"border-top: 1px solid #DDD; padding: 5px;\"></div>"																																		+
				" </div>"
		);
		emailcontent.append("</body>");
		emailcontent.append("</html>");
		eservice.sendMail(user.getUserId(), "[ARTMATE] 회원가입 인증 메일입니다.", emailcontent.toString());
	}
	
	//메일로 보낸 아이디,인증키와 db에 저장된 아이디,인증키 일치하는지 확인
	@GetMapping(value = "/email/verify")
	@Transactional
	public void checkmail(HttpServletRequest request, UserDto user) throws MessagingException {
		if(uservice.selectUseridAndAccesskey(user.getUserId(), user.getAccesskey()) != null){ //찾았다면
			user.setAccesskey("y");
			uservice.modifyAccesskey(user);
			System.out.println("==================본인 인증 완료!==================");
		}else System.out.println("================== 본인 인증 실패! ==================");
		
	}
}
