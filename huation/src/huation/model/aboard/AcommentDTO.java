package huation.model.aboard;

import java.sql.Timestamp;

public class AcommentDTO {
	private int num;
	private int renum;
	private String writer;
	private String content;
	private Timestamp reg_date;
	
	public void setNum(int num) {
		this.num= num;
	}
	public int getNum() {
		return num;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWriter() {
		return writer; 
		
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public int getRenum() {
		return renum;
	}
	public void setRenum(int renum) {
		this.renum = renum;
	}
	
	
	

}




