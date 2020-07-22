package broker.controller;

import java.io.IOException;

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
 * Servlet implementation class UpdatePasswordServlet
 */
@WebServlet("/broker/updatePassword")
public class UpdatePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/broker/brokerProfile.jsp")
			   .forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.사용자입력값 처리
		String br_cp_id = request.getParameter("br_cp_id");
		String password = Utils.getEncryptedPassword(request.getParameter("password"));
		String newPassword = Utils.getEncryptedPassword(request.getParameter("newPassword"));
		
		
		//2.업무로직
		//기존 비밀번호 검사
		Broker b = new BrokerService().selectOne(br_cp_id);
		String msg = "";
		String loc = "";
		if(b != null && password.equals(b.getPassword())) {
			//새비밀번호로 변경
			int result = new BrokerService().updatePassword(br_cp_id, newPassword);
			msg = result > 0 ? "비밀번호 변경 성공!" : "비밀번호 변경 실패!";
		}
		else {
			//비밀번호 재입력
			msg = "비밀번호를 잘못 입력하셨습니다.";
			loc = "/broker/updatePassword?br_cp_id="+br_cp_id;
		}
		
		//3.응답  html 처리
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);
		
		
		
		
	}

}






