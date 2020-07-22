package broker.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import broker.model.dao.BrokerDAO;
import broker.model.vo.Broker;
import member.model.vo.Member;

public class BrokerService {
	
	private BrokerDAO brokerDAO = new BrokerDAO();

	
	public Broker selectOne(String br_cp_id) {
		Connection conn = getConnection();
		Broker b = brokerDAO.selectOne(conn, br_cp_id); 
		close(conn); //
		return b;
	}
	
	public int insertBroker(Broker newBroker) {
		Connection conn = getConnection();
		
		//dao단에 요청
		int result = brokerDAO.insertBroker(conn, newBroker);
		
		//트랜잭션 처리
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		//자원반납
		close(conn);
		
		return result;
	}

	public int selectTotalContents() {
		Connection conn = getConnection();
		int totalContents = brokerDAO.selectTotalContents(conn);
		close(conn);
		return totalContents;
	}
	
	public int selectTotalBlackContents() {
		Connection conn = getConnection();
		int totalContents = brokerDAO.selectTotalBlackContents(conn);
		close(conn);
		return totalContents;
	}
	

	public List<Broker> selectAll(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Broker> list = brokerDAO.selectAll(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	
	 public List<Broker> selectBlackAll(int cPage, int numPerPage) { 
		 Connection conn = getConnection(); 
		 List<Broker> list = brokerDAO.selectBlackAll(conn,	 cPage, numPerPage); 
		 close(conn);
		 return list; 
	 }

	public int updateBrBlacklist(Broker updateBr) {
		Connection conn = getConnection();
		int result = brokerDAO.updateBrBlack(conn, updateBr);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updateReport(Broker reportBr) {
		Connection conn = getConnection();
		int result = brokerDAO.updateReport(conn, reportBr);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updatePassword(String br_cp_id, String newPassword) {
		Connection conn = getConnection();
		int result = brokerDAO.updatePassword(conn, br_cp_id, newPassword);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updateMember(Broker updateBroker) {
		Connection conn = getConnection();
		int result = brokerDAO.updateBroker(conn, updateBroker);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updateDeal(Broker deal) {
		Connection conn = getConnection();
		int result = brokerDAO.updateDeal(conn, deal);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	 



	

	/*
	 * public int updateMember(Member updateMember) { Connection conn =
	 * getConnection(); int result = memberDAO.updateMember(conn, updateMember);
	 * if(result > 0) commit(conn); else rollback(conn); close(conn); return result;
	 * 
	 * }
	 */


	
	

}
