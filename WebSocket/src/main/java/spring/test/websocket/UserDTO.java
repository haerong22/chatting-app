package spring.test.websocket;

public class UserDTO {
	private String userId;
	
	
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + "]";
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
