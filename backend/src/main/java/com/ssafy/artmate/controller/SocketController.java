package com.ssafy.artmate.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.ssafy.artmate.Dto.SignalDto;

@Controller
public class SocketController {
	// /receive를 메시지를 받을 endpoint로 설정합니다.
	// /send로 메시지를 반환합니다.
	// SocketHandler는 1) /receive에서 메시지를 받고, /send로 메시지를 보내줍니다.
    // 정의한 SocketVO를 1) 인자값, 2) 반환값으로 사용합니다.
    @MessageMapping("/socket/receive")
    @SendTo("/socket/send")
    public SignalDto SocketHandler(SignalDto Message) {
        return Message; // 반환
    }
	
    @MessageMapping("/getSignal/{sendUserId}")
    @SendTo("/signal/{getUserId}")
    public SignalDto signal(@DestinationVariable String sendUserId, @DestinationVariable String getUserId, SignalDto Message) {
    	System.out.println(Message);
    	return Message;
    }
    
//    @MessageMapping("/receive")
//    @sendTo()
//    public MessageDto test() {
//    	MessageDto result=new MessageDto();
//    	result.setUserName("관리자");
//    	result.setMessage("이거 가냐");
//    	return result;
//    }
    
    
}
