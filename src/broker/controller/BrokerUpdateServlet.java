package broker.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
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
@WebServlet("/broker/brokerUpdate")
public class BrokerUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrokerUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1. 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//2. 사용자입력값 처리
		String br_cp_id = request.getParameter("br_cp_id");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		Broker updateBroker = new Broker(br_cp_id, email, null, null, null, phone, null, null, 0, null, 0, null, null);
//				System.out.println("updateMember@servlet-"+updateMember);
		
		//3. 업무로직(dml)
		int result = new BrokerService().updateMember(updateBroker);
//				System.out.println("result@servlet="+result);
		
		//세션에 변경 내역 반영
		HttpSession session = request.getSession();
		Broker brokerLoggedIn 
			= (Broker)session.getAttribute("brokerLoggedIn");
		
		if(result > 0 && br_cp_id.equals(brokerLoggedIn.getBr_cp_id())) {
			brokerLoggedIn.setEmail(email);
		}
		
		//4. 응답 view단 처리
		//jsp 주소(view)
		String view = "/WEB-INF/views/common/msg.jsp";
		//전달 메시지
		String msg = result > 0 ? "회원정보 수정 성공!" : "회원정보 수정 실패!";
		//수정을 마치고 이동할 페이지
		String loc = "/broker/brokerProfileView";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
			
	
	
	
	
	}

}
