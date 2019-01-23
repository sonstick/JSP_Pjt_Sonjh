package persistence;

import java.util.ArrayList;

import vo.InvoiceVO;
import vo.NoticeVO;

public interface BoardDAO {
	
	// 공지 갯수
	public int getNoticeCnt();
	
	// 공지 목록
	public ArrayList<NoticeVO> getNoticeList(int start, int end);
	
	// 문의 갯수
	public int getInvoiceCnt();
		
	// 문의 목록
	public ArrayList<InvoiceVO> getInvoiceList(int start, int end);
	
	// 공지 조회수 증가
	public void addNoticeReadCnt(int no);
	
	// 공지 상세페이지 조회
	public NoticeVO getNotice(int no);

	// 문의 조회수 증가
	public void addInvoiceReadCnt(int no);
	
	// 문의 - 비밀번호 확인
	public int noPwdCheck(int no, String pwd);
	
	// 문의 상세페이지 조회
	public InvoiceVO getInvoice(int no);
	
	// 문의 수정처리
	public int updateInvoice(InvoiceVO vo);
	
	// 공지 수정처리
	public int updateNotice(NoticeVO vo);
	
	// 공지 글쓰기 프로세스
	public int insertNotice(NoticeVO vo);
	
	// 문의 글쓰기 프로세스
	public int insertInvoice(InvoiceVO vo);
	
	// 문의 답변
	public int updateCmt(int no, String cmt);
	
	// 공지삭제 처리
	public int deleteNotice(int no);

	// 문의삭제 처리
	public int deleteInvoice(int no);

}
