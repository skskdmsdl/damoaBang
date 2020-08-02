package board.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.service.BoardService;
import board.model.vo.Room;
import board.model.vo.RoomBoard;
import common.WebFileRenamePolicy;
import member.model.exception.memberException;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/board/enroll")
public class BoardEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//memberEnroll.jsp view단 forwarding 처리
		
		
		 RequestDispatcher reqDispatcher =
		  request.getRequestDispatcher("/WEB-INF/board/insertBoard.jsp");
		 reqDispatcher.forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.roomBoard
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new memberException("enctype오류!");
		}
		int fileMaxSize = 10 * 1024 * 1024;
		FileRenamePolicy policy = new WebFileRenamePolicy();
		String saveDirectory = getServletContext().getRealPath("/") + "/upload/board";
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, fileMaxSize, "utf-8", policy);
		
		//최대 파일 5개까지만
		for(int i=0; i<5; i++) {
			if(multi.getOriginalFileName("f"+i).equals("")) return;
			else {
				String br_cp_id = multi.getParameter("br_cp_id");
				String board_title = multi.getParameter("board_title");
				String content = multi.getParameter("boardContent");
				String originalName = multi.getOriginalFileName("f"+i);
				String renameName = multi.getFilesystemName("f"+i);
				
				//RoomBoard객체로 만들기
				RoomBoard newRoomBoard = new RoomBoard(0, br_cp_id, board_title, content, null, 0, originalName, renameName, "F");
				//System.out.println("newRoomBoard="+newRoomBoard);
				
				//업무로직
				int result = new BoardService().insertBoard(newRoomBoard);
				//System.out.println("result@boardEnrollServlet="+result);
			}
		}
				
		
		//1. 사용자입력값 처리(room)
		String room_val = multi.getParameter("room_val");
		String tax_val = multi.getParameter("tax_val");
		int price = Integer.parseInt(multi.getParameter("price"));
		int fee = Integer.parseInt(multi.getParameter("fee"));
		int size = Integer.parseInt(multi.getParameter("size"));
		String movedate = multi.getParameter("movedate");
		String floor = multi.getParameter("floor");
		String location = multi.getParameter("location");
		
		
		//날짜타입으로 변경 : 1990-09-09
		Date movedate_ = null;
		if(movedate != null && !"".equals(movedate))
			movedate_ = Date.valueOf(movedate);
		
		//Room객체로 만들기
		Room newRoom = new Room(0, 0, null, room_val, tax_val, price, location, size, floor, movedate_, fee);
		
		//업무로직
		int result = new BoardService().insertRoom(newRoom);
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		/*
		 * //3. 업무로직: db에 insert (DML -> int =>1,0) int result = new
		 * MemberService().insertMember(newMember);
		 * System.out.println("result@memberEnrollServlet="+result);
		 * 
		 * //4. 사용자 응답처리 : msg.jsp String msg = result > 0 ? "회원 가입 성공!" : "회원 가입 실패!";
		 * String loc = "/"; //회원 가입 성공 후 인덱스 페이지로 가도록 설정
		 * 
		 * request.setAttribute("msg", msg); request.setAttribute("loc", loc);
		 * 
		 * request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(
		 * request, response);
		 */
	}

}
