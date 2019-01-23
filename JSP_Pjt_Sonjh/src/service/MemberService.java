package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MemberService {
	
	// 로그인
	public void logInPro(HttpServletRequest request, HttpServletResponse response);
	
	// 회원가입 - ID 중복 확인
	public void confirmId(HttpServletRequest request, HttpServletResponse response);
	
	// 회원가입 - 프로세스
	public void signupPro(HttpServletRequest request, HttpServletResponse response);
	
	// 회원정보 - 정보조회
	public void loadingVO(HttpServletRequest request, HttpServletResponse response);
	
	// 회원정보 - 정보수정
	public void modifyPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
	
	// 비밀번호 확인
	public void pwdCheck(HttpServletRequest request, HttpServletResponse response);
	
	// 회원탈퇴
	public void deletePro(HttpServletRequest request, HttpServletResponse response);
	
	// 관리자 회원정보 로딩
	public void loadingMember(HttpServletRequest request, HttpServletResponse response, int setPageSize);
	
	// 관리자 회원정보 상세
	public void loadingMemberInfo(HttpServletRequest request, HttpServletResponse response);
	
	// 관리자 - 회원탈퇴
	public void adminDeletePro(HttpServletRequest request, HttpServletResponse response);
	
	// 관리자 - 정보수정
	public void adminModifyPro(HttpServletRequest request, HttpServletResponse response);
}
