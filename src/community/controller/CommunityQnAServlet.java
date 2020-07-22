package community.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import community.model.service.CommunityService;
import community.model.vo.ComBoard;

/**
 * Servlet implementation class CommunityViewServlet
 */
@WebServlet("/community/communityQnA")
public class CommunityQnAServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityQnAServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int[] replycount = new int[300];
		int i = 0;
		List<ComBoard> comlist = new CommunityService().selectAll();
		for(ComBoard cb : comlist) {
			int result = new CommunityService().selectreplyCount(cb.getboardnum());
			replycount[i++] = result;
		}
			
		System.out.println(comlist);
		System.out.println(replycount);
		
		request.setAttribute("comlist", comlist);
		request.setAttribute("replycount", replycount);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/community/communityQnA.jsp");
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}