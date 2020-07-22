package board.model.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import board.model.dao.BoardDAO;
import board.model.vo.BoardComment;
import board.model.vo.BoardForList;
import board.model.vo.BoardLike;
import board.model.vo.Room;
import board.model.vo.RoomBoard;
import board.model.vo.RoomImage;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class BoardService {
	private BoardDAO boardDAO = new BoardDAO();


	public int insertBoard(RoomBoard newRoomBoard) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertBoard(conn, newRoomBoard);
		//트랜잭션 처리
		if(result>0) {
			int boardNo = new BoardDAO().selectLastBoardSeq(conn);
			//controller에서도 board객체의 참조주소를 통해 접근할 수 있다.
			newRoomBoard.setBoard_num(boardNo);
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertRoom(Room newRoom) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertRoom(conn, newRoom);
		//트랜잭션 처리
		if(result>0) {
			int roomNo = new BoardDAO().selectLastRoomSeq(conn);
			//controller에서도 board객체의 참조주소를 통해 접근할 수 있다.
			newRoom.setRoom_num(roomNo);
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public int insertBoardImg(RoomImage newRoomImg) {
		Connection conn = getConnection();
		int result = new BoardDAO().insertRoomImg(conn, newRoomImg);
		//트랜잭션 처리
		if(result>0) {
			int imgNo = new BoardDAO().selectLastRoomImgSeq(conn);
			//controller에서도 board객체의 참조주소를 통해 접근할 수 있다.
			newRoomImg.setImg_num(imgNo);
			commit(conn);
		}
		else 
			rollback(conn);
		close(conn);
		return result;
	}

	public List<RoomBoard> selectBoardList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<RoomBoard> list= boardDAO.selectBoardList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectBoardCount() {
		Connection conn = getConnection();
		int totalContents = boardDAO.selectBoardCount(conn);
		close(conn);
		return totalContents;
	}

	public int updateOk(RoomBoard rb) {
		Connection conn = getConnection();
		int result = new BoardDAO().updateOk(conn, rb);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public RoomBoard selectRoomBoardOne(int board_num, boolean hasRead) {
		Connection conn = getConnection();
		int result = 0;
		//조회수 증가
		if(!hasRead)
			result = boardDAO.increaseBoardReadCount(conn, board_num);
		
		RoomBoard board = boardDAO.selectRoomBoardOne(conn, board_num);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return board;
	}

	public Room selectRoomOne(int board_num) {
		Connection conn = getConnection();
		int result = 0;
		Room room = boardDAO.selectRoomOne(conn, board_num);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return room;
	}

	public List<RoomImage> selectRoomImgList(int board_num) {
		Connection conn = getConnection();
		List<RoomImage> list= boardDAO.selectRoomImgList(conn, board_num);
		close(conn);
		return list;
	}

	public int selectTotalContents(String room_val, String tax_val, int price, int fee) {
		Connection conn = getConnection();
		int totalContents = boardDAO.selectTotalContents(conn, room_val, tax_val, price, fee);
		close(conn);
		return totalContents;
	}

	public List<RoomBoard> searchBoardList(String room_val, String tax_val, int price, int fee, int cPage,
			int numPerPage) {
		Connection conn = getConnection();
		List<RoomBoard> list = boardDAO.searchBoardList(conn, room_val, tax_val, price, fee, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<RoomBoard> searchBoardList(String location, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<RoomBoard> list = boardDAO.searchBoardList(conn, location, cPage, numPerPage);
		close(conn);
		return list;
	}

	public int selectTotalContents(String location) {
		Connection conn = getConnection();
		int totalContents = boardDAO.selectTotalContents(conn, location);
		close(conn);
		return totalContents;
	}

	public RoomBoard selectOne(int board_num) {
		Connection conn = getConnection();
		int result = 0;
		RoomBoard rb = boardDAO.selectOne(conn, board_num);
		
		if(result > 0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return rb;
	}

	public int updateBoard(RoomBoard newRoomBoard) {
		Connection conn = getConnection();
		int result = boardDAO.updateBoard(conn, newRoomBoard);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int updateRoom(Room newRoom) {
		Connection conn = getConnection();
		int result = boardDAO.updateRoom(conn, newRoom);
		if(result > 0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}

	public int deleteBoard(int board_num) {
		Connection conn = getConnection();
		int result = new BoardDAO().deleteBoard(conn, board_num);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		
		return result;
	}

	public static List<RoomBoard> searchMyBoardList(String br_cp_id, int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<RoomBoard> list = new BoardDAO().searchMyBoardList(conn, br_cp_id, cPage, numPerPage);
		close(conn);
		return list;
	}

	public static int selectMyBoardCount(String br_cp_id) {
		Connection conn = getConnection();
		int totalContents = new BoardDAO().selectMyBoardCount(conn, br_cp_id);
		close(conn);
		return totalContents;
	}

	public BoardLike selectLikeOne(int board_num, String memberId) {
		Connection conn = getConnection();
		BoardLike bl= new BoardDAO().selectLikeOne(conn, board_num, memberId);
		close(conn);
		return bl;
	}

	public int insertLike(BoardLike like) {
		Connection conn = getConnection();
		//dao단에 요청
		int result = new BoardDAO().insertLike(conn, like);
		
		//트랜잭션 처리
		if(result > 0)
			commit(conn);
		else
			rollback(conn);
		
		//자원반납
		close(conn);
		return result;
	}

	public int deleteLike(BoardLike bl) {
		Connection conn = getConnection();
		int result = new BoardDAO().deleteLike(conn, bl);
		if(result>0)
			commit(conn);
		else 
			rollback(conn);
		close(conn);
		return result;
	}

//	public static List<BoardLike> selectLikeList(String memberId) {
//		Connection conn = getConnection();
//		List<BoardLike> list = new BoardDAO().selectLikeList(conn, memberId);
//		close(conn);
//		return list;
//	}

	public static int selectMyLikeCount(String memberId) {
		Connection conn = getConnection();
		int totalContents = new BoardDAO().selectMyLikeCount(conn, memberId);
		close(conn);
		return totalContents;
	}

//	public static List<RoomBoard> searchMyLikeList(int board_num, int cPage, int numPerPage) {
//		Connection conn = getConnection();
//		List<RoomBoard> list = new BoardDAO().searchMyLikeList(conn, board_num, cPage, numPerPage);
//		close(conn);
//		return list;
//	}

	public static List<RoomBoard> selectLikeList(String memberId, int cPage, int numPerPage) {
	      Connection conn = getConnection();
	      List<RoomBoard> list = new BoardDAO().searchMyLikeList(conn, memberId, cPage, numPerPage);
	      close(conn);
	      return list;
	   }



	
}
