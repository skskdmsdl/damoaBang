package broker.model.vo;
import java.io.Serializable;
import java.sql.Date;

public class Broker implements Serializable {
	
	private String br_cp_id;
	private String email;
	private String password;
	private String br_cp_name;
	private String br_name;
	private String phone;
	private Date joindate;
	private String insurance;
	private int sellcount;
	private Date enrolldate;
	private int outcount;
	private String profile;
	private String black;
	
	public Broker() {
		super();

	}

	public Broker(String br_cp_id, String email, String password, String br_cp_name, String br_name, String phone,
			Date joindate, String insurance, int sellcount, Date enrolldate, int outcount, String profile, String black) {
		super();
		this.br_cp_id = br_cp_id;
		this.email = email;
		this.password = password;
		this.br_cp_name = br_cp_name;
		this.br_name = br_name;
		this.phone = phone;
		this.joindate = joindate;
		this.insurance = insurance;
		this.sellcount = sellcount;
		this.enrolldate = enrolldate;
		this.outcount = outcount;
		this.profile = profile;
		this.black = black;
	}

	public String getBr_cp_id() {
		return br_cp_id;
	}

	public void setBr_cp_id(String br_cp_id) {
		this.br_cp_id = br_cp_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBr_cp_name() {
		return br_cp_name;
	}

	public void setBr_cp_name(String br_cp_name) {
		this.br_cp_name = br_cp_name;
	}

	public String getBr_name() {
		return br_name;
	}

	public void setBr_name(String br_name) {
		this.br_name = br_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public int getSellcount() {
		return sellcount;
	}

	public void setSellcount(int sellcount) {
		this.sellcount = sellcount;
	}

	public Date getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(Date enrolldate) {
		this.enrolldate = enrolldate;
	}

	public int getOutcount() {
		return outcount;
	}

	public void setOutcount(int outcount) {
		this.outcount = outcount;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public String getBlack() {
		return black;
	}
	
	public void setBlack(String black) {
		this.black = black;
	}

	@Override
	public String toString() {
		return "Broker [br_cp_id=" + br_cp_id + ", email=" + email + ", password=" + password + ", br_cp_name="
				+ br_cp_name + ", br_name=" + br_name + ", phone=" + phone + ", joindate=" + joindate + ", insurance="
				+ insurance + ", sellcount=" + sellcount + ", enrolldate=" + enrolldate + ", outcount=" + outcount
				+ ", profile=" + profile + ", black=" + black + "]";
	}

}
