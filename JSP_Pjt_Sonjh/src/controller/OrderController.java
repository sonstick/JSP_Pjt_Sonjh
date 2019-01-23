package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.OrderService;
import service.OrderServiceImpl;

@WebServlet("*.order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public OrderController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		order(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		order(request, response);
	}

	protected void order(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String linkPage="";
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.substring(contextPath.length());
		
		// 예약 메인
		if(url.equals("/booking.order") || url.equals("/*.order")) {
			System.out.println("URL : /booking.order");
			
			OrderService service = new OrderServiceImpl();
			service.productInfo(request, response);
			
			linkPage = "/pages/booking.jsp";
		
		// 예약 처리
		} else if(url.equals("/bookingPro.order")) {
			System.out.println("URL : /bookingPro.order");
			
			OrderService service = new OrderServiceImpl();
			service.bookingPro(request, response);
			
			linkPage = "/process/bookingPro.jsp";
		
		// 예약 취소
		} else if(url.equals("/bookingCancel.order")) {
			System.out.println("URL : /bookingCancel.order");
			
			OrderService service = new OrderServiceImpl();
			service.bookingCancelPro(request, response);
			
			linkPage = "/process/bookingCancel.jsp";
			
		// 스크랩 하기
		} else if(url.equals("/scrap.order")) {
			System.out.println("URL : /scrap.order");
			
			OrderService service = new OrderServiceImpl();
			service.scrapPro(request, response);
			
			linkPage = "/process/scrapPro.jsp";
			
		// 스크랩 삭제
		} else if(url.equals("/scrapDel.order")) {
			System.out.println("URL : /scrapDel.order");
			
			OrderService service = new OrderServiceImpl();
			service.scrapDeletePro(request, response);
			
			linkPage = "/process/scrapDeletePro.jsp";
		}
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(linkPage);
		dispatcher.forward(request, response);
	}
}
