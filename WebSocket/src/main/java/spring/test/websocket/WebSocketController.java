package spring.test.websocket;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSocketController {
	
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
}
