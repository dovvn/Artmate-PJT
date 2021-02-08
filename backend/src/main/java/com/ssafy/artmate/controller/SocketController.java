package com.ssafy.artmate.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.ssafy.artmate.dto.FeedDto;
import com.ssafy.artmate.dto.SignalDto;
import com.ssafy.artmate.dto.UserDto;
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
    	if(sendUserId.equals(getUserId)) {
    		return null;
    	}
    	UserDto sendUser = userService.selectUser(sendUserId);
    	/*Follow 알림*/
		SignalDto message = new SignalDto(getUserId, sendUserId,1,0,0); //받는 아이디, 보내는 아이디, 피드 알림, 팔로우 알림, 읽기x
		message.setImg(sendUser.getUserImg()); //프로필 사진 설정
		message.setSendUserName(sendUser.getUserName()); //닉네임 설정
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
		message.setSigDate(currentDate);
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
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
    	for(UserDto user : followers) {
    		/*feed 알림*/
    		message = new SignalDto(user.getUserId(), sendUserId,1,1,0); //받는 아이디, 보내는 아이디, 피드 알림, 피드 알림, 읽기x
    		message.setImg(feed.getFeedImg()); //피드 사진 설정
    		message.setSendUserName(sendUser.getUserName()); //닉네임 설정
    		message.setSigDate(currentDate);
    		signalService.insertSignal(message); //알림 추가
    	}
		return message;
    }
    
    @MessageMapping("/send/like/{sendUserId}/{getUserId}/{feedId}") //message를 백으로 받음 
    @SendTo("/get/like/{getUserId}") //message를 프론트로 보냄
    public SignalDto signalLike(@DestinationVariable String sendUserId, @DestinationVariable String getUserId, @DestinationVariable int feedId) {
    	if(sendUserId.equals(getUserId)) {
    		return null;
    	}
    	UserDto sendUser = userService.selectUser(sendUserId);
    	FeedDto feed = feedService.selectOneFeed(getUserId, feedId);
    	
    	/*좋아요 알림*/
    	SignalDto message = new SignalDto(getUserId, sendUserId,1,2,0); //받는 아이디, 보내는 아이디, 피드 알림, 좋아요 알림, 읽기x
		message.setImg(feed.getFeedImg()); //피드 사진 설정
		message.setSendUserName(sendUser.getUserName()); //닉네임 설정
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
		message.setSigDate(currentDate);
		if(!signalService.insertSignal(message)) return null; //알림 추가
		return message;
    }
    
    @Scheduled(fixedRate=10000)
    @SendTo("/get/exhibit")
    public SignalDto signalExhibit() {
    	String getUserId = "hyeji6138@naver.com";
    	int exId = 1;
    	SignalDto message = new SignalDto(getUserId,exId,0,0);
    	System.out.println("주기적으로 호출이 됐다!");
    	return message;
    }
    
}
