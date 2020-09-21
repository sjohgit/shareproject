package huation.model.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class Huechatbean {
	@RequestMapping("/chat.huation")
	public String huationMain(HttpSession session,HttpServletRequest request) {
	String memId = (String)session.getAttribute("memId");
	request.setAttribute("memId", memId);
		

	return "chat/chat";
}

}
