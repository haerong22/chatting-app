package spring.test.websocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDAOImpl implements ChatDAO {
	@Autowired
	MongoTemplate mongoTemplate;
	
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
