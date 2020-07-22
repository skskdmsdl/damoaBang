package broker.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import broker.model.vo.Broker;
import member.model.vo.Member;

import static common.JDBCTemplate.*;

public class BrokerDAO {

	private Properties prop = new Properties();
	
	public BrokerDAO() {
		//build-path의 절대경로를 가져오기
		String fileName 
			= BrokerDAO.class.getResource("/sql/broker/broker-query.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Broker selectOne(Connection conn, String br_cp_id) {
		Broker b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = prop.getProperty("selectOne");
		
		try{
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, br_cp_id);
			//쿼리문실행
			//완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			rset = pstmt.executeQuery();
			
			if(rset.next()){
				b = new Broker();
				
				b.setBr_cp_id(rset.getString("br_cp_id"));
				b.setEmail(rset.getString("email"));
				b.setPassword(rset.getString("password"));
				b.setBr_cp_name(rset.getString("br_cp_name"));
				b.setBr_name(rset.getString("br_name"));
				b.setPhone(rset.getString("phone"));
				b.setJoindate(rset.getDate("joindate"));
				b.setInsurance(rset.getString("insurance"));
				b.setSellcount(rset.getInt("sellcount"));
				b.setEnrolldate(rset.getDate("enrolldate"));
				b.setOutcount(rset.getInt("outcount"));
				b.setProfile(rset.getString("profile"));
				b.setBlack(rset.getString("black"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
//		System.out.println("b@DAO = "+b);
		return b;
	}

	
	public int insertBroker(Connection conn, Broker newBroker) {
		int result = 0;
		//실제 실행될 쿼리 객체
		PreparedStatement pstmt = null;
		//쿼리객체에 전달할 미완성 쿼리
		String sql = prop.getProperty("insertBroker");
		
		try {
			//1. Statement객체 생성(미완성 쿼리 값 대입)
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newBroker.getBr_cp_id());
			pstmt.setString(2, newBroker.getEmail());
			pstmt.setString(3, newBroker.getPassword());
			pstmt.setString(4, newBroker.getBr_cp_name());
			pstmt.setString(5, newBroker.getBr_name());
			pstmt.setString(6, newBroker.getPhone());
			pstmt.setDate(7, newBroker.getJoindate());
			pstmt.setString(8, newBroker.getInsurance());
			
			//2. 실행 : executeUpdate
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//3. 자원반납
			close(pstmt);
		}
		
		return result;
	}
	

	private Date getJoindate() {
		// TODO Auto-generated method stub
		return null;
	}


	public int updateMember(Connection conn, Member updateMember) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateMember.getEmail());
			pstmt.setString(2, updateMember.getMemberId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
//		System.out.println("result@dao="+result);
		return result;
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
	
	public int selectTotalBlackContents(Connection conn) {
		int totalContents = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectTotalBlackContents");
		
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
	

	public List<Broker> selectAll(Connection conn, int cPage, int numPerPage) {
		List<Broker> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllPaging");
			
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(2, cPage * numPerPage);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				Broker b = new Broker();
				b.setBr_cp_id(rset.getString("br_cp_id"));
				b.setEmail(rset.getString("email"));
				b.setPassword(rset.getString("password"));
				b.setBr_cp_name(rset.getString("br_cp_name"));
				b.setBr_name(rset.getString("br_name"));
				b.setPhone(rset.getString("phone"));
				b.setJoindate(rset.getDate("joindate"));
				b.setInsurance(rset.getString("insurance"));
				b.setSellcount(rset.getInt("sellcount"));
				b.setEnrolldate(rset.getDate("enrolldate"));
				b.setOutcount(rset.getInt("outcount"));
				b.setProfile(rset.getString("profile"));
				b.setBlack(rset.getString("black"));
				if(!b.getBlack().equals("T")) {
					list.add(b);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public List<Broker> selectBlackAll(Connection conn, int cPage, int numPerPage) {
		List<Broker> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBlackAllPaging");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage - 1) * numPerPage + 1);
			pstmt.setInt(2, cPage * numPerPage);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				Broker b = new Broker();
				b.setBr_cp_id(rset.getString("br_cp_id"));
				b.setEmail(rset.getString("email"));
				b.setPassword(rset.getString("password"));
				b.setBr_cp_name(rset.getString("br_cp_name"));
				b.setBr_name(rset.getString("br_name"));
				b.setPhone(rset.getString("phone"));
				b.setJoindate(rset.getDate("joindate"));
				b.setInsurance(rset.getString("insurance"));
				b.setSellcount(rset.getInt("sellcount"));
				b.setEnrolldate(rset.getDate("enrolldate"));
				b.setOutcount(rset.getInt("outcount"));
				b.setProfile(rset.getString("profile"));
				b.setBlack(rset.getString("black"));
				if(b.getBlack().equals("T")) {
					list.add(b);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int updateBrBlack(Connection conn, Broker updateBr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBrBalck");
		//update broker set black = ? where br_cp_id = ?
		
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, updateMember.getPassword());
			pstmt.setString(1, updateBr.getBlack());
			pstmt.setString(2, updateBr.getBr_cp_id());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		System.out.println("result@dao="+result);
		
		return result;
	}

	public int updateReport(Connection conn, Broker reportBr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateReport");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reportBr.getOutcount());
			pstmt.setString(2, reportBr.getBr_cp_id());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("dao@"+result);
		return result;
	}

	public int updatePassword(Connection conn, String br_cp_id, String newPassword) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePassword");
		//update member set password = ? where member_id = ?
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, br_cp_id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
//		System.out.println("result@dao = " + result);
		
		return result;
	}

	public int updateBroker(Connection conn, Broker updateBroker) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBroker");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateBroker.getEmail());
			pstmt.setString(2, updateBroker.getPhone());
			pstmt.setString(3, updateBroker.getBr_cp_id());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
//		System.out.println("result@dao="+result);
		return result;
	}

	public int updateDeal(Connection conn, Broker deal) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateDeal");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deal.getSellcount());
			pstmt.setString(2, deal.getBr_cp_id());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		System.out.println("dao@"+result);
		return result;
	}
	

	

}
