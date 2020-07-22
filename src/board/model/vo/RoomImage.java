package board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class RoomImage implements Serializable{

	private int img_num;
	private int board_num;
	private String originalName;
	private String renameName;
	
	public RoomImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public RoomImage(int img_num, int board_num, String originalName, String renameName) {
		super();
		this.img_num = img_num;
		this.board_num = board_num;
		this.originalName = originalName;
		this.renameName = renameName;
	}
	
	public int getImg_num() {
		return img_num;
	}
	public void setImg_num(int img_num) {
		this.img_num = img_num;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
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
	
	@Override
	public String toString() {
		return "RoomImage [img_num=" + img_num + ", board_num=" + board_num + ", originalName=" + originalName
				+ ", renameName=" + renameName + "]";
	}

	
	
}
