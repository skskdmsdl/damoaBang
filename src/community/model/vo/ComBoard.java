package community.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ComBoard implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int boardnum;
	private String memberid;
	private String title;
	private String content;
	private Date enrolldate;
	private int viewcount;
	private String originalname;
	private String renamename;


	public ComBoard() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ComBoard(int boardnum, String memberid, String title, String content, Date enrolldate, int viewcount,
			String originalname, String renamename) {
		super();
		this.boardnum = boardnum;
		this.memberid = memberid;
		this.title = title;
		this.content = content;
		this.enrolldate = enrolldate;
		this.viewcount = viewcount;
		this.originalname = originalname;
		this.renamename = renamename;
	}


	public int getboardnum() {
		return boardnum;
	}

	public void setboardnum(int boardnum) {
		this.boardnum = boardnum;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}

	public int getViewcount() {
		return viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}
	
	
	public String getOriginalname() {
		return originalname;
	}

	public void setOriginalname(String originalname) {
		this.originalname = originalname;
	}

	public String getRenamename() {
		return renamename;
	}

	public void setRenamename(String renamename) {
		this.renamename = renamename;
	}

	@Override
	public String toString() {
		return "ComBoard [boardnum=" + boardnum + ", memberid=" + memberid + ", title=" + title + ", content=" + content
				+ ", enrolldate=" + enrolldate + ", viewcount=" + viewcount + ", originalname=" + originalname
				+ ", renamename=" + renamename + "]";
	}


	
}
