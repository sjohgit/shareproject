package huation.model.member;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class MemberDAO implements MemberDAOImpl{
	
	private SqlSessionTemplate sqlSession = null;

	public MemberDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public void insert(MemberDTO dto) {
		sqlSession.insert("huemember.insert",dto);
		
	}
	@Override
	public int checkmember(String id) {
		int check = (int)sqlSession.selectOne("huemember.checkmember",id);
		
		return  check;
	}

	@Override
	public int logincheck(MemberDTO dto) {
		System.out.println(dto.getId());
		System.out.println(dto.getPw());
		
		int check = (int)sqlSession.selectOne("huemember.logincheck",dto);
		return check;
	}
	//개인정보수정
	@Override
	public List<MemberDTO> sel_member(String id) {
		List <MemberDTO> list = sqlSession.selectList("huemember.sel_member",id);
		
		return list;
	}

	@Override
	public void memberupdate(MemberDTO dto) {
		sqlSession.update("huemember.memberupdate",dto);
		
	}



}
