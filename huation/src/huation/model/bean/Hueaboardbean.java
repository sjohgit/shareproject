package huation.model.bean;

import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import huation.model.aboard.AboardDAO;
import huation.model.aboard.AboardDTO;
import huation.model.aboard.AcommentDTO;
import huation.model.board.BoardDTO;


@Controller
@RequestMapping("/aboard")




public class Hueaboardbean {

	@Autowired
	private AboardDAO dao = null;
	
	
	
	@RequestMapping("/List.do")
	public String List(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");

		if (pageNum == null) {
			pageNum = "1";
		}

		int pageSize = 5;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		int number = 0;

		int count = dao.noticecount();
		request.setAttribute("count", count);

		List noticeList = null;
		if (count > 0) {
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			noticeList = dao.noticelist(start, end);
			request.setAttribute("noticeList", noticeList);

			number = count - (currentPage - 1) * pageSize;

			int startPage = (int) (currentPage / 10) * 10 + 1;
			int pageBlock = 10;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			request.setAttribute("number", number);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
		}
	
		return "/aboard/List";
}
	@RequestMapping("/searchList.do")
	public String searchList(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		String keyword = request.getParameter("keyword");
		String search_option = request.getParameter("search_option");
				
		if (pageNum == null) {
			pageNum = "1";
		}

		int pageSize = 10;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		int number = 0;

		int count = dao.noticecount();
		request.setAttribute("count", count);

		List searchlist = null;
		if (count > 0) {
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			searchlist = dao.searchnoticelist(start, end,keyword,search_option);
			request.setAttribute("searchlist", searchlist);

			number = count - (currentPage - 1) * pageSize;

			int startPage = (int) (currentPage / 10) * 10 + 1;
			int pageBlock = 10;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			request.setAttribute("pageNum", pageNum);
			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			request.setAttribute("number", number);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
		}
	return "/aboard/searchList";  
	}
	@RequestMapping("/writeForm.do")
	public String writeForm() {
	
		return "/aboard/writeForm";
}
	//Java script
		@RequestMapping(value= "/insert.do")
		@ResponseBody
		public String insert(HttpServletRequest request,AboardDTO dto,HttpSession session) {
			String writer = (String)session.getAttribute("memId");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			System.out.println(writer);
			System.out.println(subject);
			System.out.println(content);
			
			
			dto.setWriter(writer);
			dto.setSubject(subject);
			dto.setContent(content);
			
			dao.insert(dto);
			
			String url = "List.do";
			
			return url;
		}
		
