package huation.model.board;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class BoardDAO implements BoardDAOImpl{
	private SqlSessionTemplate sqlSession = null;

	public BoardDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	@Override
	public void insert(BoardDTO dto) {
		int num = 0;
		num = sqlSession.selectOne("hueboard.find");
		
		if(num == 0) {
			num = 1;
		}else {
			num++;
		}
		sqlSession.insert("hueboard.insert",dto);
	}
	@Override
	public int noticecount() {
		int count = (int)sqlSession.selectOne("hueboard.amount");
		
		return count;
		
	}
	@Override
	public List<BoardDTO> noticelist(int start, int end) {
		HashMap map = new HashMap();
		map.put("start",start);
		map.put("end", end);
		return sqlSession.selectList("hueboard.boardlist",map);
	}
	@Override
	public List<BoardDTO> sel_notice(int num) {
		
		List<BoardDTO> list = sqlSession.selectList("hueboard.sel_notice",num);
		
		return list ;
	}
	@Override
	public int addreadcount(int num) {
		return sqlSession.update("hueboard.addreadcount",num);
	}
	@Override
	public void update(BoardDTO dto) {
		sqlSession.update("hueboard.modifynotice",dto);
		
	}
	@Override
	public void delete(int num) {
		sqlSession.delete("hueboard.deletenotice",num);
		
	}
	@Override
	public List<BoardDTO> excellist() {
		List<BoardDTO> excellist = sqlSession.selectList("hueboard.excelselect");
		
		return excellist;
		
	}


}
