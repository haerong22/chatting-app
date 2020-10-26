package spring.test.websocket;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSocketController {
	@Autowired
	private ChatService service;
	
	@RequestMapping("/chat.do")
	public ModelAndView chatViewPage(String userName, HttpServletRequest req) {
		req.getSession().setAttribute("userName", userName);
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
	public ModelAndView login(String userId, String userPassword) {
		int result = service.loginCheck(userId, userPassword);
		if(result == 1) {
			return new ModelAndView("redirect: chat.do", "userName", userId);
		} 
		return new ModelAndView("views/login");
	}
}
