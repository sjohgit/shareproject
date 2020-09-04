package huation.model.board;

import java.sql.Timestamp;

public class BoardDTO {
	private int num;
	private String writer;
	private String subject;
	private String content;
	private Timestamp reg_date;
	private int readcount;
	
	
	
	public void setNum(int num) {
		this.num= num;
	}
	public int getNum() {
		return num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSubject() {
		return subject;
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
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getReadcount() {
		return readcount;
	}
	
}


