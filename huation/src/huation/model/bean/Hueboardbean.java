package huation.model.bean;


import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;


import huation.model.board.BoardDAO;
import huation.model.board.BoardDTO;
import huation.model.board.CommentDTO;


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
	@RequestMapping("/boardsearchList.huation")
	public String boardsearchList(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		String keyword = request.getParameter("keyword");
		String search_option = request.getParameter("search_option");
		
		System.out.println("ㅇ키워"+keyword);
		System.out.println("ㅇ서ㅇㅂ"+search_option);
		
		
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
	return "/board/boardsearchList";  
	}
	@RequestMapping("/boardcontent.huation")
	public String boardcontent(HttpServletRequest request) {
		String memId = request.getParameter("memId");
		String n = request.getParameter("num");
		int num = Integer.parseInt(n);

		String pageNum = request.getParameter("pageNum");
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);

		dao.addreadcount(num);

		List<BoardDTO> contentList = dao.sel_notice(num);
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
	return "/board/boardcontent";
}
	@RequestMapping("/boardwriteForm.huation")
	public String boardwriteForm() {

	return "/board/boardwriteForm";
}
	@RequestMapping("/boardwritePro.huation")
	public String boardwritePro(HttpServletRequest request,HttpSession session,MultipartHttpServletRequest request1) {
		BoardDTO dto = new BoardDTO();
		String writer = (String)session.getAttribute("memId");
		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		MultipartFile file = request1.getFile("files");
		
		// 파일원본 이름
		String orgName = file.getOriginalFilename();

		// 확장자 따옴.	
		String ext = orgName.substring(orgName.lastIndexOf(".") + 1);
				
		// 경로설정
		String files = request1.getSession().getServletContext().getRealPath("User/board/files");
		System.out.println("파일즈  "+files);

		String fileName = orgName;
		// files라는 폴더없으면 폴더 생성
		File folder = new File(files);
		if (!folder.exists()) {
			folder.mkdir();
		}
		
		File orgfile = new File (fileName);
		System.out.println("파일라이트 "+orgfile);
		
	
		try {
			file.transferTo(orgfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		dto.setWriter(writer);
		dto.setFiles(fileName);
		dto.setSubject(subject);
		dto.setContent(content);
		
		
			dao.insert(dto);
			
	return "/board/boardwritePro";
}
	
	@RequestMapping("/boardfiledownloadPro.huation")
	public String boardfiledownloadPro (HttpServletRequest request, HttpServletResponse response)throws Exception {

	        
	        // 다운로드할 파일명을 가져온다.
	        String fileName = request.getParameter("file_name");
	        
	        // 파일이 있는 절대경로를 가져온다.
	        // 현재 업로드된 파일은 UploadFolder 폴더에 있다.
	        String folder = request.getServletContext().getRealPath("User/board/files");
	        // 파일의 절대경로를 만든다.
	        String filePath = folder;
	       System.out.println("파일패스"+filePath);
	 
	        try {
	            File file = new File(filePath);
	            System.out.println("파일1"+file);
	            byte b[] = new byte[(int) file.length()];
	            
	            // page의 ContentType등을 동적으로 바꾸기 위해 초기화시킴
	            response.reset();
	            response.setContentType("application/octet-stream");
	            
	            // 한글 인코딩
	            String encoding = new String(fileName.getBytes("euc-kr"),"8859_1");
	            
	            // 파일 링크를 클릭했을 때 다운로드 저장 화면이 출력되게 처리하는 부분
	            response.setHeader("Content-Disposition", "attachment;filename="+ encoding);
	            response.setHeader("Content-Length", String.valueOf(file.length()));
	            System.out.println("파일2"+file);
	            if (file.isFile()) // 파일이 있을경우
	            {
	            	System.out.println("파일3"+file);
	                FileInputStream fileInputStream = new FileInputStream(file);
	                ServletOutputStream servletOutputStream = response.getOutputStream();
	                
	                //  파일을 읽어서 클라이언트쪽으로 저장한다.
	                int readNum = 0;
	                while ( (readNum = fileInputStream.read(b)) != -1) {
	                    servletOutputStream.write(b, 0, readNum);
	                }
	                
	                servletOutputStream.close();
	                fileInputStream.close();
	            }
	            
	        } catch (Exception e) {
	            System.out.println("Download Exception : " + e.getMessage());
	        }
	 


	
		
		return "/board/boardfiledownloadPro";
}
	
	//답글달기
	@RequestMapping("/boardreplyForm.huation")
	public String boardreplyForm(HttpServletRequest request) {
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));

		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		List<BoardDTO> contentList = dao.sel_notice(num);
		request.setAttribute("contentList", contentList);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
		
		
		
		
	return "/board/boardreplyForm";
}	
	@RequestMapping("/boardreplyPro.huation")
	public String boardrereplyPro(HttpServletRequest request,HttpSession session,MultipartHttpServletRequest request1) {
		
		BoardDTO dto = new BoardDTO();
		MultipartFile file = request1.getFile("files");
		
		// 파일원본 이름
		String orgName = file.getOriginalFilename();

		// 확장자 따옴.	
		String ext = orgName.substring(orgName.lastIndexOf(".") + 1);
				
		// 경로설정
		String files = request1.getSession().getServletContext().getRealPath("User/board/files");
	
		String fileName = orgName;
		
		File orgfile = new File (fileName);

		
	
		try {
			file.transferTo(orgfile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
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
		dto.setFiles(fileName);
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
		
		
		
	    
		 
		 
		
		

		
	return "/board/boardreplyPro";
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
	public String boardupdatePro(HttpServletRequest request,BoardDTO dto,MultipartHttpServletRequest request1) {
		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		List<BoardDTO> updatelist = dao.sel_notice(num);
		
		
		String oldifile = updatelist.get(0).getFiles();
		
		
		System.out.println("올두 "+oldifile);
		
		

		
		// form에서 전달받은 파일
		MultipartFile file = request1.getFile("file");//수정할 파일
		System.out.println("뉴"+file);
		// 파일원본 이름
		String orgName = file.getOriginalFilename();
		// 확장자 따옴.
		String ext = orgName.substring(orgName.lastIndexOf(".") + 1);
		// 경로설정
		String files = request.getSession().getServletContext().getRealPath("User/board/files");
				
		String oldname = oldifile ; //파일수정을 안할때
		String newname = orgName ; //파일수정 할때

		File fileCopy1 = new File(files + "//" + oldname);
		File fileCopy = new File(files + "//" + newname);
		try {
			file.transferTo(fileCopy);
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		if(file == null || file.equals(oldifile)) {//이미지 변경 안할경우
			dto.setFiles(oldifile);
		}else {
			dto.setFiles(newname);//이미지 변경할때
			File file1 = new File(files + "//" + oldifile);
			if(file1.exists()) {
				file1.delete();
		}
}
		
		
		
		
		dto.setNum(num);
		dto.setWriter(writer);
		dto.setSubject(subject);
		dto.setContent(content);
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
	@RequestMapping("/uploadExcelForm.huation")
	public String uploadExcelForm (BoardDTO dto){
	
		return "/board/uploadExcelForm";
}
	
		@RequestMapping("/uploadExcellistForm.huation")
		public String uploadExcellistForm (MultipartHttpServletRequest request){
			// form에서 전달받은 파일
			MultipartFile excelFile = request.getFile("excelfile");
			// 파일원본 이름
			String orgName = excelFile.getOriginalFilename();
			// 확장자 따옴.
			String ext = orgName.substring(orgName.lastIndexOf(".")+1);
			// 경로설정
			String file = request.getServletContext().getRealPath("User/file/excel");
			
			String excelname = orgName;
			
			System.out.println("excelname:"+excelname);
			
			
			File orgExcelfile = new File (excelname);
			
			System.out.println("orgExcelfile:"+excelname);
			try {
				excelFile.transferTo(orgExcelfile);
				dao.uploadExcelFile(orgExcelfile);
				
			}catch(Exception e){
				
				e.printStackTrace();
			}
       
			return "/board/uploadExcellistForm";
	}

		@RequestMapping("/boardcommentPro.huation")
		public String boardcommentPro (CommentDTO dto, HttpServletRequest request){
			int num = Integer.parseInt(request.getParameter("num"));
			String pageNum = request.getParameter("pageNum");

			dto.setNum(num);
			dao.com_insert(dto);

			request.setAttribute("num", num);
			request.setAttribute("pageNum", pageNum);
		
			return "/board/boardcommentPro";
	}
		// 댓글 삭제 처리
		@RequestMapping("/boardcommentdeletePro.huation")
		public String boardcommentdeletePro(HttpServletRequest request) {
			String pageNum = request.getParameter("pageNum");
			int num = Integer.parseInt(request.getParameter("num"));
			request.setAttribute("pageNum", pageNum);
			request.setAttribute("num", num);

			int renum = Integer.parseInt(request.getParameter("renum"));

			dao.deletecomment(num, renum);
			return "/board/boardcommentdeletePro";
		}
}

	

