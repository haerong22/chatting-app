package spring.test.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ChatHandler extends TextWebSocketHandler {
	private final List<WebSocketSession> users = new ArrayList<>();
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final Map<String, WebSocketSession> user = new HashMap<>();

	// 메시지 수신 후 
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		ChatDTO chatDto = objectMapper.readValue(msg, ChatDTO.class);
		System.out.println(chatDto);
		switch (chatDto.getType()) {
		case CHAT : {
			WebSocketSession ws = user.get(chatDto.getReciever());
			if(ws != null) ws.sendMessage(new TextMessage(msg));
			ws = user.get(chatDto.getWriter());
			ws.sendMessage(new TextMessage(msg));
			} 
			break;
		case ENTER : {
			System.out.println("연결 성공");
			user.put(chatDto.getWriter(), session);
			users.add(session);
		} break;
		case LEAVE : {
			System.out.println("연결 종료");
			user.remove(chatDto.getWriter());
		} break;
		default:
			break;
		}
		
		
	}
}
