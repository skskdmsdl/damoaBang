package member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import member.model.vo.Member;

import static common.JDBCTemplate.*;

public class MemberDAO {

	private Properties prop = new Properties();
	
	public MemberDAO() {
		//build-path의 절대경로를 가져오기
		String fileName 
			= MemberDAO.class.getResource("/sql/member/member-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member selectOne(Connection conn, String memberId) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, memberId);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				m = new Member();
				//컬럼명은 대소문자 구분이 X
				m.setMemberId(rset.getString("member_id"));
				m.setEmail(rset.getString("email"));
				m.setPassword(rset.getString("password"));
				m.setMemberRole(rset.getString("member_role"));
				m.setPhone(rset.getString("phone"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				m.setOutCount(rset.getInt("outcount"));
				m.setProfile(rset.getString("profile"));
				m.setBlack(rset.getString("black"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
//		System.out.println("m@DAO = "+m);
		return m;
	}

	public int insertMember(Connection conn, Member newMember) {
		int result = 0;
		//실제 실행될 쿼리 객체
		PreparedStatement pstmt = null;
		//쿼리객체에 전달할 미완성 쿼리
		String sql = prop.getProperty("insertMember");
		
		try {
			//1. Statement객체 생성(미완성 쿼리 값 대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newMember.getMemberId());
			pstmt.setString(2, newMember.getEmail());
			pstmt.setString(3, newMember.getPassword());
			pstmt.setString(4, newMember.getMemberRole());
			pstmt.setString(5, newMember.getPhone());
			
			//2. 실행 : executeUpdate
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3. 자원반납
			close(pstmt);
		}
		
		System.out.println("result@dao="+result);
		return result;
	}

	public int updateMember(Connection conn, Member updateMember) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateMember.getEmail());
			pstmt.setString(2, updateMember.getPhone());
			pstmt.setString(3, updateMember.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
//		System.out.println("result@dao="+result);
		return result;
	}

	public Member selectOneEmail(Connection conn, String email) {
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOneEmail");
		
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, email);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				m = new Member();
				//컬럼명은 대소문자 구분이 X
				m.setMemberId(rset.getString("member_id"));
				m.setEmail(rset.getString("email"));
				m.setPassword(rset.getString("password"));
				m.setMemberRole(rset.getString("member_role"));
				m.setPhone(rset.getString("phone"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				m.setOutCount(rset.getInt("outcount"));
				m.setProfile(rset.getString("profile"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
//		System.out.println("m@DAO = "+m);
		return m;
	}

	public List<Member> selectAll(Connection conn, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllPaging");
			
		try {
			pstmt = conn.prepareStatement(sql);
			//cPage=1, numPerPage=10 : 1,10
			//cPage=2, numPerPage=10 : 11,20
			//cPage=3, numPerPage=10 : 21,30
			//....
			pstmt.setInt(1, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(2, cPage * numPerPage);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setEmail(rset.getString("email"));
				m.setPassword(rset.getString("password"));
				m.setMemberRole(rset.getString("member_role"));
				m.setPhone(rset.getString("phone"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				m.setOutCount(rset.getInt("outcount"));
				m.setProfile(rset.getString("profile"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
//		System.out.println("list@dao = " + list);
		
		return list;
	}

	public int selectTotalContents(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalContents");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContents = rset.getInt("TOTAL_CONTENTS");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return totalContents;
	}

	public List<Member> searchMember(Connection conn, String searchType, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("searchMemberPaging");
//		select * from member where # like ?
//		select * from member where member_name like ?
		
		String columnName = "";
		switch(searchType) {
		case "memberId": columnName = "member_id"; break;
		case "email": columnName = "email"; break;
		case "memberRole": columnName = "member_role"; break;
		}
		
		sql = sql.replace("#", columnName);
		System.out.println("sql@dao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setInt(2, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(3, cPage * numPerPage);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("member_id"));
				m.setEmail(rset.getString("email"));
				m.setPassword(rset.getString("password"));
				m.setMemberRole(rset.getString("member_role"));
				m.setPhone(rset.getString("phone"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				m.setOutCount(rset.getInt("outcount"));
				m.setProfile(rset.getString("profile"));
				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("list@dao = " + list);
		
		return list;
	}
	
	public int selectTotalContents(Connection conn, String searchType, String searchKeyword) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectFinderTotalContents");
		
		String columnName = "";
		switch(searchType) {
		case "memberId": columnName = "member_id"; break;
		case "email": columnName = "email"; break;
		case "memberRole": columnName = "member_role"; break;
		}
		
		sql = sql.replace("#", columnName);
		System.out.println("sql@dao = " + sql);
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			
			rset = pstmt.executeQuery();
			if(rset.next())
				totalContents = rset.getInt("TOTAL_CONTENTS");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		System.out.println("totalContents@dao = " + totalContents);
		return totalContents;
	}

	public int updatePassword(Connection conn, String memberId, String newPassword) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePassword");
		//update member set password = ? where member_id = ?
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
//		System.out.println("result@dao = " + result);
		
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, memberId);
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateMemberProfile(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMemberProfile");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getProfile());
			pstmt.setString(2, m.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
//		System.out.println("result@dao="+result);
		return result;
	}

	public int updatePassword(Connection conn, Member updateMember) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePasswordSend");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateMember.getPassword());
			pstmt.setString(2, updateMember.getEmail());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

}
