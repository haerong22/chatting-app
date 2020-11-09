package spring.test.websocket;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSocketController {
	@Autowired
	private ChatService service;
	
	@ResponseBody
	@RequestMapping(value = "/addFriends.do",
				method=RequestMethod.GET)
	public UserDTO addFriends(UserDTO userDto, HttpServletRequest req) {
		userDto.setUserId((String) req.getSession().getAttribute("userName"));
		int result = service.addFriends(userDto);
		UserDTO userInfo = service.getUserInfo(userDto.getUserId());
		return userInfo;
	}
	
	@RequestMapping("/friends.do")
	public ModelAndView friendsListViewPage(HttpServletRequest req) {
		String userId = (String) req.getSession().getAttribute("userName");
		UserDTO userInfo = service.getUserInfo(userId);
		return new ModelAndView("views/friends", "userInfo", userInfo);
	}
	
	@RequestMapping("/chat.do")
	public ModelAndView chatViewPage(String friendName, HttpServletRequest req) {
		String userId = (String) req.getSession().getAttribute("userName");
		List<ChatDTO> chatList = service.getChatting(userId, friendName);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("views/chat");
		mav.addObject("chatList", chatList);
		mav.addObject("friendName",friendName);
		mav.addObject("userName", userId);
		return mav;
	}
	@RequestMapping("/login.do")
	public ModelAndView chatListViewPage() {
		return new ModelAndView("views/login");
	}
	
	@RequestMapping("/joinForm.do")
	public String joinFormViewPage() {
		return "views/joinForm";
	}
	
	@RequestMapping("/joinPro.do")
	public String joinMember(String userId, String userPassword) {
		service.insertMember(userId, userPassword);
		return "views/login";
	}
	
	@RequestMapping("/loginPro.do")
	public ModelAndView login(String userId, String userPassword, HttpServletRequest req) {
		int result = service.loginCheck(userId, userPassword);
		if(result == 1) {
			req.getSession().setAttribute("userName", userId);
			return new ModelAndView("redirect: friends.do", "userName", userId);
		} 
		return new ModelAndView("views/login");
	}
	
	@ResponseBody
	@RequestMapping(value="/findUser.do",
			method=RequestMethod.GET)
	public List<UserDTO> findUser(String search, HttpServletRequest req) {
		String userName = (String) req.getSession().getAttribute("userName");
		List<UserDTO> users = service.findUser(search, userName);
		return users;
	}
}
