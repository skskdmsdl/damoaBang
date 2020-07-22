package member.controller;

import java.io.IOException;
import java.util.Properties;

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

import common.util.Utils;
import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class MemberFindPwdServlet
 */
@WebServlet("/member/findPwd")
public class MemberFindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFindPwdServlet() {
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
			
				//랜덤 알파벳+숫자 생성
				String newPwd = "";
				for (int i = 1; i <= 4; i++) {
				     char ch = (char) ((Math.random() * 26) + 97);
				     newPwd += ch;
				    }
				newPwd = newPwd+(int)(Math.random()*1000000);
				System.out.println(newPwd);
				
				Member m  = new MemberService().selectOneEmail(email);
				System.out.println(m.getEmail());
				Member updateMember = new Member(null, email, Utils.getEncryptedPassword(newPwd), null, null, null, 0, null,null);
				int result = new MemberService().updatePassword(updateMember);
				System.out.println(result);
				
				if(result>=1) {
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
						msg.setSubject("다모아방 비밀번호 안내 메일입니다");
						
						//메일 내용
						msg.setText("비밀번호를 [" +newPwd+ "]로 초기화 하였습니다.");
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
				}else {
					request.setAttribute("msg", "회원정보가 존재하지 않습니다.");
					request.setAttribute("loc", "/"); 
					
					RequestDispatcher reqDispatcher
					= request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp");
					reqDispatcher.forward(request, response);
				}
	}

}
