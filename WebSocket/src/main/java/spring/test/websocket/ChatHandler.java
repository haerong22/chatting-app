package spring.test.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ChatHandler extends TextWebSocketHandler {
	private final List<WebSocketSession> users = new ArrayList<>();
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Map<String, WebSocketSession> user = new HashMap<>();
	// 소켓 연결 후 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("연결 성공");
		users.add(session);
	}
	
	// 메시지 수신 후 
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		System.out.println("세션 : " + message);
		System.out.println("메시지 : " + message.getPayload());
		ChatDTO chatDto = objectMapper.readValue(msg, ChatDTO.class);
		switch (chatDto.getType()) {
		case CHAT : {
			for(WebSocketSession user : users) {
				System.out.println(user.getId());
				user.sendMessage(new TextMessage(msg));
			}
			break;
		}
		default:
			break;
		}
		
		
	}
	
	// 연결 해제 후 메시지
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("연결 종료");
		users.remove(session);	
	}
}
