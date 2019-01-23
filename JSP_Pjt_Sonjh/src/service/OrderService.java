package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OrderService {
	
	// 상품정보
	public void productInfo(HttpServletRequest request, HttpServletResponse response);
	
	// 예약 프로세스
	public void bookingPro(HttpServletRequest request, HttpServletResponse response);
	
	// 예약내역
	public void viewBooking(HttpServletRequest request, HttpServletResponse response, int setPageSize);
	
	// 예약정보 로딩
	public void bookingView(HttpServletRequest request, HttpServletResponse response);
	
	// 예약 취소
	public void bookingCancelPro(HttpServletRequest request, HttpServletResponse response);
	
	// 스크랩 하기
	public void scrapPro(HttpServletRequest request, HttpServletResponse response);
	
	// 스크랩 정보 로딩
	public void viewScrap(HttpServletRequest request, HttpServletResponse response, int setPageSize);
	
	// 스크랩 삭제
	public void scrapDeletePro(HttpServletRequest request, HttpServletResponse response);
	
	// 예약 목록
	public void loadingBooking(HttpServletRequest request, HttpServletResponse response, int setPageSize);
}
