package huation.model.bean;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import huation.model.member.MemberDAO;
import huation.model.member.MemberDTO;

	





@Controller
@RequestMapping("/member")
	public class Huemainbean {
	
	@Autowired
	private MemberDAO dao = null;
	

		@RequestMapping("/huemember.huation")
		public String huemember() {

		return "/member/huemember";
	}
		@RequestMapping("/huecheckmember.huation")
		public String huecheckmember(HttpServletRequest request) {
			String memId = request.getParameter("id");
			int check = dao.checkmember(memId);
			
			request.setAttribute("memId",memId);
			request.setAttribute("check", check);

		return "/member/huecheckmember";
	}
		@RequestMapping("/huememberPro.huation")
		public String hueMemeberPro(MemberDTO dto,HttpServletRequest request) {
			
			dao.insert(dto);
			
		return "/member/huememberPro";
	}
		@RequestMapping("/huelogin.huation")
		public String huelogin(HttpServletRequest request) {
			String memId = request.getParameter("id");
			
			request.setAttribute("memId",memId);

		return "/member/huelogin";
	}
		@RequestMapping("/hueloginPro.huation")
		public String hueloginPro(HttpServletRequest request,HttpSession session,MemberDTO dto) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			dto.setId(id);
			dto.setPw(pw);
			
			int check = dao.logincheck(dto);
			System.out.println(check);
			
		if(check==1) {
			
			session.setAttribute("memId", dto.getId());
			
			request.setAttribute("memId",dto.getId());
		}
	
			request.setAttribute("check", check);

		return "/member/hueloginPro";
	}
		@RequestMapping("/hueloginMain.huation")
		public String hueloginMain(HttpServletRequest request) {
			String id = request.getParameter("id");
			request.setAttribute("id",id);

		return "/member/huationMain";
	}
		@RequestMapping("/huelogout.huation")
		public String huelogout(HttpSession session) {
			session.invalidate();
		return "/member/huelogout";
	}
		

}	