package com.ssafy.artmate.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.ssafy.artmate.dto.ExhibitDto;
import com.ssafy.artmate.dto.FeedDto;
import com.ssafy.artmate.dto.SignalDto;
import com.ssafy.artmate.dto.UserDto;
import com.ssafy.artmate.service.ExhibitService;
import com.ssafy.artmate.service.FeedService;
import com.ssafy.artmate.service.SignalService;
import com.ssafy.artmate.service.UserService;

@EnableScheduling
@Controller
public class SocketController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SignalService signalService;
	@Autowired
	private FeedService feedService;
	@Autowired
	private SimpMessagingTemplate simpm;
	@Autowired
	private ExhibitService exhibitService;
	
    @MessageMapping("/send/follow/{sendUserId}/{getUserId}") //message를 백으로 받음
    @SendTo("/get/follow/{getUserId}") //message를 프론트로 보냄
    public SignalDto signalFollow(@DestinationVariable String sendUserId, @DestinationVariable String getUserId) {
    	if(sendUserId.equals(getUserId)) {
    		return null;
    	}
    	UserDto sendUser = userService.selectUser(sendUserId);
    	/*Follow 알림*/
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
		//받는 아이디, 보내는 아이디, 피드 알림, 팔로우 알림, 읽기x, 이미지, 날짜, 닉네임
		SignalDto message = new SignalDto(getUserId, sendUserId,1,0,0, sendUser.getUserImg(), currentDate, sendUser.getUserName());
		if(!signalService.insertSignal(message)) return null; //알림 추가
		return message;
    }
    
    @MessageMapping("/send/feed/{sendUserId}") //message를 백으로 받음
    public void signalFeed(@DestinationVariable String sendUserId) {
    	UserDto sendUser = userService.selectUser(sendUserId);
    	FeedDto feed = feedService.selectAllMyFeed(sendUserId).get(0);
    	List<UserDto> followers = userService.selectFollower(sendUserId);
    	
    	SignalDto message = null;
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
    	for(UserDto user : followers) {
    		/*feed 알림*/
    		//받는 아이디, 보내는 아이디, 피드 알림, 피드 알림, 읽기x, 이미지, 날짜, 닉네임
    		message = new SignalDto(user.getUserId(), sendUserId,1,1,0, feed.getFeedImg(), currentDate, sendUser.getUserName());
    		message.setMessageId(feed.getId());
    		signalService.insertSignal(message); //알림 추가
    		simpm.convertAndSend("/get/feed/"+user.getUserId(),message);
    	}
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
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
    	//받는 아이디, 보내는 아이디, 피드 알림, 팔로우 알림, 읽기x, 이미지, 날짜, 닉네임
    	SignalDto message = new SignalDto(getUserId, sendUserId,1,2,0, feed.getFeedImg(), currentDate, sendUser.getUserImg());
    	message.setMessageId(feed.getId());
		if(!signalService.insertSignal(message)) return null; //알림 추가
		return message;
    }
    //전시회 알림
    @Scheduled(cron="0 0/3 0-23 * * ?") //10시부터 18시까지 10분간격으로
    public void signalExhibit() {
    	List<UserDto> users = userService.selectAllUser();
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentDate = dateFormat.format(new Date());
		ExhibitDto sendExhibit = null;
		List<ExhibitDto> exhibits = exhibitService.selectAllExhibit("");
		int getIdx = 0;
    	for(UserDto u : users) {
    		List<ExhibitDto> recomExhibits = exhibitService.selectExhibitMyTag(u.getUserId());   
    		if(recomExhibits.size()<1) {
    			getIdx = (int)(Math.random()*exhibits.size());
    			sendExhibit = exhibits.get(getIdx);
    		}else {
    			getIdx = (int)(Math.random()*recomExhibits.size());
    			sendExhibit = recomExhibits.get(getIdx);    			
    		}
    		//받는 아이디, 전시회 아이디, 전시회 알림, 읽기x, 이미지, 날짜, 전시회 이름
    		SignalDto message = new SignalDto(u.getUserId(),sendExhibit.getId(),0,0, sendExhibit.getExImg(), currentDate, sendExhibit.getName());
    		message.setDescription(sendExhibit.getDescription());
    		if(signalService.insertSignal(message)) simpm.convertAndSend("/get/exhibit/"+u.getUserId(), message);
    	}
    }
    //로그인 시 확인 안한 새 알림이 있는지 확인
    @MessageMapping("/send/login/{userId}")
    public void signalCheckNew(@DestinationVariable String userId) {
    	if(signalService.countNewSignal(userId)>0) {
    		SignalDto message = new SignalDto();
    		simpm.convertAndSend("/get/login/"+userId, message);
    	}
    }
}
