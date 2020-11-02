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
	
	@RequestMapping("/friends.do")
	public String friendsListViewPage() {
		return "views/friends";
	}
	
	@RequestMapping("/chat.do")
	public ModelAndView chatViewPage(String userName) {
		return new ModelAndView("views/chat", "userName", userName);
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
			return new ModelAndView("views/friends", "userName", userId);
		} 
		return new ModelAndView("views/login");
	}
	
	@ResponseBody
	@RequestMapping(value="/findUser.do",
			method=RequestMethod.GET)
	public List<UserDTO> findUser(String search) {
		System.out.println(":" + search + ":");
		List<UserDTO> users = service.findUser(search);
		return users;
	}
}
