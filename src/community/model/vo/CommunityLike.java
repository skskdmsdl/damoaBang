package community.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class CommunityLike implements Serializable{

	private String memberId;
	private int board_num;
	private String like;
	
	public CommunityLike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommunityLike(String memberId, int board_num, String like) {
		super();
		this.memberId = memberId;
		this.board_num = board_num;
		this.like = like;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public String getLike() {
		return like;
	}

	public void setLike(String like) {
		this.like = like;
	}

	@Override
	public String toString() {
		return "CommunityLike [memberId=" + memberId + ", board_num=" + board_num + ", like=" + like + "]";
	}
}
