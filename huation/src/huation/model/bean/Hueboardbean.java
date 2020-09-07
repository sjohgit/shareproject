package huation.model.bean;


import java.text.SimpleDateFormat;

import java.util.List;


import javax.servlet.http.HttpServletRequest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import huation.model.board.BoardDAO;
import huation.model.board.BoardDTO;


@Controller
@RequestMapping("/board")

public class Hueboardbean {
	
	@Autowired
	private BoardDAO dao = null;
	
	@RequestMapping("/boardList.huation")
	public String boardList(HttpServletRequest request) {
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
	return "/board/boardList";
}
	@RequestMapping("/boardcontent.huation")
	public String boardcontent(HttpServletRequest request) {
		String memId = request.getParameter("memId");
		String n = request.getParameter("num");
		int num = Integer.parseInt(n);

		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);

		dao.addreadcount(num);

		List<BoardDTO> contentList = dao.sel_notice(num);
		request.setAttribute("contentList", contentList);
		request.setAttribute("memId", memId);

	return "/board/boardcontent";
}
	@RequestMapping("/boardwriteForm.huation")
	public String boardwriteForm() {

	return "/board/boardwriteForm";
}
	@RequestMapping("/boardwritePro.huation")
	public String boardwritePro(BoardDTO dto) {
		dao.insert(dto);
	return "/board/boardwritePro";
}
	@RequestMapping("/boardupdateForm.huation")
	public String boardupdateForm(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));

		List<BoardDTO> updatelist = dao.sel_notice(num);

		request.setAttribute("updatelist", updatelist);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		
	return "/board/boardupdateForm";
}
	@RequestMapping("/boardupdatePro.huation")
	public String boardupdatePro(HttpServletRequest request,BoardDTO dto) {
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		dto.setNum(num);
		dao.update(dto);

		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);		
	return "/board/boardupdatePro";
}
	@RequestMapping("/boarddeleteForm.huation")
	public String boarddeleteForm(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		System.out.println(num);
	return "/board/boarddeleteForm";
}
	@RequestMapping("/boarddeletePro.huation")
	public String boarddeletePro(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);

		dao.delete(num);		
	return "/board/boarddeletePro";
}
	@RequestMapping("/downExcelForm.huation")
	public String downExcelForm (HttpServletRequest request,BoardDTO dto)throws Exception {
		List<BoardDTO> excellist = dao.excellist();
		request.setAttribute("excellist", excellist);
		
		return "/board/downExcelForm";
}

}

	

