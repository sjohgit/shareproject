package huation.model.aboard;

import java.util.List;

import huation.model.board.BoardDTO;


public interface AboardImpl {
	public void insert(AboardDTO dto);
	public int noticecount();
	public List<AboardDTO> noticelist(int start, int end);
	public List<AboardDTO> searchnoticelist(int start, int end,String keyword, String search_option);
	public List<AboardDTO> sel_notice(int num);
	public int addreadcount(int num);
	public void update(AboardDTO dto);
	public void delete(int num);
	public void allcommentdelete(int num);

	
	//댓글public void com_insert(CommentDTO dto); 
	public List<AcommentDTO> commentlist(int start, int end, int num);
	public int commentcount();
	public void deletecomment(int num, int renum);
	public void com_insert(AcommentDTO dto);
	//답글에 대한 메서드
	
	public int re_stepcount(int ref,int re_step);
	public int foundre_step(int ref);
	public void replyinsert(AboardDTO dto);
}
