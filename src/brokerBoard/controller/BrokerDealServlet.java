package brokerBoard.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import broker.model.service.BrokerService;
import broker.model.vo.Broker;

/**
 * Servlet implementation class BoardDeleteServlet
 */
@WebServlet("/brokerBoard/brokerDeal")
public class BrokerDealServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrokerDealServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1. 사용자 입력값 처리
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String br_cp_id = request.getParameter("br_cp_id");//1234_34.txt 혹은 ""
		
		//2. 업무로직
		//브로커의 실적 조회후 실적 +1 해주면 됨
		Broker br = new BrokerService().selectOne(br_cp_id);
		
		Broker deal = new Broker(br_cp_id, null, null, null, null, null, null, null, br.getSellcount()+1, null, 0, null, null);
		int dealResult = new BrokerService().updateDeal(deal);
		System.out.println("@@@@@@@@@@@@@@@@@@@@ deal"+dealResult);
		
		//DB 레코드 삭제
		int result = new BoardService().deleteBoard(board_num);
		
		//3. view단 처리
		String msg = result > 0 ? "거래 완료 되었습니다!" : "다시 시도해 주세요!";
		String loc = "/brokerBoard/boardList";
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			   .forward(request, response);
		
	}

}
