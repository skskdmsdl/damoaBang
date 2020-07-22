package community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommunityService;

/**
 * Servlet implementation class CommunityQnADeleteServlet
 */
@WebServlet("/community/comboarddelete")
public class CommunityQnADeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityQnADeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardno = Integer.parseInt(request.getParameter("boardNo"));
		int result = new CommunityService().deleteBoard(boardno);
		
		String msg = result > 0 ? "질문 삭제 성공" : "질문 삭제 실패";
		String loc = "/community/communityQnA";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
	}
//	String msg = result > 0 ? "게시글 수정 성공!" : "게시글 수정 실패!";
//	String loc = "/board/boardView?boardNo=" + boardNo;
//	request.setAttribute("msg", msg);
//	request.setAttribute("loc", loc);
//	request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
//		   .forward(request, response);
}
