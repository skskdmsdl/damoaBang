package brokerBoard.controller;

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
import board.model.vo.RoomImage;
import common.WebFileRenamePolicy;
import member.model.exception.memberException;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/brokerBoard/enroll")
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
		//1.roomBoard
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new memberException("enctype오류!");
		}
		int fileMaxSize = 10 * 1024 * 1024;
		FileRenamePolicy policy = new WebFileRenamePolicy();
		String saveDirectory = getServletContext().getRealPath("/") + "/upload/board";
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, fileMaxSize, "utf-8", policy);
		
		String br_cp_id = multi.getParameter("br_cp_id");
		String board_title = multi.getParameter("board_title");
		String content = multi.getParameter("boardContent");
		String originalName = multi.getOriginalFileName("f0");
		String renameName = multi.getFilesystemName("f0");
		
		//RoomBoard객체로 만들기
		RoomBoard newRoomBoard = new RoomBoard(0, br_cp_id, board_title, content, null, 0, originalName, renameName, "F");
		//System.out.println("newRoomBoard="+newRoomBoard);
		
		//업무로직
		int result1 = new BoardService().insertBoard(newRoomBoard);
		//System.out.println("result@boardEnrollServlet="+result1);
		
		
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
		//System.out.println("newRoom="+newRoom);
		
		//업무로직
		int result2 = new BoardService().insertRoom(newRoom);
		//System.out.println("result@RoomEnrollServlet="+result2);
		
		
		int result = 0;
		
		//RoomImage
		//최대 파일 10개까지만
		for(int i=0; i<10; i++) {
			if(multi.getOriginalFileName("f"+i)==null) { 
				//3. 처리결과에 따른 view단에 처리위임.
				String view = "/WEB-INF/views/common/msg.jsp";
				String msg = result > 0 ? "게시판 등록 성공!" : "게시판 등록 실패!";
				String loc = "/brokerBoard/boardList";
				//String loc = "/brokerBoard/boardForm?boardNo="+newRoomBoard.getBoard_num();
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher(view).forward(request, response);
				return;
			}
			String oName = multi.getOriginalFileName("f"+i);
			String rName = multi.getFilesystemName("f"+i);
			
			RoomImage newRoomImg = new RoomImage(0, 0, oName, rName);
			//System.out.println("newRoomImg="+newRoomImg);
			
			//업무로직
			result = new BoardService().insertBoardImg(newRoomImg);
			//System.out.println("result@boardImgEnrollServlet="+result);
			
		}
		
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		doGet(request, response);
			
		
	}

}
