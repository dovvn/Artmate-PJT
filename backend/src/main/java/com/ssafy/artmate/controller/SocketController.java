package com.ssafy.artmate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ssafy.artmate.Dto.FeedDto;
import com.ssafy.artmate.Dto.SignalDto;
import com.ssafy.artmate.Dto.UserDto;
import com.ssafy.artmate.service.FeedService;
import com.ssafy.artmate.service.SignalService;
import com.ssafy.artmate.service.UserService;

@Controller
public class SocketController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SignalService signalService;
	@Autowired
	private FeedService feedService;
	
    @MessageMapping("/send/follow/{sendUserId}/{getUserId}") //message를 백으로 받음 
    @SendTo("/get/follow/{getUserId}") //message를 프론트로 보냄
    public SignalDto signalFollow(@DestinationVariable String sendUserId, @DestinationVariable String getUserId) {
    	UserDto sendUser = userService.selectUser(sendUserId);
    	/*Follow 알림*/
		SignalDto message = new SignalDto(getUserId, sendUserId,1,0,0); //받는 아이디, 보내는 아이디, 피드 알림, 팔로우 알림, 읽기x
		message.setImg(sendUser.getUserImg()); //프로필 사진 설정
		message.setSendUserName(sendUser.getUserName()); //닉네임 설정
		if(!signalService.insertSignal(message)) return null; //알림 추가
		return message;
    }
    
    @MessageMapping("/send/feed/{sendUserId}") //message를 백으로 받음 
    @SendTo("/get/feed") //message를 프론트로 보냄
    public SignalDto signalFeed(@DestinationVariable String sendUserId) {
    	UserDto sendUser = userService.selectUser(sendUserId);
    	FeedDto feed = feedService.selectAllMyFeed(sendUserId).get(0);
    	List<UserDto> followers = userService.selectFollower(sendUserId);
    	
    	SignalDto message = null;
    	for(UserDto user : followers) {
    		/*feed 알림*/
    		message = new SignalDto(user.getUserId(), sendUserId,1,1,0); //받는 아이디, 보내는 아이디, 피드 알림, 피드 알림, 읽기x
    		message.setImg(feed.getFeedImg()); //피드 사진 설정
    		message.setSendUserName(sendUser.getUserName()); //닉네임 설정
    		signalService.insertSignal(message); //알림 추가
    	}
		return message;
    }
    
}
