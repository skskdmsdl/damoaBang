package admin.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import broker.model.service.BrokerService;
import broker.model.vo.Broker;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/admin/brokerBalcklistUpdate")
public class AdminBrokerBlacklistUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminBrokerBlacklistUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.encoding
		request.setCharacterEncoding("utf-8");
		
		//2.사용자입력값처리
		String br_cp_id = request.getParameter("br_cp_id");
		System.out.println("brokerBalck@servlet"+br_cp_id);
		
		
		Broker updateBr = new Broker(br_cp_id, null, null, null, null, null, null, null, 0, null, 0, null, "T"); 
		
		
		//3.업무로직
		int result = new BrokerService().updateBrBlacklist(updateBr);
		System.out.println("result@servlet="+result);
		
	
	}

}
