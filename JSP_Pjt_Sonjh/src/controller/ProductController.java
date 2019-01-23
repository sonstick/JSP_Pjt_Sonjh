package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductService;
import service.ProductServiceImpl;

@WebServlet("*.product")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public ProductController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		product(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		product(request, response);
	}

	protected void product(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String linkPage="";
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url = uri.substring(contextPath.length());
		
		// 상품 목록
		if(url.equals("/list.product") || url.equals("/*.product")) {
			System.out.println("URL : /list.product");

			ProductService service = new ProductServiceImpl();
			service.productList(request, response);
			
			linkPage = "/pages/productlist.jsp";
			
		// 상품 상세보기
		} else if(url.equals("/productView.product")) {
			System.out.println("URL : /productView.product");
			
			ProductService service = new ProductServiceImpl();
			service.productView(request, response);
			
			linkPage = "/pages/productView.jsp";
			
		// 상품 수정
		} else if(url.equals("/productModify.product")) {
			System.out.println("URL : /productModify.product");
			
			ProductService service = new ProductServiceImpl();
			service.productView(request, response);
			
			linkPage = "/admin/admin_productModify.jsp";
			
		// 상품수정 처리
		} else if(url.equals("/productModifyPro.product")) {
			System.out.println("URL : productModifyPro");
			
			ProductService service = new ProductServiceImpl();
			service.productModifyPro(request, response);
			
			linkPage = "/process/productModifyPro.jsp";
			
		// 상품등록 페이지
		} else if(url.equals("/productWriteForm.product")) {
			System.out.println("URL : /productWriteForm.product");
			
			ProductService service = new ProductServiceImpl();
			service.productView(request, response);
			
			linkPage = "/admin/admin_productWriteForm.jsp";
			
		// 상품등록 처리
		} else if(url.equals("/productWritePro.product")) {
			System.out.println("URL : /productWritePro.product");
			
			ProductService service = new ProductServiceImpl();
			service.productWritePro(request, response);
			
			linkPage = "/process/productWritePro.jsp";
			
		// 상품 삭제 처리
		} else if(url.equals("/productDel.product")) {
			System.out.println("URL : /productDel.product");
			
			ProductService service = new ProductServiceImpl();
			service.productDeletePro(request, response);
			
			linkPage = "/process/productDelPro.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(linkPage);
		dispatcher.forward(request, response);
	}
}
