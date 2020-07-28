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
@WebServlet("/board/searchRoom")
public class SearchRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BoardService boardService = new BoardService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자 입력값
		String room_val = request.getParameter("room_val");
		String tax_val = request.getParameter("tax_val");
		int price = Integer.parseInt(request.getParameter("price"));
		int fee = Integer.parseInt(request.getParameter("fee"));
		
		int numPerPage = 3;
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));			
		}catch(NumberFormatException e) {
			
		}
		
		//2. 업무로직 : 게시글 목록 조회
		//a. contents 영역
		List<RoomBoard> list = boardService.searchBoardList(room_val, tax_val, price, fee, cPage, numPerPage); 
		System.out.println("list@servlet="+list);
		
		//b. pageBar 영역
		int totalContents = boardService.selectTotalContents(room_val, tax_val, price, fee);
		String url = request.getRequestURI()
					+ "?room_val=" + room_val 
					+ "&tax_val=" + tax_val 
					+ "&price=" + price 
					+ "&fee=" + fee 
					+ "&";
		String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
		
		//3. view단 처리 : boardList.jsp
		
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalContents", totalContents);
		
		request.getRequestDispatcher("/WEB-INF/views/board/lookingRoom.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
