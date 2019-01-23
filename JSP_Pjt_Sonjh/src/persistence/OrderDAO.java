package persistence;

import java.util.ArrayList;

import vo.OrderVO;
import vo.ProductVO;

public interface OrderDAO {
	
	// 상품정보 로딩
	public ProductVO getProductInfo(int p_no);
	
	// 예약 하기
	public int insertBooking(OrderVO vo);
	
	// 예약 갯수
	public int getBookingCnt();
	
	// 예약 목록
	public ArrayList<OrderVO> getBookingList(int start, int end);
	
	// 예약정보
	public int selectBooking(OrderVO vo);
	
	// 예약 취소
	public int BookingCalcel(int no);
	
	// 예약 하기
	public int insertScrap(OrderVO vo);
	
	// 스크랩 갯수
	public int getScrapCnt();
	
	// 예약 목록
	public ArrayList<OrderVO> getScrapList(int start, int end);
	
	// 스크랩 삭제
	public int ScrapDelete(int p_no);
}
