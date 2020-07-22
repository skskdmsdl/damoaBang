package board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class RoomBoard implements Serializable{

	private int board_num;
	private String br_cp_id;
	private String board_title;
	private String content;
	private Date enrolldate;
	private int viewCount;
	private String originalName;
	private String renameName;
	private String ok;
	
	public RoomBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomBoard(int board_num, String br_cp_id, String board_title, String content, Date enrolldate, int viewCount,
			String originalName, String renameName, String ok) {
		super();
		this.board_num = board_num;
		this.br_cp_id = br_cp_id;
		this.board_title = board_title;
		this.content = content;
		this.enrolldate = enrolldate;
		this.viewCount = viewCount;
		this.originalName = originalName;
		this.renameName = renameName;
		this.ok = ok;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getBr_cp_id() {
		return br_cp_id;
	}

	public void setBr_cp_id(String br_cp_id) {
		this.br_cp_id = br_cp_id;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
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

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getOriginalName() {
		return originalName;
	}

	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}

	public String getRenameName() {
		return renameName;
	}

	public void setRenameName(String renameName) {
		this.renameName = renameName;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String ok) {
		this.ok = ok;
	}

	@Override
	public String toString() {
		return "RoomBoard [board_num=" + board_num + ", br_cp_id=" + br_cp_id + ", board_title=" + board_title
				+ ", content=" + content + ", enrolldate=" + enrolldate + ", viewCount=" + viewCount + ", originalName="
				+ originalName + ", renameName=" + renameName + ", ok=" + ok + "]";
	}

}
