package huation.model.member;

public interface MemberDAOImpl {
	public void insert (MemberDTO dto);
	public int checkmember(String id);
	public int logincheck(MemberDTO dto);

}
