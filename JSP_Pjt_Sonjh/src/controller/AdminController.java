package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardServiceImpl;
import service.MemberServiceImpl;
import service.OrderServiceImpl;
import service.ProductService;
import service.ProductServiceImpl;

@WebServlet("*.admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public AdminController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admin(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		admin(request, response);
	}

	protected void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String linkPage="";
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.substring(contextPath.length());
		
		// 관리자 메인
		if(url.equals("/main.admin") || url.equals("/*.admin")) {
			System.out.println("URL : /main.admin");

			// 회원정보 로딩
			MemberServiceImpl member = new MemberServiceImpl();
			member.loadingMember(request, response, 5);

			// 공지사항 로딩
			BoardServiceImpl notice = new BoardServiceImpl();
			notice.loadingNotice(request, response, 5);
			
			// 문의사항 로딩
			BoardServiceImpl invoice = new BoardServiceImpl();
			invoice.loadingInvoice(request, response, 5);
			
			// 예약현황
			OrderServiceImpl booking = new OrderServiceImpl();
			booking.loadingBooking(request, response, 5);
			
			// 상품 로딩 - 여기선 안쓰고 아래에서 쓰자~
			ProductService product = new ProductServiceImpl();
			product.lodingProduct(request, response, 5);
			
			linkPage = "/admin/admin_main.jsp";
			
		// 예약관리
		} else if(url.equals("/booking.admin")) {
			System.out.println("URL : /booking.admin");
			
			OrderServiceImpl booking = new OrderServiceImpl();
			booking.loadingBooking(request, response, 10);
			
			linkPage = "/admin/admin_booking.jsp";
			
		// 회원관리
		} else if(url.equals("/member.admin")) {
			System.out.println("URL : /member.admin");
			
			MemberServiceImpl member = new MemberServiceImpl();
			member.loadingMember(request, response, 10);
			
			linkPage = "/admin/admin_member.jsp";
			
		// 게시판관리
		} else if(url.equals("/boards.admin")) {
			System.out.println("URL : /boards.admin");
			
			ProductService product = new ProductServiceImpl();
			product.lodingProduct(request, response, 5);
			
			BoardServiceImpl notice = new BoardServiceImpl();
			notice.loadingNotice(request, response, 5);
			
			BoardServiceImpl invoice = new BoardServiceImpl();
			invoice.loadingInvoice(request, response, 5);
			
			linkPage = "/admin/admin_boards.jsp";
		
		// 여행상품
		} else if(url.equals("/product.admin")) {
			System.out.println("URL : /product.admin");
			
			ProductService product = new ProductServiceImpl();
			product.lodingProduct(request, response, 10);
			
			linkPage = "/admin/admin_product.jsp";
			
		// 공지사항
		} else if(url.equals("/notice.admin")) {
			System.out.println("URL : /notice.admin");
			
			BoardServiceImpl notice = new BoardServiceImpl();
			notice.loadingNotice(request, response, 10);
			
			linkPage = "/admin/admin_notice.jsp";
			
		// 문의사항
		} else if(url.equals("/invoice.admin")) {
			System.out.println("URL : /invoice.admin");
			
			BoardServiceImpl invoice = new BoardServiceImpl();
			invoice.loadingInvoice(request, response, 10);
			
			linkPage = "/admin/admin_invoice.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(linkPage);
		dispatcher.forward(request, response);
	}
}























