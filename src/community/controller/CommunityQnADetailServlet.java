package community.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import community.model.service.CommunityService;
import community.model.vo.ComBoard;
import community.model.vo.ComBoardReply;
import community.model.vo.CommunityLike;
import member.model.vo.Member;

/**
 * Servlet implementation class CommunityViewServlet
 */
@WebServlet("/community/communityQnADetail")
public class CommunityQnADetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityQnADetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int comBoardno = Integer.parseInt(request.getParameter("boardnum"));
			
			List<ComBoardReply> replylist = new CommunityService().selectCommentList(comBoardno);
			ComBoard cb = new CommunityService().selectOne(comBoardno);
			int replycount = new CommunityService().selectreplyCount(comBoardno);
			
			System.out.println(replylist);
			System.out.println(cb);
			
			HttpSession session = request.getSession();
			Member memberLoggedIn = (Member)session.getAttribute("memberLoggedIn");
			if(memberLoggedIn!=null) {
				CommunityLike resultCl = new CommunityService().selectLikeOne(cb.getboardnum(), memberLoggedIn.getMemberId());
				boolean like = resultCl == null ? false : true;
				
				request.setAttribute("like", like);
			}
			request.setAttribute("replylist", replylist);
			request.setAttribute("ComBoard", cb);
			request.setAttribute("replycount", replycount);
			RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/community/communityQnADetail.jsp");
			reqDispatcher.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String memberId = request.getParameter("memberId");
			int board_num = Integer.parseInt(request.getParameter("board_num"));
	
			List<ComBoardReply> replylist = new CommunityService().selectCommentList(board_num);
			CommunityLike cl = new CommunityService().selectLikeOne(board_num, memberId);
			boolean exitistLike = cl == null ? false : true;
			if(exitistLike) {
				//like 삭제
				int result = new CommunityService().deleteLike(cl);
				System.out.println("insertLike="+result);
			}else {
				//like 추가
				CommunityLike like = new CommunityLike(memberId, board_num, "T");
				int result = new CommunityService().insertLike(like);
				System.out.println("insertLike="+result);
			}
			
			CommunityLike resultCl = new CommunityService().selectLikeOne(board_num, memberId);
			boolean like = resultCl == null ? false : true;
		} catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}
	}

}

