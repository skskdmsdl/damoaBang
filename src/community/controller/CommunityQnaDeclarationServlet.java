package community.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommunityService;
import community.model.vo.ComBoard;

/**
 * Servlet implementation class CommunityQnaDeclarationServlet
 */
@WebServlet("/community/qnadecl")
public class CommunityQnaDeclarationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityQnaDeclarationServlet() {
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
//		String decl = request.getParameter("chk_info");
		String memberId = request.getParameter("memberId");
		int boardno = Integer.parseInt(request.getParameter("boardno"));
		int result = new CommunityService().IncreaseOutcount(memberId);
		
		String msg = result > 0 ? "신고 성공!" : "신고 실패!";
		String loc ="/community/communityQnA";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);

		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		
	}

}
