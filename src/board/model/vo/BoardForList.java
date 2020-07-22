package board.model.vo;

import java.sql.Date;

public class BoardForList extends Room{
	private String fileName;

	public BoardForList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BoardForList(int room_num, int board_num, Date enrolldate, String room_val, String tax_val, int price,
			String location, int size, String floor, Date movedate, int fee) {
		super(room_num, board_num, enrolldate, room_val, tax_val, price, location, size, floor, movedate, fee);
		// TODO Auto-generated constructor stub
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "BoardForList [fileName=" + fileName + "]";
	}

	
	
	
}
