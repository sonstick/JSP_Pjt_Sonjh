package service;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.BoardDAO;
import persistence.BoardDAOImpl;
import vo.InvoiceVO;

import vo.NoticeVO;

public class BoardServiceImpl implements BoardService {

	// 공지사항 목록
	@Override
	public void noticeList(HttpServletRequest request, HttpServletResponse response) {
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		
		// 페이징
		int pageSize = 8;		// 한 페이지당 출력할 글 갯수
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
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		// 5-1단계. 글 갯수 구하기
		cnt = dao.getNoticeCnt();
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
			ArrayList<NoticeVO> dtos = dao.getNoticeList(start, end);
			
			// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
			request.setAttribute("dtos", dtos); // 큰바구니(게시글(vo, 작은 바구니) 목록)를 jsp로 넘김.
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
		
		request.setAttribute("cnt", cnt);						// 글 갯수
		request.setAttribute("number", number);					// 출력용 글번호(현재 페이지까지 등록된 글 갯수)
		request.setAttribute("pageNum", pageNum);				// 페이지 번호
		
		if(cnt > 0) {
			request.setAttribute("startPage", startPage);		// 시작페이지
			request.setAttribute("endPage", endPage);			// 마지막 페이지
			request.setAttribute("pageBlock", pageBlock);		// 출력할 페이지 갯수(한 블록당 페이지 갯수)
			request.setAttribute("pageCount", pageCount);		// 페이지 갯수
			request.setAttribute("currentPage", currentPage);	// 현재 페이지
		}
	}
	
	// 문의사항 목록
	@Override
	public void invoiceList(HttpServletRequest request, HttpServletResponse response) {
			
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		
		// 페이징
		int pageSize = 8;		// 한 페이지당 출력할 글 갯수
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
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		// 5-1단계. 글 갯수 구하기
		cnt = dao.getInvoiceCnt();
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
			ArrayList<InvoiceVO> dtos = dao.getInvoiceList(start, end);
			
			// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
			request.setAttribute("dtos", dtos); // 큰바구니(게시글(vo, 작은 바구니) 목록)를 jsp로 넘김.
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
		
		request.setAttribute("cnt", cnt);						// 글 갯수
		request.setAttribute("number", number);					// 출력용 글번호(현재 페이지까지 등록된 글 갯수)
		request.setAttribute("pageNum", pageNum);				// 페이지 번호
		
		if(cnt > 0) {
			request.setAttribute("startPage", startPage);		// 시작페이지
			request.setAttribute("endPage", endPage);			// 마지막 페이지
			request.setAttribute("pageBlock", pageBlock);		// 출력할 페이지 갯수(한 블록당 페이지 갯수)
			request.setAttribute("pageCount", pageCount);		// 페이지 갯수
			request.setAttribute("currentPage", currentPage);	// 현재 페이지
		}
	}

