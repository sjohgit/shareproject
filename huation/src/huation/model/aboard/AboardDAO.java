package huation.model.aboard;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class AboardDAO implements AboardImpl{
	private SqlSessionTemplate sqlSession = null;

	public AboardDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public void insert(AboardDTO dto) {
		int num = 0;
		num = sqlSession.selectOne("hueaboard.find");
		
		if(num == 0) {
			num = 1;
		}else {
			num++;
		}
		sqlSession.insert("hueaboard.insert",dto);
		
	}

	@Override
	public int noticecount() {
		int count = (int)sqlSession.selectOne("hueaboard.amount");
		
		return count;
	}

	@Override
	public List<AboardDTO> noticelist(int start, int end) {
		HashMap map = new HashMap();
		map.put("start",start);
		map.put("end", end);
		
		return sqlSession.selectList("hueaboard.boardlist",map);
	}

	@Override
	public List<AboardDTO> sel_notice(int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addreadcount(int num) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void update(AboardDTO dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AboardDTO> excellist() {
		// TODO Auto-generated method stub
		return null;
	}

}
