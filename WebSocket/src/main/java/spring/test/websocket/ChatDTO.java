package spring.test.websocket;

public class ChatDTO {
	private String writer;
	private String message;
	private String reciever;
	private String time;
	private MessageType type;
	
	public enum MessageType {
		ENTER, CHAT, LEAVE
	}
	
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
	
	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	@Override
	public String toString() {
		return "ChatDTO [writer=" + writer + ", message=" + message + ", reciever=" + reciever + ", type=" + type + "]";
	}
	
}
