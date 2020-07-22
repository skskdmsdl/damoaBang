package community.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class ComBoardReply implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int repnum;
	private int boardnum;
	private String memberid;
	private int repref;
	private String content;
	private int recommend;
	private int level;
	private Date enrolldate;
	
	public ComBoardReply() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ComBoardReply(int repnum, int boardnum, String memberid, int repref, String content, int recommend,
			int level, Date enrolldate) {
		super();
		this.repnum = repnum;
		this.boardnum = boardnum;
		this.memberid = memberid;
		this.repref = repref;
		this.content = content;
		this.recommend = recommend;
		this.level = level;
		this.enrolldate = enrolldate;
	}

	public int getRepnum() {
		return repnum;
	}

	public void setRepnum(int repnum) {
		this.repnum = repnum;
	}

	public int getBoardnum() {
		return boardnum;
	}

	public void setBoardnum(int boardnum) {
		this.boardnum = boardnum;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public int getRepref() {
		return repref;
	}

	public void setRepref(int repref) {
		this.repref = repref;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}

	@Override
	public String toString() {
		return "ComBoardReply [repnum=" + repnum + ", boardnum=" + boardnum + ", memberid=" + memberid + ", repref="
				+ repref + ", content=" + content + ", recommend=" + recommend + ", level=" + level + ", enrolldate="
				+ enrolldate + "]";
	}
	
	
}