	// 공지 상세보기
	@Override
	public void noticeView(HttpServletRequest request, HttpServletResponse response) {
		
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		int no = Integer.parseInt(request.getParameter("no"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		// 5-1단계. 조회수 증가
		dao.addNoticeReadCnt(no);
		
		// 5-2단계. 상세페이지 조회
		NoticeVO vo = dao.getNotice(no);
		
		// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
		request.setAttribute("dto", vo);
		request.setAttribute("pageNum", pageNum);
	}
	
	// 문의 비밀번호 확인
	@Override
	public void pwdCheck(HttpServletRequest request, HttpServletResponse response) {

	// 3단계. 화면으로부터 입력받은 값을 받아온다.
	int no = Integer.parseInt(request.getParameter("no"));
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	String strPwd = request.getParameter("pwd");
	
	// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
	BoardDAO dao = BoardDAOImpl.getInstance();
	
	// 5-1단계. 글 수정 전 비밀번호 인증
	int pwdCnt = dao.noPwdCheck(no, strPwd);
	
	// 5-2단계. 비밀번호가 일치하면 해당 글 1건을 읽어온다.
	InvoiceVO vo = null;
	if(pwdCnt == 1) {
		System.out.println("pwdCnt : 1");
		vo = dao.getInvoice(no);
	}
	
	// 6단계. request나 session에 처리 결과를 저장(jsp에서 받아야 함)
	request.setAttribute("dto", vo);
	request.setAttribute("pageNum", pageNum);
	request.setAttribute("no", no);
	request.setAttribute("pwdCnt", pwdCnt);

	}

	// 문의 상세보기
	@Override
	public void invoiceView(HttpServletRequest request, HttpServletResponse response) {
		
	// 3단계. 화면으로부터 입력받은 값을 받아온다.
	int no = Integer.parseInt(request.getParameter("no"));
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	
	// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
	BoardDAO dao = BoardDAOImpl.getInstance();
	
	// 5-1단계. 조회수 증가
	dao.addInvoiceReadCnt(no);
	
	// 5-2단계. 상세페이지 조회
	InvoiceVO vo = dao.getInvoice(no);
	
	// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
	request.setAttribute("dto", vo);
	request.setAttribute("pageNum", pageNum);
	}

	// 문의 수정처리
	@Override
	public void invoiceModifyPro(HttpServletRequest request, HttpServletResponse response) {
		
		// 3단계. 입력받은 값을 받아온다.
	int no = Integer.parseInt(request.getParameter("no"));
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	
	// 화면으로부터 입력받은 값을 vo에 담자
	InvoiceVO vo = new InvoiceVO();
	vo.setNo(no);
	vo.setSubject(request.getParameter("subject"));
	vo.setContent(request.getParameter("content"));
	
	// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
	BoardDAO dao = BoardDAOImpl.getInstance();
	
	// 5단계. 글 수정 실행(vo를 DAO로 전달하여 SQL 실행)
	int updateCnt = dao.updateInvoice(vo);
	
	// 6단계. request나 session에 처리 결과를 저장(jsp에서 받아야 하니깐!)
	request.setAttribute("updateCnt", updateCnt);
	request.setAttribute("pageNum", pageNum);
	request.setAttribute("no", no);
	}
	
	// 공지 수정처리
	@Override
	public void noticeModifyPro(HttpServletRequest request, HttpServletResponse response) {
		
		// 3단계. 입력받은 값을 받아온다.
	int no = Integer.parseInt(request.getParameter("no"));
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	
	// 화면으로부터 입력받은 값을 vo에 담자
	NoticeVO vo = new NoticeVO();
	vo.setNo(no);
	vo.setSubject(request.getParameter("subject"));
	vo.setContent(request.getParameter("content"));
	
	// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
	BoardDAO dao = BoardDAOImpl.getInstance();
	
	// 5단계. 글 수정 실행(vo를 DAO로 전달하여 SQL 실행)
	int updateCnt = dao.updateNotice(vo);
	
	// 6단계. request나 session에 처리 결과를 저장(jsp에서 받아야 하니깐!)
	request.setAttribute("updateCnt", updateCnt);
	request.setAttribute("pageNum", pageNum);
	request.setAttribute("no", no);
	}

	// 공지쓰기 폼
	@Override
	public void noticeWriteForm(HttpServletRequest request, HttpServletResponse response) {
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		int no = 0;
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		// 6단계. request나 session에 처리 결과를 저장(jsp에서 받아야 하니깐!)
		request.setAttribute("no", no);
		request.setAttribute("pageNum", pageNum);
	}
	
	// 공지쓰기 처리
	@Override
	public void noticeWritePro(HttpServletRequest request, HttpServletResponse response) {
		
		// 3단계. 입력받은 값을 받아온다.
		int no = Integer.parseInt(request.getParameter("no"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		// 화면으로부터 입력받은 값을 vo에 담자
		NoticeVO vo = new NoticeVO();
		vo.setNo(no);
		vo.setCountry(request.getParameter("country"));
		vo.setCity(request.getParameter("city"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContent(request.getParameter("content"));
		vo.setReadCnt(0);
		vo.setReg_date(new Timestamp(System.currentTimeMillis())); // db에서 reg_date가 sysdate로 되어있지 않을 경우 입력(db보다 우선적으로 처리된다.)
		
		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		// 5단계. 글쓰기 처리(vo를 DAO로 전달하여 SQL 실행)
		int insertCnt = dao.insertNotice(vo);
		
		// 6단계. request나 session에 처리 결과를 저장(jsp에서 받아야 하니깐!)
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", vo);
		request.setAttribute("insertCnt", insertCnt);
		
	}
	
	// 문의쓰기 폼
	@Override
	public void invoiceWriteForm(HttpServletRequest request, HttpServletResponse response) {
	
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		int no = Integer.parseInt(request.getParameter("no"));
		if(no == 0) {
			no = 0;
		}
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		 
		// 6단계. request나 session에 처리 결과를 저장(jsp에서 받아야 하니깐!)
		request.setAttribute("no", no);
		request.setAttribute("pageNum", pageNum);
	}
	
	// 문의쓰기 처리
	@Override
	public void invoiceWritePro(HttpServletRequest request, HttpServletResponse response) {
		
		// 3단계. 입력받은 값을 받아온다.
		int no = Integer.parseInt(request.getParameter("no"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String cmt = "isNull";
		
		// 화면으로부터 입력받은 값을 vo에 담자
		InvoiceVO vo = new InvoiceVO();
		vo.setNo(no);
		vo.setName(request.getParameter("name"));
		vo.setPwd(request.getParameter("pwd"));
		vo.setSubject(request.getParameter("subject"));
		vo.setContent(request.getParameter("content"));
		vo.setReadCnt(0);
		vo.setCmt(cmt);
		vo.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		// 5단계. 글쓰기 처리(vo를 DAO로 전달하여 SQL 실행)
		int insertCnt = dao.insertInvoice(vo);
		
		// 6단계. request나 session에 처리 결과를 저장(jsp에서 받아야 하니깐!)
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("dto", vo);
		request.setAttribute("insertCnt", insertCnt);		
	}

	// 문의 답변
	@Override
	public void invoiceCmt(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String cmt = request.getParameter("cmt");

		BoardDAO dao = BoardDAOImpl.getInstance();
		int updateCnt = dao.updateCmt(no, cmt);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("no", no);
		request.setAttribute("updateCnt", updateCnt);
	}
	
	// 공지 삭제 처리
	@Override
	public void deleteNoticePro(HttpServletRequest request, HttpServletResponse response) {
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		int no = Integer.parseInt(request.getParameter("no"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		
		// 5-2단계. 비밀번호가 일치하면 해당 글을 삭제한다.
		int deleteCnt = 0;
		deleteCnt = dao.deleteNotice(no);
		System.out.println("deleteCnt : " + deleteCnt);
		
		// 6단계. request나 session에 처리 결과를 저장(jsp에서 받아야 함)
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("deleteCnt", deleteCnt);
	}
	
	// 문의 삭제 처리
	@Override
	public void deleteInvoicePro(HttpServletRequest request, HttpServletResponse response) {
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		int no = Integer.parseInt(request.getParameter("no"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		
		// 5-2단계. 비밀번호가 일치하면 해당 글을 삭제한다.
		int deleteCnt = 0;
		deleteCnt = dao.deleteInvoice(no);
		System.out.println("deleteCnt : " + deleteCnt);
		
		// 6단계. request나 session에 처리 결과를 저장(jsp에서 받아야 함)
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("deleteCnt", deleteCnt);
	}

	// 공지사항 목록
	@Override
	public void loadingNotice(HttpServletRequest request, HttpServletResponse response, int setPageSize) {
		
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
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		// 5-1단계. 글 갯수 구하기
		cnt = dao.getNoticeCnt();
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
			ArrayList<NoticeVO> dtos = dao.getNoticeList(start, end);
			
			// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
			request.setAttribute("noticeDtos", dtos); // 큰바구니(게시글(vo, 작은 바구니) 목록)를 jsp로 넘김.
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
		
		request.setAttribute("noticeCnt", cnt);						// 글 갯수
		request.setAttribute("noticeNumber", number);					// 출력용 글번호(현재 페이지까지 등록된 글 갯수)
		request.setAttribute("noticePageNum", pageNum);				// 페이지 번호
		
		if(cnt > 0) {
			request.setAttribute("noticeStartPage", startPage);		// 시작페이지
			request.setAttribute("noticeEndPage", endPage);			// 마지막 페이지
			request.setAttribute("noticePageBlock", pageBlock);		// 출력할 페이지 갯수(한 블록당 페이지 갯수)
			request.setAttribute("noticePageCount", pageCount);		// 페이지 갯수
			request.setAttribute("noticeCurrentPage", currentPage);	// 현재 페이지
		}
	}

	// 문의사항 목록
	@Override
	public void loadingInvoice(HttpServletRequest request, HttpServletResponse response, int setPageSize) {

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
		BoardDAO dao = BoardDAOImpl.getInstance();
		
		// 5-1단계. 글 갯수 구하기
		cnt = dao.getInvoiceCnt();
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
			ArrayList<InvoiceVO> dtos = dao.getInvoiceList(start, end);
			
			// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
			request.setAttribute("invoiceDtos", dtos); // 큰바구니(게시글(vo, 작은 바구니) 목록)를 jsp로 넘김.
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
		
		request.setAttribute("invoiceCnt", cnt);						// 글 갯수
		request.setAttribute("invoiceNumber", number);					// 출력용 글번호(현재 페이지까지 등록된 글 갯수)
		request.setAttribute("invoicePageNum", pageNum);				// 페이지 번호
		
		if(cnt > 0) {
			request.setAttribute("invoiceStartPage", startPage);		// 시작페이지
			request.setAttribute("invoiceEndPage", endPage);			// 마지막 페이지
			request.setAttribute("invoicePageBlock", pageBlock);		// 출력할 페이지 갯수(한 블록당 페이지 갯수)
			request.setAttribute("invoicePageCount", pageCount);		// 페이지 갯수
			request.setAttribute("invoiceCurrentPage", currentPage);	// 현재 페이지
		}
	}
}
