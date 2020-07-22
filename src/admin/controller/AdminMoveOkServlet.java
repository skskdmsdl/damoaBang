package admin.controller;

import java.io.IOException;
import java.sql.Date;

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
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet("/admin/moveOk")
public class AdminMoveOkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMoveOkServlet() {
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
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println("moveOk@servlet"+board_num);
		
		
		RoomBoard rb = new RoomBoard(board_num, null, null, null, null, 0, null, null, "T");
		
		//3.업무로직
		int result = new BoardService().updateOk(rb);
		System.out.println("result@servlet="+result);
		
		/**
		HttpSession session = request.getSession();
		Member memberLoggedIn 
			= (Member)session.getAttribute("memberLoggedIn");
		
		//세션에 변경내역 반영
		if(result > 0 && memberId.equals(memberLoggedIn.getMemberId())) {
//			memberLoggedIn.setPassword(password);
			memberLoggedIn.setMemberName(memberName);
			memberLoggedIn.setGender(gender);
			memberLoggedIn.setBirthDay(birthDay_);
			memberLoggedIn.setEmail(email);
			memberLoggedIn.setPhone(phone);
			memberLoggedIn.setAddress(address);
			memberLoggedIn.setHobby(hobby_);
		}
		**/
		
		//4. 사용자응답처리
		/*
		 * request.getRequestDispatcher("/WEB-INF/views/admin/brokerBlacklist.jsp")
		 * .forward(request, response);
		 */
		/**
		//4.응답 view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = result > 0 ? "회원정보 수정 성공!" : "회원정보 수정 실패!";
		String loc = "/member/memberView?memberId="+memberId;
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view)
			   .forward(request, response);
		
		
		**/
	
	
	
	
	}

}
