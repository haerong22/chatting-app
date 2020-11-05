package spring.test.websocket;

import java.util.Arrays;

public class UserDTO {
	private String userId;
	private String searchId;
	private String[] friends;
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", searchId=" + searchId + ", friends=" + Arrays.toString(friends) + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSearchId() {
		return searchId;
	}

	public void setSearchId(String searchId) {
		this.searchId = searchId;
	}

	public String[] getFriends() {
		return friends;
	}

	public void setFriends(String[] friends) {
		this.friends = friends;
	}
	
}
