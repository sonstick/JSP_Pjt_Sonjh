package persistence;

import java.io.IOException;
import java.util.ArrayList;

import vo.MemberVO;

public interface MemberDAO {
	// 로그인 - id와 비밀번호 체크
	public int idPwdCheck(MemberVO vo);
	public int invoiceIdPwdCheck(MemberVO memVo);
	
	// 회원가입 - ID 중복확인 - id 체크
	public int idCheck(String strId);
	
	// 회원가입 - 처리 - 회원정보 서버에 전달
	public int insertMember(MemberVO vo);
	
	// 회원정보 불러오기
	public MemberVO selectMember(MemberVO vo);
	
	// 회원정보 - 정보수정 
	public int updateMember(MemberVO vo) throws IOException;
	
	// 회원탈퇴
	public int deleteMember(String strId, String strPwd);
	
	// 회원 수
	public int getMemberCnt();
	
	// 회원 목록
	public ArrayList<MemberVO> getMemberList(int start, int end);
	
	// 관리자 - 회원탈퇴
	public int adminDeleteMember(String strId);
	
	// 관리자 - 정보수정 
	public int adminUpdateMember(MemberVO vo);
}
