package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ProductService {

	// 메인 상품정보
	public void mainProductList(HttpServletRequest request, HttpServletResponse response);
	
	// 상품목록
	public void productList(HttpServletRequest request, HttpServletResponse response);
	
	// 상품 상세보기
	public void productView(HttpServletRequest request, HttpServletResponse response);
	
	// 상품정보 로딩
	public void lodingProduct(HttpServletRequest request, HttpServletResponse response, int setPageSize);
	
	// 상품 수정처리
	public void productModifyPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	// 상품 등록
	public void productWritePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	// 상품 삭제
	public void productDeletePro(HttpServletRequest request, HttpServletResponse response);
}
