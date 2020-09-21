package huation.model.bean;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import huation.model.aboard.AboardDAO;
import huation.model.aboard.AboardDTO;

@Controller
@RequestMapping("/aboard")




public class Hueaboardbean {

	@Autowired
	private AboardDAO dao = null;
	
	//Java script
	@RequestMapping(value= "/insert")
	public boolean selectAccountNum(HttpServletRequest request,AboardDTO dto) {
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		dto.setWriter(writer);
		dto.setSubject(subject);
		dto.setContent(content);
		
		dao.insert(dto);
		
		return true ;
	}
	
	@RequestMapping("/aboardList.huation")
	public String aboardList(HttpServletRequest request) {
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
	
		return "/aboard/aboardList";
}
	@RequestMapping("/aboardwriteForm.huation")
	public String aboardwriteForm() {
	
		return "/aboard/aboardwriteForm";
}
	@RequestMapping("/aboardwritePro.huation")
	public String aboardwritePro() {
	
		return "/aboard/aboardwritePro";
}
	

	
	@RequestMapping("/aboardcontent.huation")
	public String aboardcontent() {
	
		return "/aboard/aboardcontent";
}
	@RequestMapping("/aboardupdateForm.huation")
	public String aboardupdateForm() {
	
		return "/aboard/aboardupdateForm";
}
	@RequestMapping("/aboardupdatePro.huation")
	public String aboardupdatePro() {
	
		return "/aboard/aboardupdatePro";
}
	@RequestMapping("/aboarddeleteFrom.huation")
	public String aboarddeleteFrom() {
	
		return "/aboard/aboarddeleteFrom";
}
	@RequestMapping("/aboarddeletePro.huation")
	public String aboarddeletePro() {
	
		return "/aboard/aboarddeletePro";
}
}
