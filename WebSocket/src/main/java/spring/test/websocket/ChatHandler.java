package spring.test.websocket;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ChatHandler extends TextWebSocketHandler {
	@Autowired
	private ChatService service;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Map<String, WebSocketSession> userSession = new HashMap<>();
	private final Map<String, String> userId = new HashMap<>();

	// 메시지 수신 후 
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		ChatDTO chatDto = objectMapper.readValue(msg, ChatDTO.class);
		System.out.println(chatDto);
		switch (chatDto.getType()) {
		case CHAT : {
			int result = service.addChatting(chatDto);
			WebSocketSession ws = userSession.get(userId.get(chatDto.getReciever()));
			if(ws != null) ws.sendMessage(new TextMessage(msg));
			ws = userSession.get(userId.get(chatDto.getWriter()));
			ws.sendMessage(new TextMessage(msg));
			} break;
		case ENTER : {
			System.out.println("연결 성공");
			userSession.put(session.getId(), session);
			userId.put(chatDto.getWriter(), session.getId());
		} break;
		case LEAVE : {
			System.out.println("연결 종료");
		} break;
		default:
			break;
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("연결 종료");
		userSession.remove(session.getId());
	}
}
