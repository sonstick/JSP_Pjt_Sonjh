package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberServiceImpl;
import service.OrderService;
import service.OrderServiceImpl;
import service.ProductService;
import service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;

@WebServlet("*.go")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MainController() {
		super();
	}

	// 1단계. HTTP 요청 받음 (모든 요청을 go로 보냄)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		go(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		go(request, response);
	}

	public void go(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		request.setCharacterEncoding("UTF-8");

		String linkPage="";
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.substring(contextPath.length());

		// 2단계. 요청 분석
		// 메인페이지(첫페이지)
		if(url.equals("/main.go") || url.equals("/*.go")) {
			System.out.println("/main.go");

			ProductService service = new ProductServiceImpl();
			service.mainProductList(request, response);
			/*service.productList(request, response);*/
			
			request.setAttribute("selectCnt", 3);
			linkPage = "/pages/main.jsp";
			
		// 로그인 
		} else if(url.equals("/logInPro.go")) {
			System.out.println("/logInPro.go");
			
			MemberServiceImpl service = new MemberServiceImpl();
			service.logInPro(request, response);
			
			linkPage = "/process/loginPro.jsp";
		
		// 회원가입
		} else if(url.equals("/signup.go")) {
			System.out.println("/signup.go");
			
			linkPage = "pages/signup.jsp";
			
		// 회원가입 - ID 중복 확인
		} else if(url.equals("/confirmId.go")) {
			System.out.println("/confirmId.go");
			
			MemberServiceImpl service = new MemberServiceImpl();
			service.confirmId(request, response);
			
			linkPage = "pages/confirmid.jsp";
			
		// 회원가입 - 프로세스
		} else if(url.equals("/signupPro.go")) {
			System.out.println("URL : /signupPro.go");
			
			MemberServiceImpl service = new MemberServiceImpl();
			service.signupPro(request, response);
			
			linkPage = "/process/signupPro.jsp";
			
		// 회원가입 - 성공
		} else if(url.equals("/mainSuccess.go")) {
			System.out.println("URL : /mainSuccess.go");
			int insertCnt = Integer.parseInt(request.getParameter("insertCnt"));
			
			MemberServiceImpl service = new MemberServiceImpl();
			service.logInPro(request, response);
			
			request.setAttribute("insertCnt", insertCnt);
			request.setAttribute("selectCnt", 2);
			
			linkPage = "/pages/main.jsp";
			
		// 로그아웃
		} else if(url.equals("/logout.go")) {
			System.out.println("URL : /logout.go");

			request.getSession().removeAttribute("sessionId");
			request.getSession().removeAttribute("sessionPwd");
			request.getSession().removeAttribute("sessionName");
			request.getSession().removeAttribute("sessionAuth");
			request.setAttribute("selectCnt", 5);
			
			linkPage = "/main.go";
			
		// 회원정보 페이지(My page)
		} else if(url.equals("/mypage.go")) {
			System.out.println("URL : /mypage.go");
			
			MemberServiceImpl service = new MemberServiceImpl();
			service.loadingVO(request, response);
			
			linkPage = "/pages/mypage.jsp";
			
		// 회원정보 - 비밀번호 확인
		} else if(url.equals("/modify_pwdcheck.go")){
			System.out.println("URL: /modify_pwdcheck.go");
			
			MemberServiceImpl service = new MemberServiceImpl();
			service.loadingVO(request, response);
			
			linkPage = "/pages/modify_pwdcheck.jsp";
			
		// 회원정보 - 정보수정
		} else if(url.equals("/modify.go")) {
			System.out.println("URL: /modify.go");
			
			MemberServiceImpl service = new MemberServiceImpl();
			service.pwdCheck(request, response);
			
			linkPage = "/pages/modify.jsp";
			
		// 회원정보 - 정보수정 - 프로세스
		} else if(url.equals("/modifyPro.go")) {
			System.out.println("URL: /modifyPro.go");
		
			MemberServiceImpl service = new MemberServiceImpl();
			service.modifyPro(request, response);
			
			linkPage = "/process/modifyPro.jsp";
		
		// 회원탈퇴 - 비밀번호 확인
		} else if(url.equals("/leave_pwdcheck.go")) {
			System.out.println("URL: /leave_pwdcheck.go");
			
			MemberServiceImpl service = new MemberServiceImpl();
			service.loadingVO(request, response);
			
			linkPage = "/pages/leave_pwdcheck.jsp";
			
		// 회원탈퇴 - 프로세스
		} else if(url.equals("/leave.go")) {
			System.out.println("URL : /leave.go");
			
			MemberServiceImpl service = new MemberServiceImpl();
			service.deletePro(request, response);
			
			linkPage = "/process/leavePro.jsp";
			
		// 회원탈퇴 - 완료
		} else if (url.equals("/leaveSucess.go")) {
			System.out.println("URL : /leaveSucess.go");
			
			request.getSession().removeAttribute("sessionId");
			request.getSession().removeAttribute("sessionPwd");
			request.getSession().removeAttribute("sessionName");
			request.getSession().removeAttribute("sessionAuth");
			request.setAttribute("selectCnt", 4);
			
			linkPage = "/pages/main.jsp";
			
		// 회원정보 - 예약내역 리스트
		} else if(url.equals("/bookinglist.go")) {
			System.out.println("URL: /bookinglist.go");
			
			OrderServiceImpl service = new OrderServiceImpl();
			service.viewBooking(request, response, 5);
			
			linkPage = "/pages/bookinglist.jsp";
			
		// 회원정보 - 예약상세
		} else if(url.equals("/bookingInfo.go")) {
			System.out.println("URL : /bookingInfo.go");
			
			OrderService service = new OrderServiceImpl();
			service.bookingView(request, response);
			
			linkPage = "/pages/bookingInfo.jsp";
		
		// 회원정보 - 스크랩 목록
		} else if(url.equals("/scraplist.go")) {
			System.out.println("URL: /scraplist.go");
			
			OrderServiceImpl service = new OrderServiceImpl();
			service.viewScrap(request, response, 3);
			
			linkPage = "/pages/scraplist.jsp";
		
		// 새로고침 잔머리
		} else if(url.equals("/sucess.go")) {
			System.out.println("URL : /sucess.go");
			
			linkPage = "/process/trick.jsp";
			
		// 관리자 - 회원정보 상세
		} else if(url.equals("/memberInfo.go")) {
			System.out.println("URL : /memberInfo.go");
			
			MemberServiceImpl service = new MemberServiceImpl();
			service.loadingMemberInfo(request, response);
			
			linkPage = "/admin/admin_memberInfo.jsp";
			
		// 관리자 - 회원탈퇴
		} else if(url.equals("/adminLeave.go")) {
			System.out.println("URL : /adminLeave.go");
			
			MemberServiceImpl service = new MemberServiceImpl();
			service.adminDeletePro(request, response);
			
			linkPage = "/process/adminLeavePro.jsp";
			
		// 관리자 - 정보수정
		} else if(url.equals("/adminModifyPro.go")) {
			System.out.println("URL: /adminModifyPro.go");
		
			MemberServiceImpl service = new MemberServiceImpl();
			service.adminModifyPro(request, response);
			
			linkPage = "/process/adminModifyPro.jsp";
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(linkPage);
		dispatcher.forward(request, response);
	}
}