package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;
import service.BoardServiceImpl;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		board(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		board(request, response);
	}

	public void board(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String linkPage="";
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.substring(contextPath.length());
		
		// 공지사항 목록
		if(url.equals("/noticelist.board") || url.equals("/*.board")) {
			System.out.println("URL : /noticelist.board");
			
			BoardService service = new BoardServiceImpl();
			service.noticeList(request, response);
			
			linkPage = "/pages/noticelist.jsp";
			
		// 문의사항 목록
		} else if(url.equals("/invoicelist.board")) {
			System.out.println("URL : /invoicelist.board");
			
			BoardService service = new BoardServiceImpl();
			service.invoiceList(request, response);
			
			linkPage = "/pages/invoicelist.jsp";
			
		// 공지 상세보기
		} else if(url.equals("/noticeView.board")) {
			System.out.println("URL : /noticeView.board");
			
			BoardService service = new BoardServiceImpl();
			service.noticeView(request, response);
			linkPage = "/pages/noticeView.jsp";
			
		// 문의 비밀번호 확인
		} else if(url.equals("/invoicePwdCheck.board")){
			System.out.println("URL: /invoicePwdCheck.board");
			
			int no = Integer.parseInt(request.getParameter("no"));
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			
			request.setAttribute("no", no);
			request.setAttribute("pageNum", pageNum);
			
			linkPage = "/pages/invoicePwdCheck.jsp";
			
		// 문의 비밀번호 확인 프로세스
		} else if(url.equals("/invoiceViewPro.board")) {
			System.out.println("URL: /invoiceViewPro.board");
			
			int no = Integer.parseInt(request.getParameter("no"));
			int pageNum = Integer.parseInt(request.getParameter("pageNum"));
			
			BoardService service = new BoardServiceImpl();
			service.pwdCheck(request, response);
			
			request.setAttribute("no", no);
			request.setAttribute("pageNum", pageNum);
			
			linkPage = "process/invoiceViewPro.jsp";
			
		// 문의 상세보기
		} else if(url.equals("/invoiceView.board")) {
			System.out.println("URL : /invoiceView.board");
			
			BoardService service = new BoardServiceImpl();
			service.invoiceView(request, response);
			
			linkPage = "/pages/invoiceView.jsp";
			
		// 문의 수정
		} else if(url.equals("/invoiceModify.board")) {
			System.out.println("URL: /invoiceModify.board");
			
			BoardService service = new BoardServiceImpl();
			service.invoiceView(request, response);
			
			linkPage = "/pages/invoiceModify.jsp";
			
		// 문의 수정 프로세스
		} else if(url.equals("/invoiceModifyPro.board")) {
			System.out.println("URL: /invoiceModifyPro.board");
			
			BoardService service = new BoardServiceImpl();
			service.invoiceModifyPro(request, response);
			
			linkPage = "/process/invoiceModifyPro.jsp";
			
		// 공지 수정
		} else if(url.equals("/noticeModify.board")) {
			System.out.println("URL: /noticeModify.board");
			
			BoardService service = new BoardServiceImpl();
			service.noticeView(request, response);
			
			linkPage = "/pages/noticeModify.jsp";
		
		// 공지수정 프로세스
		} else if(url.equals("/noticeModifyPro.board")) {
			System.out.println("URL: /noticeModifyPro.board");
			
			BoardService service = new BoardServiceImpl();
			service.noticeModifyPro(request, response);
			
			linkPage = "/process/noticeModifyPro.jsp";
		
		// 공지 쓰기
		} else if(url.equals("/noticeWriteForm.board")) {
			System.out.println("URL: /noticeWriteForm.board");
			
			BoardService service = new BoardServiceImpl();
			service.noticeWriteForm(request, response);
			
			linkPage = "/pages/noticeWriteForm.jsp";
			
		// 공지쓰기 처리
		} else if(url.equals("/noticeWritePro.board")) {
			System.out.println("URL: /noticeWritePro.board");
			
			BoardService service = new BoardServiceImpl();
			service.noticeWritePro(request, response);
			
			linkPage = "/process/noticeWritePro.jsp";
			
		// 문의 쓰기
		} else if(url.equals("/invoiceWriteForm.board")) {
			System.out.println("URL: /invoiceWriteForm.board");
			
			BoardService service = new BoardServiceImpl();
			service.invoiceWriteForm(request, response);
			
			linkPage = "/pages/invoiceWriteForm.jsp";
			
		// 문의쓰기 처리
		} else if(url.equals("/invoiceWritePro.board")) {
			System.out.println("URL: /invoiceWritePro.board");
			
			BoardService service = new BoardServiceImpl();
			service.invoiceWritePro(request, response);
			
			linkPage = "/process/invoiceWritePro.jsp";
			
		// 문의 답변
		} else if(url.equals("/invoiceCmt.board")) {
			System.out.println("URL: /invoiceCmt.board");
			
			BoardService service = new BoardServiceImpl();
			service.invoiceCmt(request, response);
			
			linkPage = "/process/invoiceCmtPro.jsp";
		
		// 공지 식제
		} else if(url.equals("/deleteNotice.board")) {
			System.out.println("URL : /deleteNotice.board");
			BoardService service = new BoardServiceImpl();
			service.deleteNoticePro(request, response);
			
			linkPage="/process/deleteNoticePro.jsp";

		// 문의 식제
		} else if(url.equals("/deleteInvoice.board")) {
			System.out.println("URL : /deleteInvoice.board");
			BoardService service = new BoardServiceImpl();
			service.deleteInvoicePro(request, response);
			
			linkPage="/process/deleteInvoicePro.jsp";
		}

		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(linkPage);
		dispatcher.forward(request, response);
	}
}
