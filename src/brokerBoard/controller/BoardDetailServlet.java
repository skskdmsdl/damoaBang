package brokerBoard.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.BoardComment;
import board.model.vo.Room;
import board.model.vo.RoomBoard;
import board.model.vo.RoomImage;
import broker.model.service.BrokerService;
import broker.model.vo.Broker;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/brokerBoard/boardView")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			//1. 사용자 입력값 처리
			int board_num = Integer.parseInt(request.getParameter("board_num"));
			String br_cp_id = request.getParameter("br");
			
			//2. 업무로직 
			//쿠키검사 : boardCookie
			Cookie[] cookies = request.getCookies();
			String boardCookieVal = "";
			boolean hasRead = false;
			
			if(cookies != null) {
				for(Cookie c : cookies) {
					String name = c.getName();
					String value = c.getValue();
					
					if("boardCookie".equals(name)) {
						boardCookieVal = value;
						
						if(value.contains("[" + board_num + "]"))
							hasRead = true;
					}
				}
			}
			
			if(!hasRead) {			
				//boardCookie생성
				Cookie boardCookie = new Cookie("boardCookie", boardCookieVal + "[" + board_num + "]");
				boardCookie.setPath(request.getContextPath()+"/board");
				boardCookie.setMaxAge(365*24*60*60);
				response.addCookie(boardCookie);
			}
			
			//방찾기 게시판 조회
			RoomBoard roomBoard = new BoardService().selectRoomBoardOne(board_num, hasRead);
			//System.out.println("board@servlet = " + roomBoard);
			
			//개행문자처리
			String contentWithBr = roomBoard.getContent()
					.replaceAll("\\n", "<br/>");
			roomBoard.setContent(contentWithBr);
			
			//방 정보 조회
			Room room = new BoardService().selectRoomOne(board_num);
			//System.out.println("room@servlet = " + room);
			
			//중개인 정보 조회
			Broker broker = new BrokerService().selectOne(br_cp_id);
			//System.out.println("room@servlet = " + room);
			
			//이미지 조회
			
			List<RoomImage> imgList = new BoardService().selectRoomImgList(board_num);
			
			//3. view단 처리
			request.setAttribute("roomBoard", roomBoard);
			request.setAttribute("room", room);
			request.setAttribute("broker", broker);
			request.setAttribute("imgList", imgList);
			
			request.getRequestDispatcher("/WEB-INF/views/brokerBoard/boardForm.jsp")
			.forward(request, response);
		} catch(Exception e) {
			
			e.printStackTrace(); //디버깅용
			
			throw e; //was 웹 컨테이너에게 오류 던지기*** => 우리 프로젝트는 서버에 배포 후 서버를 실행하는것 우리는 오류 발생시 던지기만 하면됨
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
