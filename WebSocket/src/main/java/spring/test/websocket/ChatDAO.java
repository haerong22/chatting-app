package spring.test.websocket;

import java.util.Map;

public interface ChatDAO {

	public void insertMember(Map<String, String> user);

	public int loginCheck(String userId, String userPassword);

}
