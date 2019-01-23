package service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.MemberDAO;
import persistence.MemberDAOImpl;
import persistence.OrderDAO;
import persistence.OrderDAOImpl;
import vo.MemberVO;
import vo.OrderVO;
import vo.ProductVO;

public class OrderServiceImpl implements OrderService {
	
	// 상품정보
	@Override
	public void productInfo(HttpServletRequest request, HttpServletResponse response) {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		System.out.println("확인"+p_no);
		
		ProductVO vo = new ProductVO();
		
		OrderDAO dao = OrderDAOImpl.getInstance();
		vo = dao.getProductInfo(p_no);
		
		request.setAttribute("productDto", vo);
	}

	// 예약 프로세스
	@Override
	public void bookingPro(HttpServletRequest request, HttpServletResponse response) {
		OrderVO vo = new OrderVO();
		vo.setP_no(Integer.parseInt(request.getParameter("p_no")));
		vo.setId((String)request.getSession().getAttribute("sessionId"));
		vo.setDepart_date(Date.valueOf(request.getParameter("depart_date")));
		vo.setReturn_date(Date.valueOf(request.getParameter("return_date")));
		vo.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		OrderDAO dao = OrderDAOImpl.getInstance();
		int insertCnt = dao.insertBooking(vo);
		
		request.setAttribute("insertCnt", insertCnt);
		request.setAttribute("vo", vo);
	}

	// 예약정보
	@Override
	public void viewBooking(HttpServletRequest request, HttpServletResponse response, int setPageSize) {
		int pageSize = setPageSize;		// 한 페이지당 출력할 글 갯수
		int pageBlock = 3;				// 한 블록당 페이지 갯수
		
		int cnt = 0;					// 글 갯수
		int start = 0;					// 현재 페이지 시작 글번호
		int end = 0;					// 현재 페이지 마지막 글번호
		int number = 0;					// 출력용 글번호(현재 페이지까지 등록된 글 갯수 / 중간에 num이 삭제되더라도 글번호 시퀀스가 유지되도록)
		String pageNum = null;			// 페이지 번호
		int currentPage = 0;			// 현재 페이지
		
		int pageCount = 0;				// 페이지 갯수
		int startPage = 0;				// 시작 페이지
		int endPage = 0;				// 마지막 페이지
		
		OrderDAO dao = OrderDAOImpl.getInstance();
		cnt = dao.getBookingCnt();
		
		pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";	// 첫 페이지를 1페이지로 지정
		}
		
		currentPage = Integer.parseInt(pageNum);
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize - 1;
		if(end > cnt) end = cnt;
		number = cnt - (currentPage - 1) * pageSize;
		if(cnt > 0) {
			ArrayList<OrderVO> dtos = dao.getBookingList(start, end);
			
			request.setAttribute("bookingDtos", dtos);
		}
		startPage = (currentPage / pageBlock) * pageBlock + 1;
		if(currentPage % pageBlock == 0) startPage -= pageBlock;
		endPage = startPage + pageBlock - 1;
		if(endPage > pageCount) endPage = pageCount;
		
		request.setAttribute("bookingCnt", cnt);						// 글 갯수
		request.setAttribute("bookingNumber", number);					// 출력용 글번호(현재 페이지까지 등록된 글 갯수)
		request.setAttribute("bookingPageNum", pageNum);				// 페이지 번호
		
		if(cnt > 0) {
			request.setAttribute("bookingStartPage", startPage);		// 시작페이지
			request.setAttribute("bookingEndPage", endPage);			// 마지막 페이지
			request.setAttribute("bookingPageBlock", pageBlock);		// 출력할 페이지 갯수(한 블록당 페이지 갯수)
			request.setAttribute("bookingPageCount", pageCount);		// 페이지 갯수
			request.setAttribute("bookingCurrentPage", currentPage);	// 현재 페이지
		}
		
		// 회원정보 로딩
		MemberVO memVo = new MemberVO();
		memVo.setId((String)request.getSession().getAttribute("sessionId"));
		MemberDAO memDao = MemberDAOImpl.getInstance();
		memVo = memDao.selectMember(memVo);
		System.out.println("확인"+memVo.getMember_image());

