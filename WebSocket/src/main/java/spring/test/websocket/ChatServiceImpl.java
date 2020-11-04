package spring.test.websocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {
	@Autowired
	private ChatDAO dao;
	
	@Override
	public List<UserDTO> findUser(String search, String userName) {
		return dao.findUser(search, userName);
	}
	
	@Override
	public void insertMember(String userId, String userPassword) {
		Map<String, String> user = new HashMap<>();
		user.put("userId", userId);
		user.put("userPassword", userPassword);
		dao.insertMember(user);
	}
	
	@Override
	public int loginCheck(String userId, String userPassword) {
		return dao.loginCheck(userId, userPassword);
	}
	
}
