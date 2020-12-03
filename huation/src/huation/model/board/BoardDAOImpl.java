package huation.model.board;

import java.util.List;

import huation.model.chart.ChartDTO;



public interface BoardDAOImpl {
	public int insert(BoardDTO dto);
	public void replyinsert(BoardDTO dto);
	public void excelinsert(BoardDTO dto);
	public int noticecount();
	public List<BoardDTO> noticelist(int start, int end);
	public List<BoardDTO> searchnoticelist(int start, int end,String keyword, String search_option);
	public List<BoardDTO> sel_notice(int num);
	public List<ChartDTO> chart();
	public int addreadcount(int num);
	public void update(BoardDTO dto);
	public void delete(int num);
	public List<BoardDTO> excellist();
	
	
	//댓글
	public void com_insert(CommentDTO dto); 
	public List<CommentDTO> commentlist(int start, int end, int num);
	public int commentcount();
	public void deletecomment(int num, int renum);

	//답글에 대한 메서드
	
	public int re_stepcount(int ref,int re_step);
	public int foundre_step(int ref);


}
