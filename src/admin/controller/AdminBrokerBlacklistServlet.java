package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import broker.model.service.BrokerService;
import broker.model.vo.Broker;
import common.util.Utils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberListServlet
 */
@WebServlet("/admin/brokerBlacklist")
public class AdminBrokerBlacklistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBrokerBlacklistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//0. 사용자입력값
		int numPerPage = 10;
		int cPage = 1;
		
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch(NumberFormatException e) {
			//예외가 던져진 경우, cPage = 1로 유지
		}
		
		//1. 업무로직 : 회원목록
		//컨텐츠영역에 대한 쿼리 요청
		List<Broker> list = new BrokerService().selectBlackAll(cPage, numPerPage);
//				System.out.println("list@servlet = " + list);
		
		//페이지바 영역 html
		String url = request.getRequestURI() + "?";// /mvc/admin/memberList?
		int totalContents = new BrokerService().selectTotalBlackContents();
		String pageBar 
			= Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
		
		
		//2. view단 위임
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		
		request.getRequestDispatcher("/WEB-INF/views/admin/brokerBlacklist.jsp")
			   .forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