		request.setAttribute("vo", memVo);
	}

	// 예약정보 로딩
	@Override
	public void bookingView(HttpServletRequest request, HttpServletResponse response) {
		OrderVO vo = new OrderVO();
		vo.setId((String)request.getSession().getAttribute("sessionId"));
		vo.setNo(Integer.parseInt(request.getParameter("no")));
		
		OrderDAO dao = OrderDAOImpl.getInstance();
		int selectCnt = dao.selectBooking(vo);
		
		request.setAttribute("selectOrderCnt", selectCnt);
		request.setAttribute("vo", vo);
	}

	// 예약 취소
	@Override
	public void bookingCancelPro(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		
		OrderDAO dao = OrderDAOImpl.getInstance();
		
		int deleteCnt = 0;
		deleteCnt = dao.BookingCalcel(no);
		
		request.setAttribute("deleteCnt", deleteCnt);
	}

	// 스크랩 프로세스
	@Override
	public void scrapPro(HttpServletRequest request, HttpServletResponse response) {
		OrderVO vo = new OrderVO();
		vo.setP_no(Integer.parseInt(request.getParameter("p_no")));
		vo.setId((String)request.getSession().getAttribute("sessionId"));
		
		OrderDAO dao = OrderDAOImpl.getInstance();
		int insertCnt = dao.insertScrap(vo);
		
		request.setAttribute("insertCnt", insertCnt);
		request.setAttribute("vo", vo);
	}
	
	// 스크랩 정보 로딩
	@Override
	public void viewScrap(HttpServletRequest request, HttpServletResponse response, int setPageSize) {
		int pageSize = setPageSize;		// 한 페이지당 출력할 글 갯수
		int pageBlock = 3;				// 한 블록당 페이지 갯수
		
		int cnt = 0;					// 글 갯수
		int start = 0;					// 현재 페이지 시작 글번호
		int end = 0;					// 현재 페이지 마지막 글번호
		int number = 0;					// 출력용 글번호(현재 페이지까지 등록된 글 갯수 / 중간에 num이 삭제되더라도 글번호 시퀀스가 유지되도록)
		String pageNum = null;			// 페이지 번호
		int currentPage = 0;			// 현재 페이지
		
		int pageCount = 0;				// 페이지 갯수
		int startPage = 0;				// 시작 페이지
		int endPage = 0;				// 마지막 페이지
		
		OrderDAO dao = OrderDAOImpl.getInstance();
		cnt = dao.getScrapCnt();
		
		pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";	// 첫 페이지를 1페이지로 지정
		}
		
		currentPage = Integer.parseInt(pageNum);
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize - 1;
		if(end > cnt) end = cnt;
		number = cnt - (currentPage - 1) * pageSize;
		if(cnt > 0) {
			ArrayList<OrderVO> dtos = dao.getScrapList(start, end);
			
			request.setAttribute("scrapDtos", dtos);
		}
		startPage = (currentPage / pageBlock) * pageBlock + 1;
		if(currentPage % pageBlock == 0) startPage -= pageBlock;
		endPage = startPage + pageBlock - 1;
		if(endPage > pageCount) endPage = pageCount;
		
		request.setAttribute("scrapCnt", cnt);						// 글 갯수
		request.setAttribute("scrapNumber", number);					// 출력용 글번호(현재 페이지까지 등록된 글 갯수)
		request.setAttribute("scrapPageNum", pageNum);				// 페이지 번호
		
		if(cnt > 0) {
			request.setAttribute("scrapStartPage", startPage);		// 시작페이지
			request.setAttribute("scrapEndPage", endPage);			// 마지막 페이지
			request.setAttribute("scrapPageBlock", pageBlock);		// 출력할 페이지 갯수(한 블록당 페이지 갯수)
			request.setAttribute("scrapPageCount", pageCount);		// 페이지 갯수
			request.setAttribute("scrapCurrentPage", currentPage);	// 현재 페이지
		}
		
		// 회원정보 로딩
		MemberVO memVo = new MemberVO();
		memVo.setId((String)request.getSession().getAttribute("sessionId"));
		MemberDAO memDao = MemberDAOImpl.getInstance();
		memVo = memDao.selectMember(memVo);
		System.out.println("확인"+memVo.getMember_image());

		request.setAttribute("vo", memVo);
	}

	// 스크랩 삭제
	@Override
	public void scrapDeletePro(HttpServletRequest request, HttpServletResponse response) {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		
		OrderDAO dao = OrderDAOImpl.getInstance();
		
		int deleteCnt = 0;
		deleteCnt = dao.ScrapDelete(p_no);
		
		request.setAttribute("deleteCnt", deleteCnt);
	}

	// 예약 목록
	@Override
	public void loadingBooking(HttpServletRequest request, HttpServletResponse response, int setPageSize) {

		// 페이징
		int pageSize = setPageSize;		// 한 페이지당 출력할 글 갯수
		int pageBlock = 3;		// 한 블록당 페이지 갯수
		
		int cnt = 0;			// 글 갯수
		int start = 0;			// 현재 페이지 시작 글번호
		int end = 0;			// 현재 페이지 마지막 글번호
		int number = 0;			// 출력용 글번호(현재 페이지까지 등록된 글 갯수 / 중간에 num이 삭제되더라도 글번호 시퀀스가 유지되도록)
		String pageNum = null;	// 페이지 번호
		int currentPage = 0;	// 현재 페이지
		
		int pageCount = 0;		// 페이지 갯수
		int startPage = 0;		// 시작 페이지
		int endPage = 0;		// 마지막 페이지
		
		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		OrderDAO dao = OrderDAOImpl.getInstance();
		
		// 5-1단계. 글 갯수 구하기
		cnt = dao.getBookingCnt();
		System.out.println("cnt : " + cnt);	// 우선 테이블에 30건의 데이터를 insert 해 둔다(페이징 확인을 위해)
		
		pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";	// 첫 페이지를 1페이지로 지정
		}
		
		// 글 30건 기준
		currentPage = Integer.parseInt(pageNum);	// 현재 페이지 : 1
		System.out.println("currentPage : " + currentPage);
		
		// 페이지 갯수 6 = (30 / 5) + (0)
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);	// 페이지 갯수 + (나머지가 있으면) 1을 더한다. cnt:글 갯수
		
		// 현재 페이지 시작 글번호 1 (페이지별)
		// 1 = (1 - 1) * 5 + 1
		start = (currentPage - 1) * pageSize + 1;
		
		// 현재 페이지 마지막 글번호(페이지별)
		// 5 = 1 + 5 - 1
		end = start + pageSize - 1;
		System.out.println("start : " + start);
		System.out.println("end : " + end);
		
		if(end > cnt) end = cnt;
		
		// 출력용 글번호
		// 30 = 30 - (1 - 1) * 5
		number = cnt - (currentPage - 1) * pageSize; // 출력용 글번호(현재 페이지까지 등록된 글 갯수)
		System.out.println("number : " + number);
		System.out.println("pageSize : " + pageSize);
		
		if(cnt > 0) {
			// 5-2단계. 게시글 목록 조회
			ArrayList<OrderVO> dtos = dao.getBookingList(start, end);
			
			// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
			request.setAttribute("bookingDtos", dtos); // 큰바구니(게시글(vo, 작은 바구니) 목록)를 jsp로 넘김.
		}
		
		// 시작 페이지
		// 1 = (1 / 3) * 3 + 1
		startPage = (currentPage / pageBlock) * pageBlock + 1;
		if(currentPage % pageBlock == 0) startPage -= pageBlock;
		System.out.println("startPage : " + startPage);
		
		// 마지막 페이지
		// 3 = 1 + 3 - 1;
		endPage = startPage + pageBlock - 1;
		if(endPage > pageCount) endPage = pageCount;
		System.out.println("endPage : " + endPage);
		System.out.println("=============================================================================");
		
		request.setAttribute("bookingCnt", cnt);						// 글 갯수
		request.setAttribute("bookingNumber", number);					// 출력용 글번호(현재 페이지까지 등록된 글 갯수)
		request.setAttribute("bookingPageNum", pageNum);				// 페이지 번호
		
		if(cnt > 0) {
			request.setAttribute("bookingStartPage", startPage);		// 시작페이지
			request.setAttribute("bookingEndPage", endPage);			// 마지막 페이지
			request.setAttribute("bookingPageBlock", pageBlock);		// 출력할 페이지 갯수(한 블록당 페이지 갯수)
			request.setAttribute("bookingPageCount", pageCount);		// 페이지 갯수
			request.setAttribute("bookingCurrentPage", currentPage);	// 현재 페이지
		}
	}	
	
}