	@RequestMapping("/writePro.do")
	public String writePro() {
	
		return "/aboard/writePro";
}
	
	

	
	@RequestMapping("/content.do")
	public String content(HttpServletRequest request) {
		String memId = request.getParameter("memId");
		String n = request.getParameter("num");

		int num = Integer.parseInt(n);
		System.out.println("넘"+num);
		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		System.out.println("페이지"+pageNum);
		System.out.println("넘 "+num);
		dao.addreadcount(num);

		List<AboardDTO> contentList = dao.sel_notice(num);
		request.setAttribute("contentList", contentList);

		request.setAttribute("memId", memId);
		
		
		
		
		/* 댓글 */
		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 10;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		int number = 0;

		int count = dao.commentcount();
		request.setAttribute("count", count);
		List commentList = null;
		if (count > 0) {
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			commentList = dao.commentlist(start, end, num);
			request.setAttribute("commentList", commentList);
			number = count - (currentPage - 1) * pageSize;

			int startPage = (int) (currentPage / 10) * 10 + 1;
			int pageBlock = 10;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount)
				endPage = pageCount;

			request.setAttribute("pageSize", pageSize);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("start", start);
			request.setAttribute("end", end);
			request.setAttribute("number", number);
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			
		}
		return "/aboard/content";
}
	//Java script
	@RequestMapping(value= "/replyinsert.do")
	@ResponseBody
	public String replyinsert(HttpServletRequest request,AboardDTO dto,HttpSession session) {
		String pageNum = request.getParameter("pageNum");
		
		int ref = Integer.parseInt(request.getParameter("ref"));
		System.out.println("라이에프"+ref);
		
		String id = (String)session.getAttribute("memId");
		
		String subject = request.getParameter("subject");
		
		String content = request.getParameter("content");
		
		int re_step = Integer.parseInt(request.getParameter("re_step"));
		
		int re_level = Integer.parseInt(request.getParameter("re_level"));
		
		System.out.println("아이디 "+id);
		System.out.println("제목"+subject);
		System.out.println("내용 "+content);
		System.out.println("엄마글 "+ref);
		System.out.println("답글레벨"+re_level);
		System.out.println("글 시퀀스 "+re_step);
		
		
		dto.setWriter(id);
		dto.setSubject(subject);
		dto.setContent(content);
		dto.setRef(ref);
		dto.setRe_level(re_level);
		
		int re_stepup = dao.foundre_step(ref);
		
		
		
		System.out.println("re_stepup"+re_stepup);
		if(re_stepup == 0) {
			re_stepup = 1; 
			
		}else {
			re_stepup++; 
			
		}
		System.out.println("re_stepup+"+re_stepup);
		dto.setRe_step(re_stepup);
		
		
		
		System.out.println(dto.getRe_step());
		dao.replyinsert(dto);
		
		
		String url = "List.do";
		
		return url;
	}
	//Java script
	@RequestMapping(value= "/commentinsert.do")
	@ResponseBody
	public String commentinsert(HttpServletRequest request,AcommentDTO dto) {
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		System.out.println("댓글다는"+num);
		dto.setNum(num);
		dao.com_insert(dto);
//
//		if (pageNum == null) {
//			pageNum = "1";
//		}
//		int pageSize = 10;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//		int currentPage = Integer.parseInt(pageNum);
//		int start = (currentPage - 1) * pageSize + 1;
//		int end = currentPage * pageSize;
//		int number = 0;
//
//		int count = dao.commentcount();
//		request.setAttribute("count", count);
//		List commentList = null;
//		String json = "";
//		if (count > 0) {
//			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
//			commentList = dao.commentlist(start, end, num);
//			request.setAttribute("commentList", commentList);
//			number = count - (currentPage - 1) * pageSize;
//
//			int startPage = (int) (currentPage / 10) * 10 + 1;
//			int pageBlock = 10;
//			int endPage = startPage + pageBlock - 1;
//			if (endPage > pageCount)
//				endPage = pageCount;
//
//			request.setAttribute("pageSize", pageSize);
//			request.setAttribute("currentPage", currentPage);
//			request.setAttribute("start", start);
//			request.setAttribute("end", end);
//			request.setAttribute("number", number);
//			request.setAttribute("pageCount", pageCount);
//			request.setAttribute("startPage", startPage);
//			request.setAttribute("endPage", endPage);
//		
//			
//			
//		}
//		HashMap map = new HashMap();
//		map.put("commentList",commentList);
//		try {
//			json = new ObjectMapper().writeValueAsString(map);
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//		System.out.println(json);
		
		return "a";
	}
	@RequestMapping("/replyForm.do")
	public String replyForm(HttpServletRequest request) {
		String n = request.getParameter("num");

		int num = Integer.parseInt(n);
		List<AboardDTO> contentList = dao.sel_notice(num);
		request.setAttribute("contentList", contentList);
		return "/aboard/replyForm";
}
	@RequestMapping("/updateForm.do")
	public String updateForm(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));

		List<AboardDTO> updatelist = dao.sel_notice(num);

		request.setAttribute("updatelist", updatelist);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		return "/aboard/updateForm";
}
	//Java script
	@RequestMapping(value= "/update.do")
	@ResponseBody
	public String update(HttpServletRequest request,AboardDTO dto,HttpSession session) {
		String writer = (String)session.getAttribute("memId");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		int num = Integer.parseInt(request.getParameter("num"));
		
		System.out.println(writer);
		System.out.println(subject);
		System.out.println(content);
		System.out.println(num);
		
		dto.setWriter(writer);
		dto.setSubject(subject);
		dto.setContent(content);

		dao.update(dto);
		
		String url = "List.do";
		
		return url;
	}
	@RequestMapping("/deleteFrom.huation")
	public String deleteFrom() {
	
		return "/aboard/deleteFrom";
}
	//Java script 게시물삭제
		@RequestMapping(value= "/deletecontent.do")
		@ResponseBody
		public String deletecontent(HttpServletRequest request,AboardDTO dto,HttpSession session) {
			String pageNum = request.getParameter("pageNum");
			int num = Integer.parseInt(request.getParameter("num"));
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("num", num);
			System.out.println("ajax"+num);
			dao.delete(num);		
			dao.allcommentdelete(num);
			String url = "List.do";
			
			return url;
		}
		//Java script 댓글삭제
		@RequestMapping(value= "/deletecomment.do")
		@ResponseBody
		public String deletecomment(HttpServletRequest request,AboardDTO dto,HttpSession session) {
			String pageNum = request.getParameter("pageNum");
			int num = Integer.parseInt(request.getParameter("num"));
			int renum = Integer.parseInt(request.getParameter("renum"));
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("num", num);
			request.setAttribute("renum", renum);
			System.out.println("ajax"+num);
			
			dao.deletecomment(num, renum);	
			
			
			
			return "a";
		}
}
	
