package spring.test.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDAOImpl implements ChatDAO {
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public UserDTO getUserInfo(String userId) {
		Query query = new Query()
				.addCriteria(Criteria.where("userId").in(userId));
		UserDTO friends = mongoTemplate.findOne(query, UserDTO.class, "user");
		return friends;
	}
	
	@Override
	public int addFriends(UserDTO userDto) {
		Query query = new Query()
				.addCriteria(Criteria.where("userId").in(userDto.getUserId()));
		Update update = new Update()
				.addToSet("friends", userDto.getSearchId());
		mongoTemplate.updateFirst(query, update, "user");
		return 1;
	}
	
	@Override
	public List<UserDTO> findUser(String search, String userName) {
		List<UserDTO> users = new ArrayList<>();
		if(search != null && !search.equals("")) {
			Query query = new Query(new Criteria().andOperator(
					Criteria.where("userId").regex(".*"+ search +".*", "i"),
					Criteria.where("userId").nin(userName)));
			users =	Optional.ofNullable(mongoTemplate.find(query, UserDTO.class, "user"))
					.orElseGet(()-> null);
		}
		return users;
	}
	
	@Override
	public void insertMember(Map<String, String> user) {
		mongoTemplate.insert(user, "user");
	}
	
	@Override
	public int loginCheck(String userId, String userPassword) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userId").is(userId));
		HashMap<String,String> user = 
				Optional.ofNullable(mongoTemplate.findOne(query, HashMap.class, "user"))
				.orElseGet(()-> new HashMap<>());
		
		int result = userPassword.equals(user.get("userPassword")) ? 1 : 0;
		return result;
	}
}
