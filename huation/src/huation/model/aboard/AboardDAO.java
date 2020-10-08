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
		System.out.println("넘:"+num);
		dto.setNum(num);
		dto.setRef(num);
		
		sqlSession.insert("hueaboard.insert",dto);
		
	}
	@Override
	public void com_insert(AcommentDTO dto) {
		int renum = 0;
		int num = dto.getNum();
		renum = sqlSession.selectOne("hueaboard.findcomment",dto);
		renum++;
		dto.setRenum(renum);
		sqlSession.insert("hueaboard.commentinsert",dto);
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
	public void replyinsert(AboardDTO dto) {
		int num = sqlSession.selectOne("hueaboard.find");

		if(num == 0) {
			num = 1; 
			
		}else {
			num++; 
			
		}
	
		System.out.println("넘:"+num);
		
		
		dto.setNum(num);
		
		
		sqlSession.insert("hueaboard.insert",dto);
	}

	@Override
	public List<AboardDTO> sel_notice(int num) {
		List<AboardDTO> list = sqlSession.selectList("hueaboard.sel_notice",num);
		return list;
	}

	@Override
	public int addreadcount(int num) {
		return sqlSession.update("hueaboard.addreadcount",num);
		
	}

	@Override
	public void update(AboardDTO dto) {
		sqlSession.update("hueaboard.modifynotice",dto);
		
	}

	@Override
	public void delete(int num) {
		sqlSession.delete("hueaboard.deletenotice",num);
		
	}



	@Override
	public List<AboardDTO> searchnoticelist(int start, int end, String keyword, String search_option) {
		HashMap map = new HashMap();
		map.put("start",start);
		map.put("end", end);
		map.put("keyword", keyword);
		map.put("search_option", search_option);
		System.out.println("daoㅇ키워"+start);
		System.out.println("daoㅇ키워"+end);
		System.out.println("daoㅇ키워"+keyword);
		System.out.println("daoㅇ서ㅇㅂ"+search_option);
		
		
		return sqlSession.selectList("hueaboard.aboardsearchlist",map);
	}

	@Override
	public List<AcommentDTO> commentlist(int start, int end, int num) {
		HashMap map = new HashMap();
		map.put("start",start);
		map.put("end", end);
		map.put("num", num);
		return sqlSession.selectList("hueaboard.commentlist",map);
	}
	@Override
	public int commentcount() {
		int count = (int)sqlSession.selectOne("hueaboard.commentamount");
		
		return count;
	}

	@Override
	public void deletecomment(int num, int renum) {
		HashMap map = new HashMap();
		map.put("num", num);
		map.put("renum", renum);
		
		sqlSession.delete("hueaboard.deletecomment",map);
		
	}

	@Override
	public int re_stepcount(int ref, int re_step) {
		return sqlSession.selectOne("hueaboard.updateRestep",ref);
	}

	@Override
	public int foundre_step(int ref) {
		
		return sqlSession.selectOne("hueaboard.updateRestep",ref);
	}

	@Override
	public void allcommentdelete(int num) {
		sqlSession.delete("hueaboard.allcommentdelete",num);
		
	}



}
