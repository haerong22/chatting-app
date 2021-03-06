package spring.test.websocket;

import java.util.List;
import java.util.Map;

public interface ChatDAO {

	public void insertMember(Map<String, String> user);

	public int loginCheck(String userId, String userPassword);

	public List<UserDTO> findUser(String search, String userName);

	public int addFriends(UserDTO userDto);

	public UserDTO getUserInfo(String userId);

	public int addChatting(ChatDTO chatDto);

	public List<ChatDTO> getChatting(Map<String, String> map);

}
