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
				sqlSession.insert("hueboard.insert",dto);
				}			
			}
      
         
    }
}