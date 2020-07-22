package board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Room implements Serializable{

	private int room_num;
	private int board_num;
	private Date enrolldate;
	private String room_val;
	private String tax_val;
	private int price;
	private String location;
	private int size;
	private String floor;
	private Date movedate;
	private int fee;
	
	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(int room_num, int board_num, Date enrolldate, String room_val, String tax_val, int price,
			String location, int size, String floor, Date movedate, int fee) {
		super();
		this.room_num = room_num;
		this.board_num = board_num;
		this.enrolldate = enrolldate;
		this.room_val = room_val;
		this.tax_val = tax_val;
		this.price = price;
		this.location = location;
		this.size = size;
		this.floor = floor;
		this.movedate = movedate;
		this.fee = fee;
	}

	public int getRoom_num() {
		return room_num;
	}

	public void setRoom_num(int room_num) {
		this.room_num = room_num;
	}

	public int getBoard_num() {
		return board_num;
	}

	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}

	public Date getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}

	public String getRoom_val() {
		return room_val;
	}

	public void setRoom_val(String room_val) {
		this.room_val = room_val;
	}

	public String getTax_val() {
		return tax_val;
	}

	public void setTax_val(String tax_val) {
		this.tax_val = tax_val;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public Date getMovedate() {
		return movedate;
	}

	public void setMovedate(Date movedate) {
		this.movedate = movedate;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	@Override
	public String toString() {
		return "Room [room_num=" + room_num + ", board_num=" + board_num + ", enrolldate=" + enrolldate + ", room_val="
				+ room_val + ", tax_val=" + tax_val + ", price=" + price + ", location=" + location + ", size=" + size
				+ ", floor=" + floor + ", movedate=" + movedate + ", fee=" + fee + "]";
	}

}
