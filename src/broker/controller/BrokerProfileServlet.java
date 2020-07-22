package broker.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;
import member.model.exception.memberException;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberProfileServlet
 */
@WebServlet("/broker/brokerProfile")
public class BrokerProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrokerProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new memberException("enctype오류!");
		}
		int fileMaxSize = 10 * 1024 * 1024;
		FileRenamePolicy policy = new DefaultFileRenamePolicy();
		String saveDirectory = getServletContext().getRealPath("/") + "/upload/broker";
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, fileMaxSize, "utf-8", policy);
		
		
		
		String memberId = multi.getParameter("memberId");
		String profile = multi.getOriginalFileName("profile");
		
		
		Member m = new Member(memberId, null, null, null, null, null, 0, profile,null);
		int result = new MemberService().updateMemberProfile(m);
		
		HttpSession session = request.getSession();
		Member memberLoggedIn 
			= (Member)session.getAttribute("memberLoggedIn");
		//세션에 변경내역 반영
		if(result > 0 && memberId.equals(memberLoggedIn.getMemberId())) {
//					memberLoggedIn.setPassword(password);
			memberLoggedIn.setProfile(profile);
		} 
		 
		System.out.println("memberProfile@servlet"+m);	
		
		
		
		//4. view단
		String msg = result > 0 ? "프로필 수정 성공!" : "프로필 수정 실패!";
		String loc = "/member/memberProfileView";
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);
	}

}
