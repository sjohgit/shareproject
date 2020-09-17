package huation.model.aboard;

import java.util.List;

public interface AboardImpl {
	public void insert(AboardDTO dto);
	public int noticecount();
	public List<AboardDTO> noticelist(int start, int end);
	public List<AboardDTO> sel_notice(int num);
	public int addreadcount(int num);
	public void update(AboardDTO dto);
	public void delete(int num);
	public List<AboardDTO> excellist();
	

}
