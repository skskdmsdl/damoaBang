package community.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import board.model.exception.BoardException;
import common.WebFileRenamePolicy;
import community.model.service.CommunityService;
import community.model.vo.ComBoard;

/**
 * Servlet implementation class CommunityInsertServlet
 */
@WebServlet("/community/communityInsert")
public class CommunityInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일업로드 enctype="mulpart/form-data" 속성 사용여부
		if(!ServletFileUpload.isMultipartContent(request)) {
			throw new BoardException("파일업로드 enctype 속성 미적용! : " + request.getRequestURI());			
		}
		
	
		//MultipartRequest객체 생성
		//a.HttpServletRequest
		//b.saveDirectory : 업로드할 파일 저장경로(절대경로)
		//c.maxPostSize : 업로드할 파일 크기 제한 10mb
		//d.encoding 
		//e.FileRenamePolicy객체 : 중복파일 이름 재지정 객체
		
		String saveDirectory
//		= getServletContext().getRealPath("/") + File.separator+"upload"+File.separator+"board";
		= getServletContext().getRealPath("/") + "/upload/community";
		int maxPostSize = 1024 * 1024 * 10; //10MB
		FileRenamePolicy policy = new WebFileRenamePolicy();
		
		MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", policy);
		
		//MultipartRequest객체를 사용하면,
		//기존의 HttpServletRequest에서는 사용자 입력값을 가져올 수 없다.
		String ctitle = multipartRequest.getParameter("ctitle");
		String questionWriter = multipartRequest.getParameter("questionWriter");
		String questionContent = multipartRequest.getParameter("questionContent");
		String questionOriginalFileName = multipartRequest.getOriginalFileName("upfile");
		String questionRenameFileName = multipartRequest.getFilesystemName("upfile");
		
		ComBoard cb = new ComBoard(0, questionWriter, ctitle, questionContent, null, 0, questionOriginalFileName, questionRenameFileName);
		System.out.println("comBoard@servlet = "+cb);
		
		int result = new CommunityService().insertBoard(cb);
		
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = result > 0 ? "질문 저장 성공!" : "질문 저장 실패!";
		String loc = "/community/communityQnA";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파일업로드 enctype="mulpart/form-data" 속성 사용여부
				if(!ServletFileUpload.isMultipartContent(request)) {
					throw new BoardException("파일업로드 enctype 속성 미적용! : " + request.getRequestURI());			
				}
				
				//MultipartRequest객체 생성
				//a.HttpServletRequest
				//b.saveDirectory : 업로드할 파일 저장경로(절대경로)
				//c.maxPostSize : 업로드할 파일 크기 제한 10mb
				//d.encoding 
				//e.FileRenamePolicy객체 : 중복파일 이름 재지정 객체
				
				String saveDirectory
//				= getServletContext().getRealPath("/") + File.separator+"upload"+File.separator+"board";
				= getServletContext().getRealPath("/") + "/upload/community";
				int maxPostSize = 1024 * 1024 * 10; //10MB
				FileRenamePolicy policy = new WebFileRenamePolicy();
				
				MultipartRequest multipartRequest = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", policy);
				
				//MultipartRequest객체를 사용하면,
				//기존의 HttpServletRequest에서는 사용자 입력값을 가져올 수 없다.
				String ctitle = multipartRequest.getParameter("ctitle");
				String questionWriter = multipartRequest.getParameter("questionWriter");
				String questionContent = multipartRequest.getParameter("questionContent");
				String questionOriginalFileName = multipartRequest.getOriginalFileName("upfile");
				String questionRenameFileName = multipartRequest.getFilesystemName("upfile");
				
				ComBoard cb = new ComBoard(0, questionWriter, ctitle, questionContent, null, 0, questionOriginalFileName, questionRenameFileName);
				System.out.println("comBoard@servlet = "+cb);
				
				int result = new CommunityService().insertBoard(cb);
				
				String view = "/WEB-INF/views/common/msg.jsp";
				String msg = result > 0 ? "질문 저장 성공!" : "질문 저장 실패!";
				String loc = "/community/communityQnA";
				
				request.setAttribute("msg", msg);
				request.setAttribute("loc", loc);
				request.getRequestDispatcher(view).forward(request, response);
	
	}

}
