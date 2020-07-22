package member.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

import board.model.service.BoardService;
import board.model.vo.BoardLike;
import board.model.vo.RoomBoard;
import broker.model.vo.Broker;
import common.util.Utils;
import community.model.service.CommunityService;
import community.model.vo.ComBoard;
import member.model.exception.memberException;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberProfileServlet
 */
@WebServlet("/member/memberProfileView")
public class MemberProfileViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberProfileViewServlet() {
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
	      Member memberLoggedIn 
	         = (Member)session.getAttribute("memberLoggedIn");
	      System.out.println(memberLoggedIn.getMemberId());
	      //2. 업무로직 : 게시글 목록 조회
	      //1. contents 영역
	      List<RoomBoard> likeList = BoardService.selectLikeList(memberLoggedIn.getMemberId(), cPage, numPerPage); 
	      System.out.println("like@servlet="+likeList);
	      
	      
	      List<ComBoard> comList = new CommunityService().selectMemberList(memberLoggedIn.getMemberId());
	      int cnt = new CommunityService().qnaCnt(memberLoggedIn.getMemberId());
	      
	      
	      //2. pageBar 영역
	      int totalContents = BoardService.selectMyLikeCount(memberLoggedIn.getMemberId());
	      String url = request.getRequestURI() + "?";
	      String pageBar = Utils.getPageBarHtml(cPage, numPerPage, totalContents, url);
	      
	      System.out.println("@@@@@@@@@cnt"+comList);
		//3. view단 처리 : boardList.jsp
		
		request.setAttribute("cnt", cnt);
		request.setAttribute("comList", comList);
		request.setAttribute("likeList", likeList);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("totalContents", totalContents);
		
		request.getRequestDispatcher("/WEB-INF/views/member/memberProfile.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
