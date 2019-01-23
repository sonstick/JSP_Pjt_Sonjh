package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardService {
	
	// 공지사항 목록
	public void noticeList(HttpServletRequest request, HttpServletResponse response);
	
	// 문의사항 목록
	public void invoiceList(HttpServletRequest request, HttpServletResponse response);
	
	// 공지 상세보기
	public void noticeView(HttpServletRequest request, HttpServletResponse response);

	// 문의 비밀번호 확인
	public void pwdCheck(HttpServletRequest request, HttpServletResponse response);
	
	// 문의 상세보기
	public void invoiceView(HttpServletRequest request, HttpServletResponse response);
	
	// 문의 수정처리
	public void invoiceModifyPro(HttpServletRequest request, HttpServletResponse response);
	
	// 공지 수정 처리
	public void noticeModifyPro(HttpServletRequest request, HttpServletResponse response);
	
	// 공지쓰기 폼
	public void noticeWriteForm(HttpServletRequest request, HttpServletResponse response);
	
	// 공지쓰기 프로세스
	public void noticeWritePro(HttpServletRequest request, HttpServletResponse response);
	
	// 문의쓰기 폼
	public void invoiceWriteForm(HttpServletRequest request, HttpServletResponse response);
	
	// 문의쓰기 프로세스
	public void invoiceWritePro(HttpServletRequest request, HttpServletResponse response);
	
	// 문의 답변
	public void invoiceCmt(HttpServletRequest request, HttpServletResponse response);
	
	// 공지 삭제 처리
	public void deleteNoticePro(HttpServletRequest request, HttpServletResponse response);
	
	// 문의 삭제 처리
	public void deleteInvoicePro(HttpServletRequest request, HttpServletResponse response);
	
	// 관리자메엔 공지사항 목록
	public void loadingNotice(HttpServletRequest request, HttpServletResponse response, int setPageSize);
	
	// 관리자메엔 문의사항 목록
	public void loadingInvoice(HttpServletRequest request, HttpServletResponse response, int setPageSize);
}
