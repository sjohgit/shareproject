package huation.model.bean;


import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


import huation.model.member.MemberDAO;
import huation.model.member.MemberDTO;

	





@Controller
@RequestMapping("/member")
	public class Huemainbean {
	
	@Autowired
	private MemberDAO dao = null;
	

		@RequestMapping("/huemember.do")
		public String huemember() {

		return "/member/huemember";
	}
		@RequestMapping("/huecheckmember.do")
		public String huecheckmember(HttpServletRequest request) {
			String memId = request.getParameter("id");
			int check = dao.checkmember(memId);
			
			request.setAttribute("memId",memId);
			request.setAttribute("check", check);

		return "/member/huecheckmember";
	}
		@RequestMapping("/huememberPro.do")
		public String hueMemeberPro(MemberDTO dto,HttpServletRequest request) {
			
			dao.insert(dto);
			
		return "/member/huememberPro";
	}
		@RequestMapping("/huelogin.do")
		public String huelogin(HttpServletRequest request) {
			String memId = request.getParameter("id");
			
			request.setAttribute("memId",memId);

		return "/member/huelogin";
	}
		@RequestMapping("/hueloginPro.do")
		public String hueloginPro(HttpServletRequest request,HttpServletResponse response,HttpSession session,MemberDTO dto) throws Exception {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			dto.setId(id);
			dto.setPw(pw);
			
			int check = dao.logincheck(dto);
			System.out.println(check);
			
//			PrintWriter writer = response.getWriter();
			
			request.setAttribute("check", check);
		
		if(check==1) {
		
			
			session.setAttribute("memId", dto.getId());
			
			request.setAttribute("memId",dto.getId());
			
			
//			response.setContentType("text/html; charset=UTF-8");
//			writer.println("<script>alert('안녕하세요!'); location.href='/huation/huation.do';</script>");
//			writer.flush();
//			return "redirect:/huation.do"; 
			return "redirect:/huation.do"; 
			
		}else {
			return "redirect:/member/huelogin.do"; 
		}
		
		}
	
		@RequestMapping("/hueloginMain.do")
		public String hueloginMain(HttpServletRequest request) {


		return "/member/huationMain";
	}
		@RequestMapping("/huelogout.do")
		public String huelogout(HttpSession session) {
			session.invalidate();
		return "/member/huelogout";
	}
		@RequestMapping("/huationmodifycheckForm.do")
		public String huationmodifycheckForm(HttpServletRequest request) {
			String memId = request.getParameter("memId");
			request.setAttribute("memId", memId);
			return "/member/huationmodifycheckForm";
	}
		@RequestMapping("/huationmodifycheckPro.do")
		public String huationmodifycheckPro(HttpServletRequest request,MemberDTO dto) {
			String memId = request.getParameter("id");
			String pw = request.getParameter("pw");
			
			
			dto.setId(memId);
			dto.setPw(pw);
			
			int check = dao.logincheck(dto);
			System.out.println("체크:"+check);
			request.setAttribute("check", check);
		return "/member/huationmodifycheckPro";
	}
		@RequestMapping("/huationmodifyForm.do")
		public String huationmodifyForm(HttpServletRequest request,MemberDTO dto,HttpSession session) {
			String id = (String)session.getAttribute("memId");
			System.out.println("아이디"+id);
			List<MemberDTO> list = dao.sel_member(id);
			
			request.setAttribute("list", list);
		return "/member/huationmodifyForm";
	}
		@RequestMapping("/huationmodifyPro.do")
		public String huationmodifyPro(MemberDTO dto,HttpSession session) {
			String id = (String)session.getAttribute("memId");
			dto.setId(id);
			dao.memberupdate(dto);
		return "/member/huationmodifyPro";
	}
		

}	