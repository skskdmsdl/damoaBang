package member.model.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class Member implements Serializable, HttpSessionBindingListener{

	private String memberId;
	private String email;
	private String password;
	private String memberRole;
	private String phone;
	private Date enrollDate;
	private int outCount;
	private String profile;
	private String black;
	
	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Member(String memberId, String email, String password, String memberRole, String phone, Date enrollDate,
			int outCount, String profile,String black) {
		super();
		this.memberId = memberId;
		this.email = email;
		this.password = password;
		this.memberRole = memberRole;
		this.phone = phone;
		this.enrollDate = enrollDate;
		this.outCount = outCount;
		this.profile = profile;
		this.black = black;
	}

	


	public String getMemberId() {
		return memberId;
	}



	public void setMemberId(String memberId) {
		this.memberId = memberId;
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



	public String getMemberRole() {
		return memberRole;
	}



	public void setMemberRole(String memberRole) {
		this.memberRole = memberRole;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public Date getEnrollDate() {
		return enrollDate;
	}



	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}



	public int getOutCount() {
		return outCount;
	}



	public void setOutCount(int outCount) {
		this.outCount = outCount;
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
		return "Member [memberId=" + memberId + ", email=" + email + ", password=" + password + ", memberRole="
				+ memberRole + ", phone=" + phone + ", enrollDate=" + enrollDate + ", outCount=" + outCount
				+ ", profile=" + profile + "] , black=" + black + "]";
	}



	@Override
	public void valueBound(HttpSessionBindingEvent e) {
		//로그인한경우
		System.out.println( "(" + memberId + ")님이 로그인했습니다!" );
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent e) {
		//로그아웃한경우
		System.out.println("(" + memberId + ")님이 로그아웃했습니다!" );
	}
	
	
}
