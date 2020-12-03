package huation.model.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import huation.model.board.BoardDAO;
import huation.model.board.BoardDTO;

@Controller
@Component



@RequestMapping("/chat")
public class Huechatbean {
	
	@Autowired
	private BoardDAO dao = null;
	
	
	@RequestMapping("/chat.do")
	//@Scheduled(fixedDelay=10000)
	
	public String huationMain(HttpSession session,HttpServletRequest request) {
	String memId = (String)session.getAttribute("memId");
	request.setAttribute("memId", memId);
	//BoardDTO dto = new BoardDTO();
	//String fileName = "Filename";
	//String writer = "1234";
	//String subject = "Subject";
	//String content = "content";
	
	
	
	//dto.setWriter(writer);
	//dto.setFiles(fileName);
	//dto.setSubject(subject);
	//dto.setContent(content);
	
	//int result = 0;
	
	
	 //result = dao.insert(dto);
	 //System.out.println("result:"+ result);
	
//	if(1 == result) {
//		//model.addAttribute("msg", "글이 등록되었습니다.");
//	}else {
//		//model.addAttribute("msg", "글 등록이 안되었습니다.");
//	}

	return "chat/chat";
}

}
