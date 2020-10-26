package spring.test.websocket;

public interface ChatService {

	public void insertMember(String userId, String userPassword);

	public int loginCheck(String userId, String userPassword);

}
