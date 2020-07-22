package community.model.dao;

import static common.JDBCTemplate.close;

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

import board.model.exception.BoardException;
import community.model.vo.ComBoard;
import community.model.vo.ComBoardReply;
import community.model.vo.CommunityLike;


public class CommunityDAO {
	
private Properties prop = new Properties();
	
	public CommunityDAO() {
		try {
			//클래스객체 위치찾기 : 절대경로를 반환한다. 
			// "/" : 루트디렉토리를 절대경로로 URL객체로 반환한다.
			// "./" : 현재디렉토리를 절대경로로 URL객체로 반환한다.
			String fileName 
				= CommunityDAO.class
						  .getResource("/sql/community/community-querty.properties")
						  .getPath();
			prop.load(new FileReader(fileName));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public List<ComBoard> selectAll(Connection conn) {
		List<ComBoard> list = new ArrayList<ComBoard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAll");
			
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			 
			while(rset.next()) {
				ComBoard cb = new ComBoard();
				
				cb.setboardnum(rset.getInt("board_num"));
				cb.setMemberid(rset.getString("member_id"));
				cb.setTitle(rset.getString("title"));
				cb.setContent(rset.getString("content"));
				cb.setEnrolldate(rset.getDate("enrolldate"));
				cb.setViewcount(rset.getInt("viewcount"));
				cb.setOriginalname(rset.getString("originalname"));
				cb.setRenamename(rset.getString("renamename"));
				
				list.add(cb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(list);
		return list;
	}

	public ComBoard selectOne(Connection conn, int comBoardno) {
		ComBoard cb = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comBoardno);
			
			rset = pstmt.executeQuery();
			if(rset.next()) {
				cb = new ComBoard();
				cb.setboardnum(rset.getInt("board_num"));
				cb.setMemberid(rset.getString("member_id"));
				cb.setTitle(rset.getString("title"));
				cb.setContent(rset.getString("content"));
				cb.setEnrolldate(rset.getDate("enrolldate"));
				cb.setViewcount(rset.getInt("viewcount"));
				cb.setOriginalname(rset.getString("originalname"));
				cb.setRenamename(rset.getString("renamename"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return cb;
	}

	public int IncreaseOutcount(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseOutcount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertBoard(Connection conn, ComBoard cb) {
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertComBoard");
		
		int result = 0;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cb.getMemberid());
			pstmt.setString(2, cb.getTitle());
			pstmt.setString(3, cb.getContent());
			pstmt.setString(4, cb.getOriginalname());
			pstmt.setString(5, cb.getRenamename());
			
			result = pstmt.executeUpdate();
			
			System.out.println("result@daooooooooo="+result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int selectLastComSeq(Connection conn) {
		int boardnum = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectLastComBoardSeq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			System.out.println("pstmt======================"+pstmt);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				boardnum = rset.getInt("board_num");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		System.out.println("ComBoardnum@dao =========="+ boardnum);
		return boardnum;
	}
	
	public CommunityLike selectLikeOne(Connection conn, int board_num, String memberId) {
		CommunityLike cl = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectLikeOne");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, board_num);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				cl = new CommunityLike();
				cl.setMemberId(rset.getString("member_id"));
				cl.setBoard_num(rset.getInt("board_num"));
				cl.setLike(rset.getString("like"));
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new BoardException("로그인 후 이용해주세요",e);
		} finally {
			close(rset);
			close(pstmt);
		}
		
		
		return cl;
	}

	public int deleteLike(Connection conn, CommunityLike cl) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteLike"); 

		try {
			//미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			//쿼리문미완성
			pstmt.setString(1, cl.getMemberId());
			pstmt.setInt(2, cl.getBoard_num());
			
			//쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			//DML은 executeUpdate()
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			//e.printStackTrace();
			throw new BoardException("로그인 후 이용해주세요",e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertLike(Connection conn, CommunityLike like) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertLike");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, like.getMemberId());
			pstmt.setInt(2, like.getBoard_num());
			pstmt.setString(3, like.getLike());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			//e.printStackTrace();
			throw new BoardException("로그인 후 이용해주세요",e);
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<ComBoard> selectMemberList(Connection conn, String memberId) {
		List<ComBoard> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectMemberList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,  memberId);
			
			rset = pstmt.executeQuery();
			list = new ArrayList<>();
			while(rset.next()) {
				ComBoard cb = new ComBoard();
				cb.setboardnum(rset.getInt("board_num"));
				cb.setMemberid(rset.getString("member_id"));
				cb.setTitle(rset.getString("title"));
				cb.setContent(rset.getString("content"));
				cb.setEnrolldate(rset.getDate("enrolldate"));
				cb.setViewcount(rset.getInt("viewcount"));
				cb.setOriginalname(rset.getString("originalname"));
				cb.setRenamename(rset.getString("renamename"));
				list.add(cb);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

	public int qnaCnt(Connection conn, String memberId) {
		PreparedStatement pstmt = null;
		int cnt = 0;
		ResultSet rset = null;
		String query = prop.getProperty("qnaCnt");
		
		try{
			//미완성쿼리문을 가지고 객체생성. 
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			//쿼리문실행
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				cnt = rset.getInt("cnt");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return cnt;
	}
	
	public int insertComReply(Connection conn, ComBoardReply cbr) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertComReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,cbr.getBoardnum());
			pstmt.setString(2, cbr.getMemberid());
			pstmt.setString(3, cbr.getContent());
			pstmt.setInt(4, cbr.getLevel());
			
			System.out.println(cbr.getMemberid());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
	public List<ComBoardReply> selectCommentList(Connection conn , int comBoardNo) {
		List<ComBoardReply> list = new ArrayList<>();
		ComBoardReply cbr = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectCommentList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comBoardNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cbr = new ComBoardReply();

				cbr.setRepnum(rset.getInt("rep_num"));
				cbr.setLevel(rset.getInt("LEVEL"));
				cbr.setMemberid(rset.getString("member_id"));
				cbr.setContent(rset.getString("content"));
				cbr.setBoardnum(rset.getInt("board_num"));
				cbr.setRepref(rset.getInt("rep_ref"));
				cbr.setRecommend(rset.getInt("recommend"));
				cbr.setEnrolldate(rset.getDate("enrolldate"));
				
				list.add(cbr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	

	public int deleteBoard(Connection conn, int boardno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardno);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return result;
	}
	
	public int deleteComReply(Connection conn, int replyno) {
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteComReply");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, replyno);
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int selectreplyCount(Connection conn, int getboardnum) {
		List<ComBoardReply> list = new ArrayList<>();
		ComBoardReply cbr = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectreplyCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getboardnum);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				cbr = new ComBoardReply();

				cbr.setRepnum(rset.getInt("rep_num"));
				cbr.setLevel(rset.getInt("LEVEL"));
				cbr.setMemberid(rset.getString("member_id"));
				cbr.setContent(rset.getString("content"));
				cbr.setBoardnum(rset.getInt("board_num"));
				cbr.setRepref(rset.getInt("rep_ref"));
				cbr.setRecommend(rset.getInt("recommend"));
				cbr.setEnrolldate(rset.getDate("enrolldate"));
				
				list.add(cbr);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list.size();
	}

	public List<ComBoard> selectAllzero(Connection conn) {
		List<ComBoard> list = new ArrayList<ComBoard>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectAllzero");
			
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			 
			while(rset.next()) {
				ComBoard cb = new ComBoard();
				
				cb.setboardnum(rset.getInt("board_num"));
				cb.setMemberid(rset.getString("member_id"));
				cb.setTitle(rset.getString("title"));
				cb.setContent(rset.getString("content"));
				cb.setEnrolldate(rset.getDate("enrolldate"));
				cb.setViewcount(rset.getInt("viewcount"));
				cb.setOriginalname(rset.getString("originalname"));
				cb.setRenamename(rset.getString("renamename"));
				
				list.add(cb);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		System.out.println(list);
		return list;
	}

}

