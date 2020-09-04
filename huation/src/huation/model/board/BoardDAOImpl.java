package huation.model.board;

import java.util.List;

public interface BoardDAOImpl {
	public void insert(BoardDTO dto);
	public int noticecount();
	public List<BoardDTO> noticelist(int start, int end);
	public List<BoardDTO> sel_notice(int num);
	public int addreadcount(int num);
	public void update(BoardDTO dto);
	public void delete(int num);
	public List<BoardDTO> excellist(BoardDTO dto);
	
	

}
