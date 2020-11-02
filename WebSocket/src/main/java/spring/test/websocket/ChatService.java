package spring.test.websocket;

import java.util.List;

public interface ChatService {

	public void insertMember(String userId, String userPassword);

	public int loginCheck(String userId, String userPassword);

	public List<UserDTO> findUser(String search);

}