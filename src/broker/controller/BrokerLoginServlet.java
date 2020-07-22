package broker.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import broker.model.service.BrokerService;
import broker.model.vo.Broker;
import common.JDBCTemplate;
import common.util.Utils;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/broker/login")
public class BrokerLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrokerLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/common/brokerHeader.jsp");
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//2. 사용자입력값 처리
		String br_cp_id = request.getParameter("br_cp_id");
		String password = Utils.getEncryptedPassword(request.getParameter("password"));
//		System.out.println("memberId@servlet="+memberId);
//		System.out.println("password@servlet="+password);
//		System.out.println("saveId@servlet="+saveId);
		
//		System.out.println(JDBCTemplate.getConnection());
		//3. 업무로직
		Broker b = new BrokerService().selectOne(br_cp_id);
		
		//4. 응답처리
		//아이디, 비번이 모두 일치하는 경우 
		if(b != null 
		&& br_cp_id.equals(b.getBr_cp_id())
		&& password.equals(b.getPassword())
		&& !(b.getBlack().equals("T"))) {
			
//			//로그인한 사용자 정보 저장
//			request.setAttribute("memberLoggedIn", m);
//			
//			RequestDispatcher reqDispatcher
//				= request.getRequestDispatcher("/index.jsp");
//			reqDispatcher.forward(request, response);
			
			//세션가져오기 : create 여부 true 
			//세션객체가 없다면, 새로 생성후 리턴
			//세션객체가 있다면, 가져오기
			HttpSession session = request.getSession(true);
			//System.out.println("sessionId="+session.getId());
			
			//세션유효시간설정 : 초
			//web.xml 선언한 session-config > session-timeout 보다 우선순위가 높다.
			session.setMaxInactiveInterval(30*60);
			
			
			//세션에 로그인한 사용자 정보 저장
			session.setAttribute("brokerLoggedIn", b);
			
			//리다이렉트 처리
			//로그인 요청페이지로 이동
			String referer = request.getHeader("referer");
			response.sendRedirect(referer);
			
		}
		//아이디 또는 비번이 틀린경우
		else if(b.getBlack().equals("T")) {
			request.setAttribute("msg", "이용이 정지된 회원입니다.");
			request.setAttribute("loc", "/"); //사용자 피드백 이후 이동할 페이지
			
			RequestDispatcher reqDispatcher
			= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			reqDispatcher.forward(request, response);
		}
		else {
			request.setAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
			request.setAttribute("loc", "/"); //사용자 피드백 이후 이동할 페이지
			
			RequestDispatcher reqDispatcher
			= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			reqDispatcher.forward(request, response);
			
		}
		
		
		
	}

}
