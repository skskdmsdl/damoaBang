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

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberFindPwdServlet
 */
@WebServlet("/board/sendMail")
public class BoardSendMailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSendMailServlet() {
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
		//1. 인코딩시작
		request.setCharacterEncoding("utf-8");
		
		//2. 사용자 입력값 처리
		String email = request.getParameter("email");

		Member m  = new MemberService().selectOneEmail(email);
		if(m==null) {
			//request.setAttribute("msg", "이메일 정보가 맞지 않습니다");
         //   request.setAttribute("loc", "/board/boardView");
            request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
            return;
		}
		
		StringBuffer temp =new StringBuffer();
        Random rnd = new Random();
        for(int i=0;i<10;i++)
        {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
            case 0:
                // a-z
                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                break;
            case 1:
                // A-Z
                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                break;
            case 2:
                // 0-9
                temp.append((rnd.nextInt(10)));
                break;
            }
        }
        String AuthenticationKey = temp.toString();
        System.out.println(AuthenticationKey);

        String user = "joeune92@naver.com";
		String pwd = "wlstlfdmlqkd";
		
		Properties prop = new Properties();
		
		prop.put("mail.smtp.host", "smtp.naver.com");
		prop.put("mail.smtp.port", 587);
		prop.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, pwd);
			}
		});
		
		
		
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(user));
			//수신자 메일주소
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m.getEmail()));
			
			//메일 제목
			msg.setSubject("다모아방 허위매물신고 인증 메일입니다");
			
			//메일 내용
			msg.setText("인증 번호는 [" +temp+ "]입니다.");
			//msg.setText("변경 후 사용해 주세요!");
			
			Transport.send(msg); /// 전송
			System.out.println("message sent 성공");
			
			request.setAttribute("msg", "메일을 발송했습니다.");
			request.setAttribute("loc", "/"); 
			
			RequestDispatcher reqDispatcher
			= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
			reqDispatcher.forward(request, response);
		}catch(AddressException e) {
			e.printStackTrace();
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		HttpSession saveKey = request.getSession();
        saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
        
        saveKey.setMaxInactiveInterval(30);

		
	}

}
