package brokerBoard.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.exception.BoardException;
import board.model.service.BoardService;
import board.model.vo.Room;
import board.model.vo.RoomBoard;
import board.model.vo.RoomImage;
import common.WebFileRenamePolicy;
import member.model.exception.memberException;

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/brokerBoard/boardUpdate")
public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.parameterHandling
		int board_num;
		try{
			board_num = Integer.parseInt(request.getParameter("board_num"));
		}catch(NumberFormatException e){
			throw new BoardException("유효하지 않은 게시글입니다.");
		}
		//2.businessLogic
		RoomBoard rb = new BoardService().selectOne(board_num);
		Room r = new BoardService().selectRoomOne(board_num);
		List<RoomImage> imgList = new BoardService().selectRoomImgList(board_num);
		
		
		
		//3.view
		request.setAttribute("rb", rb);
		request.setAttribute("r", r);
		request.setAttribute("imgList", imgList);
		request.getRequestDispatcher("/WEB-INF/views/brokerBoard/updateBoard.jsp").forward(request, response);
		
		
	}

	/**
	 *  기존첨부유무 
	 *  1. 기존첨부가 없는 경우
	 *  2. 기존첨부가 있는 경우
	 *  	- 기존 첨부파일 유지
	 *  	- 새첨부파일 추가 : 기존파일 삭제
	 *  	- 기존 첨부파일 삭제(삭제체크) : 기존파일 삭제
	 *  
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
		
		int board_num = Integer.parseInt(multi.getParameter("board_num"));
		String board_title = multi.getParameter("board_title");
		String content = multi.getParameter("boardContent");
		
		//RoomBoard객체로 만들기
		RoomBoard newRoomBoard = new RoomBoard(board_num, null, board_title, content, null, 0, null, null, "F");
		//System.out.println("newRoomBoard="+newRoomBoard);
		
		//업무로직
		int result1 = new BoardService().updateBoard(newRoomBoard);
		//System.out.println("result@boardEnrollServlet="+result1);
		
		
		//1. 사용자입력값 처리(room)
		
		//3. 처리결과에 따른 view단에 처리위임.
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = result1 > 0 ? "게시판 등록 성공!" : "게시판 등록 실패!";
		String loc = "/brokerBoard/boardList";
		//String loc = "/brokerBoard/boardForm?boardNo="+newRoomBoard.getBoard_num();
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
			
			
	}

}
