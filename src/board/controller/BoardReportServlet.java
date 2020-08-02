package board.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
 * Servlet implementation class MemberFindPwdServlet
 */
@WebServlet("/board/report")
public class BoardReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReportServlet() {
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
		String AuthenticationKey = (String)request.getSession().getAttribute("AuthenticationKey");
		String AuthenticationUser = request.getParameter("cerNumber");
		String br_cp_id = request.getParameter("br_cp_id");
		String board_num = request.getParameter("board_num");
		
		if(!AuthenticationKey.equals(AuthenticationUser))
		{
			System.out.println("인증번호 일치하지 않음");
			request.setAttribute("msg", "인증번호가 일치하지 않습니다.");
			request.setAttribute("loc", "/board/boardView?board_num="+board_num+"&br="+br_cp_id); 
			
			RequestDispatcher reqDispatcher
			= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			reqDispatcher.forward(request, response);
			
			
		}else {
		Broker br = new BrokerService().selectOne(br_cp_id);
		
		Broker reportBr = new Broker(br_cp_id, null, null, null, null, null, null, null, 0, null, br.getOutcount()+1, null, null);
		int result = new BrokerService().updateReport(reportBr);
		request.setAttribute("msg", "신고완료!");
		request.setAttribute("loc", "/board/boardView?board_num="+board_num+"&br="+br_cp_id); 
		
		RequestDispatcher reqDispatcher
		= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
		reqDispatcher.forward(request, response);
		}
		
	}
}
