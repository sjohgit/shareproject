package huation.model.board;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.multipart.MultipartFile;

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
	public void excelinsert(BoardDTO dto) {
		int num = 0;
		num = sqlSession.selectOne("hueboard.find");
		
		if(num == 0) {
			num = 1;
		}else {
			num++;
		}
		sqlSession.insert("hueboard.excelinsert",dto);
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
	public List<BoardDTO> searchnoticelist(int start, int end, String keyword, String search_option ) {
		HashMap map = new HashMap();
		map.put("start",start);
		map.put("end", end);
		map.put("keyword", keyword);
		map.put("search_option", search_option);
		System.out.println("daoㅇ키워"+start);
		System.out.println("daoㅇ키워"+end);
		System.out.println("daoㅇ키워"+keyword);
		System.out.println("daoㅇ서ㅇㅂ"+search_option);
		
		
		return sqlSession.selectList("hueboard.boardsearchlist",map);
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
	public void uploadExcelFile(File orgExcelfile){
		
		ExcelReadOption excelReadOption = new ExcelReadOption();
		excelReadOption.setFilePath(orgExcelfile.getAbsolutePath());
		excelReadOption.setOutputColumns("A","B","C");
		excelReadOption.setStartRow(2);
        
		BoardDTO dto = new BoardDTO();
		
		 List<Map<String, String>>excelContent = ExcelRead.read(excelReadOption);

		
		for (Map<String, String> article : excelContent) {
			
			String writer = article.get("A");
			String title = article.get("B");
			String content = article.get("C");
			
			if (title != null && writer != null && content != null) {
				dto.setSubject(title);
				dto.setWriter(writer);
				dto.setContent(content);
				sqlSession.insert("hueboard.excelinsert",dto);
				}			
			}
      
         
    }
	
	//댓글
	@Override
	public void com_insert(CommentDTO dto) {
		int renum = 0;
		int num = dto.getNum();
		renum = sqlSession.selectOne("hueboard.findcomment",dto);
		renum++;
		dto.setRenum(renum);
		sqlSession.insert("hueboard.commentinsert",dto);
	}
	@Override
	public List<CommentDTO> commentlist(int start, int end, int num) {
		HashMap map = new HashMap();
		map.put("start",start);
		map.put("end", end);
		map.put("num", num);
		return sqlSession.selectList("hueboard.commentlist",map);
	}
	@Override
	public int commentcount() {
		int count = (int)sqlSession.selectOne("hueboard.commentamount");
		
		return count;
	}
	@Override
	public void deletecomment(int num, int renum) {
		HashMap map = new HashMap();
		map.put("num", num);
		map.put("renum", renum);
		
		sqlSession.delete("hueboard.deletecomment",map);
	}
	
	//답글
	@Override
	public int re_stepcount(int ref) {
	
		
		return sqlSession.update("hueboard.updateRestep",ref);
	}

}