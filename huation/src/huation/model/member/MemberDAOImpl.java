package huation.model.member;

import java.util.List;



public interface MemberDAOImpl {
	public void insert (MemberDTO dto);
	public int checkmember(String id);
	public int logincheck(MemberDTO dto);
	
	//개인정보수정
	public List<MemberDTO>sel_member(String id);
	public void memberupdate(MemberDTO dto);
}
