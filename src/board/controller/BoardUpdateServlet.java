package board.controller;

import java.io.File;
import java.io.IOException;
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

/**
 * Servlet implementation class BoardUpdateServlet
 */
@WebServlet("/board/boardUpdate")
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
		//0. enctype=multipart/form-data 여부 확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new BoardException("enctype오류!");
		}
		
		//1. MultipartRequest(cos.jar) 객체
		String saveDirectory = getServletContext().getRealPath("/upload/board");
		int maxPostSize = 1024 *  1024 * 10;
		String encoding = "utf-8";
		FileRenamePolicy policy = new WebFileRenamePolicy();
		MultipartRequest multipartRequest
			= new MultipartRequest(request, saveDirectory, maxPostSize, encoding, policy);
		
		//2. 사용자입력값, 저장된 파일명, 업로드한 파일명 가져오기
		int boardNo = Integer.parseInt(multipartRequest.getParameter("boardNo"));
		String boardTitle = multipartRequest.getParameter("boardTitle");
		String boardWriter = multipartRequest.getParameter("boardWriter");
		String boardContent = multipartRequest.getParameter("boardContent");
		String boardOriginalFileName = multipartRequest.getOriginalFileName("upFile");
		String boardRenamedFileName = multipartRequest.getFilesystemName("upFile");
		String oldOriginalFileName = multipartRequest.getParameter("oldOriginalFileName");
		String oldRenamedFileName = multipartRequest.getParameter("oldRenamedFileName");
		String delFile = multipartRequest.getParameter("delFile");
			
	}

}
