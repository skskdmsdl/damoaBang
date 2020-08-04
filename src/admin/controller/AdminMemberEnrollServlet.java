package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberEnrollServlet
 */
@WebServlet("/admin/memberEnroll")
public class AdminMemberEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminMemberEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//memberEnroll.jsp view단 forwarding 처리
		
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("/WEB-INF/views/admin/memberFinder.jsp");
		reqDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사용자 회원가입정보를 db에 insert
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. 사용자입력값 처리
		String memberId = request.getParameter("memberId");
		String email = request.getParameter("email");
		String memberRole = request.getParameter("memberRole");
		String phone = request.getParameter("phone");
		String black = request.getParameter("black");
		
		//Member객체로 만들기
		Member newMember = new Member(memberId, email, "1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==", memberRole, phone, null, 0, null, "F");
		
		
		//3. 업무로직: db에 insert (DML -> int =>1,0)
		int result = new MemberService().insertMember(newMember);
		
		//4. 사용자 응답처리 : msg.jsp
		String msg = result > 0 ? "회원 등록 성공!" : "회원 등록 실패!";
		String loc = "/admin/memberList";  //회원 가입 성공 후 인덱스 페이지로 가도록 설정
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
		
	}

}
