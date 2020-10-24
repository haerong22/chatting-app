package spring.test.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatHandler extends TextWebSocketHandler {
	private final List<WebSocketSession> users = new ArrayList<>();
	
	// 소켓 연결 후 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("연결 성공");
		users.add(session);
	}
	
	
	// 메시지 수신 후 
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		System.out.println("세션 : " + message);
		System.out.println("메시지 : " + message.getPayload());
		for(WebSocketSession user : users) {
			System.out.println(user);
			user.sendMessage(new TextMessage(message.getPayload()));
		}
		
	}
	
	// 연결 해제 후 메시지
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("연결 종료");
		users.remove(session);	
	}
}
