package com.ssafy.artmate.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sun.xml.messaging.saaj.packaging.mime.MessagingException;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public EmailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMail(String toEmail, String subject, String message) throws MessagingException, javax.mail.MessagingException{
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		helper.setFrom("artmate"); //보내는 사람
		helper.setTo(toEmail); //받는 사람
		helper.setSubject(subject); //메일 제목
		helper.setText(message, true); //true를 넣을 경우 html
		
		javaMailSender.send(mimeMessage);
	}
	
}
