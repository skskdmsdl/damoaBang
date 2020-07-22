package broker.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import board.model.service.BoardService;
import board.model.vo.RoomBoard;
import broker.model.service.BrokerService;
import broker.model.vo.Broker;
import common.util.Utils;
import community.model.service.CommunityService;

/**
 * Servlet implementation class MemberProfileServlet
 */
@WebServlet("/broker/brokerProfileView")
public class BrokerProfileViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrokerProfileViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//1. 사용자 입력값
	      int numPerPage = 4;
	      int cPage = 1;
	      try {
	         cPage = Integer.parseInt(request.getParameter("cPage"));         
	      }catch(NumberFormatException e) {
	         
	      }
	      
	      HttpSession session = request.getSession();
	      Broker brokerLoggedIn 
	         = (Broker)session.getAttribute("brokerLoggedIn");
	      //2. 업무로직 : 게시글 목록 조회
	      //1. contents 영역
	      List<RoomBoard> list = BoardService.searchMyBoardList(brokerLoggedIn.getBr_cp_id() ,cPage, numPerPage); 
	      System.out.println("list@servlet="+list);
	      
	      Broker broker = new BrokerService().selectOne(brokerLoggedIn.getBr_cp_id());
	   
	      //2. pageBar 영역
	      int totalContents = BoardService.selectMyBoardCount(brokerLoggedIn.getBr_cp_id());
	      String url = request.getRequestURI() + "?";
	      String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
	      
	      //3. view단 처리 : boardList.jsp
	      request.setAttribute("list", list);
	      request.setAttribute("pageBar", pageBar);
	      request.setAttribute("totalContents", totalContents);
	      request.setAttribute("broker", broker);
	      
	      request.getRequestDispatcher("/WEB-INF/views/broker/brokerProfile.jsp").forward(request, response);

		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
	
	
	}

}
