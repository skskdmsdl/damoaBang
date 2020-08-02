package board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.service.BoardService;
import board.model.vo.BoardComment;
import board.model.vo.BoardLike;
import board.model.vo.Room;
import board.model.vo.RoomBoard;
import board.model.vo.RoomImage;
import broker.model.service.BrokerService;
import broker.model.vo.Broker;
import member.model.vo.Member;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
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
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
		try {
			
			//1. 사용자 입력값 처리
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
			
			HttpSession session = request.getSession();
			Member memberLoggedIn 
			= (Member)session.getAttribute("memberLoggedIn");
			
			BoardLike resultBl = new BoardService().selectLikeOne(board_num, memberLoggedIn.getMemberId());
			boolean like = resultBl == null ? false : true;
			
			//3. view단 처리
			request.setAttribute("like", like);
			request.setAttribute("roomBoard", roomBoard);
			request.setAttribute("room", room);
			request.setAttribute("broker", broker);
			request.setAttribute("imgList", imgList);
			
			request.getRequestDispatcher("/WEB-INF/views/board/boardForm.jsp").forward(request, response);
		} catch(Exception e) {
			
			e.printStackTrace(); //디버깅용
			
			throw e; //was 웹 컨테이너에게 오류 던지기*** => 우리 프로젝트는 서버에 배포 후 서버를 실행하는것 우리는 오류 발생시 던지기만 하면됨
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String br_cp_id = request.getParameter("br_cp_id");
		int board_num = Integer.parseInt(request.getParameter("board_num"));

		BoardLike bl = new BoardService().selectLikeOne(board_num, memberId);
		boolean exitistLike = bl == null ? false : true;
		if(exitistLike) {
			//like 삭제
			int result = new BoardService().deleteLike(bl);
			System.out.println("insertLike="+result);
		}else {
			//like 추가
			BoardLike like = new BoardLike(memberId, board_num, "T");
			int result = new BoardService().insertLike(like);
			System.out.println("insertLike="+result);
		}
		
		BoardLike resultBl = new BoardService().selectLikeOne(board_num, memberId);
		boolean like = resultBl == null ? false : true;
		//4. 사용자 응답처리 : msg.jsp
		//request.setAttribute("like", like);
		//request.getRequestDispatcher("/WEB-INF/views/board/boardView?board_num="+board_num+"&br="+br_cp_id).forward(request, response);
		
	}

}
