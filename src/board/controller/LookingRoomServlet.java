package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.RoomBoard;
import common.util.Utils;

/**
 * Servlet implementation class LookingRoomServlet
 */
@WebServlet("/board/boardList")
public class LookingRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BoardService boardService = new BoardService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LookingRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			//1. 사용자 입력값
			int numPerPage = 3;
			int cPage = 1;
			try {
				cPage = Integer.parseInt(request.getParameter("cPage"));			
			}catch(NumberFormatException e) {
				
			}
			
			//2. 업무로직 : 게시글 목록 조회
			//a. contents 영역
			List<RoomBoard> list = boardService.selectBoardList(cPage, numPerPage); 
			//System.out.println("list@servlet="+list);
			
			//b. pageBar 영역
			int totalContents = boardService.selectBoardCount();
			String url = request.getRequestURI() + "?";
			String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
			
			//3. view단 처리 : boardList.jsp
			
			request.setAttribute("list", list);
			request.setAttribute("pageBar", pageBar);
			request.setAttribute("totalContents", totalContents);
			
			request.getRequestDispatcher("/WEB-INF/views/board/lookingRoom.jsp").forward(request, response);
			} catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
