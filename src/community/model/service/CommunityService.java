package community.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import community.model.dao.CommunityDAO;
import community.model.vo.ComBoard;
import community.model.vo.ComBoardReply;
import community.model.vo.CommunityLike;

public class CommunityService {

	public int IncreaseOutcount(String memberId) {
		Connection conn =getConnection();
		int result = new CommunityDAO().IncreaseOutcount(conn,memberId);
		if(result >0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public List<ComBoard> selectAll() {
		Connection conn = getConnection();
		List<ComBoard> list = new CommunityDAO().selectAll(conn);
		close(conn);

		return list;
	}

	public ComBoard selectOne(int comBoardno) {
		Connection conn = getConnection();
		ComBoard cb = new CommunityDAO().selectOne(conn,comBoardno);
		close(conn);
		
		return cb;
	}

	public int insertBoard(ComBoard cb) {
		Connection conn = getConnection();
		int result = new CommunityDAO().insertBoard(conn, cb);
		if(result > 0) {
			int boardnum = new CommunityDAO().selectLastComSeq(conn);
			cb.setboardnum(boardnum);
			commit(conn);
		}
		else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public CommunityLike selectLikeOne(int board_num, String memberId) {
		Connection conn = getConnection();
		CommunityLike cl= new CommunityDAO().selectLikeOne(conn, board_num, memberId);
		close(conn);
		return cl;
	}

	public int deleteLike(CommunityLike cl) {
		Connection conn = getConnection();
		int result = new CommunityDAO().deleteLike(conn, cl);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertLike(CommunityLike like) {
		Connection conn = getConnection();
		int result = new CommunityDAO().insertLike(conn, like);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}

	public List<ComBoard> selectMemberList(String memberId) {
		Connection conn = getConnection();
		List<ComBoard> list = new CommunityDAO().selectMemberList(conn, memberId);
		close(conn);
		return list;
	}

	public int qnaCnt(String memberId) {
		Connection conn = getConnection();
		int cnt = new CommunityDAO().qnaCnt(conn, memberId);
		close(conn);
		return cnt;
	}
	
	public List<ComBoardReply> selectCommentList(int comBoardNo) {
		Connection conn = getConnection();
		List<ComBoardReply> list = new CommunityDAO().selectCommentList(conn,comBoardNo);
		close(conn);
		return list;
	}

	public int insertComReply(ComBoardReply cbr) {
		Connection conn = getConnection();
		int result = new CommunityDAO().insertComReply(conn,cbr);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteBoard(int boardno) {
		Connection conn = getConnection();
		int result = new CommunityDAO().deleteBoard(conn,boardno);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int deleteComReply(int replyno) {
		Connection conn = getConnection();
		int result = new CommunityDAO().deleteComReply(conn,replyno);
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		close(conn);
		return result;
	}
	
	public int selectreplyCount(int getboardnum) {
		Connection conn = getConnection();
		int result = new CommunityDAO().selectreplyCount(conn,getboardnum);
		close(conn);
		return result;
	}

	public List<ComBoard> selectAllzero() {
		Connection conn = getConnection();
		List<ComBoard> list = new CommunityDAO().selectAllzero(conn);
		close(conn);

		return list;
	}

	
}

