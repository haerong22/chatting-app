package spring.test.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebSocketController {
	
	@RequestMapping("/chat.do")
	public ModelAndView chatViewPage(String userName) {
		return new ModelAndView("views/chat", "userName", userName);
	}
	@RequestMapping("/chatList.do")
	public ModelAndView chatListViewPage() {
		return new ModelAndView("views/chatlist");
	}
}
