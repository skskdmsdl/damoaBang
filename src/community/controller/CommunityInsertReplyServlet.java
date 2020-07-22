package community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommunityService;
import community.model.vo.ComBoardReply;

/**
 * Servlet implementation class CommunityInsertReplyServlet
 */
@WebServlet("/community/insertreply")
public class CommunityInsertReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityInsertReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardnum = Integer.parseInt(request.getParameter("boardno"));
		String Writer = request.getParameter("writer");
		String content = request.getParameter("replycontent");
		int level = Integer.parseInt(request.getParameter("level"));
		
		System.out.println("writer==============="+Writer);
		System.out.println("boardno=========="+boardnum);
		System.out.println("content=========="+content);
		System.out.println("level=========="+level);
		
		ComBoardReply cbr = new ComBoardReply(0, boardnum, Writer, 0, content, 0, level, null);
		int result = new CommunityService().insertComReply(cbr);
		
		String msg = result > 0 ? "댓글 등록 성공!" : "댓글 등록 실패!";
		String loc ="/community/communityQnADetail?boardnum=" + boardnum;
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
	}

}
