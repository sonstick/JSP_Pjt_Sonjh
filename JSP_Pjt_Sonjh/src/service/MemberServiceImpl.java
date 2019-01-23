package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import persistence.MemberDAO;
import persistence.MemberDAOImpl;
import vo.MemberVO;

public class MemberServiceImpl implements MemberService {

	// 로그인
	@Override
	public void logInPro(HttpServletRequest request, HttpServletResponse response) {
		// 화면으로부터 입력받은 값을 가져온다.
		MemberVO vo = new MemberVO();
		vo.setId(request.getParameter("id"));
		vo.setPwd(request.getParameter("pwd"));

		// 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();

		// 로그인 정보가 있는지 확인
		int selectCnt = dao.idPwdCheck(vo);
		if(selectCnt == 1) {
			System.out.println("selectCnt : "+selectCnt);
			request.getSession().setAttribute("sessionId", vo.getId());
			request.getSession().setAttribute("sessionName", vo.getName());
			request.getSession().setAttribute("sessionAuth", vo.getAuth());
		} else if(selectCnt == 2) {
			System.out.println("selectCnt : "+selectCnt);
			request.getSession().setAttribute("sessionId", vo.getId());
			request.getSession().setAttribute("sessionPwd", vo.getPwd());
			request.getSession().setAttribute("sessionName", vo.getName());
			request.getSession().setAttribute("sessionAuth", vo.getAuth());
		}

		// request나 session에 처리 결과를 저장
		request.setAttribute("selectCnt", selectCnt);
		request.setAttribute("vo", vo);
	}

	// 중복 확인
	@Override
	public void confirmId(HttpServletRequest request, HttpServletResponse response) {

		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		String strId = request.getParameter("id");

		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();

		// 5단계. 중복된 id가 있는지 확인
		int selectCnt = dao.idCheck(strId);

		// 6단계. request나 session에 처리 결과를 저장(jsp에 전달하기 위해서~)
		request.setAttribute("selectCnt", selectCnt);
		request.setAttribute("id", strId);
	}

	// 회원가입 - 처리
	@Override
	public void signupPro(HttpServletRequest request, HttpServletResponse response) {
		// 3단계. 화면으로부터 입력받은 값을 받아온다
		MemberVO vo = new MemberVO();

		vo.setId(request.getParameter("id"));
		vo.setPwd(request.getParameter("pwd"));
		vo.setName(request.getParameter("name"));
		vo.setEmail(request.getParameter("email"));
		vo.setPhone(request.getParameter("phone"));
		vo.setAddress(request.getParameter("address"));
		vo.setReg_date(new Timestamp(System.currentTimeMillis()));

		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();
		int insertCnt = dao.insertMember(vo);

		// 5단계. request나 session에 처리 결과를 저장
		request.setAttribute("insertCnt", insertCnt);
		request.setAttribute("vo", vo);

		String strId = vo.getId();
		request.getSession().setAttribute("sessionId", strId);
		request.getSession().setAttribute("sessionName", vo.getName());
	}
	
	// 회원정보 - 정보조회
	@Override
	public void loadingVO(HttpServletRequest request, HttpServletResponse response) {
		MemberVO vo = new MemberVO();
		vo.setId((String)request.getSession().getAttribute("sessionId"));

		MemberDAO dao = MemberDAOImpl.getInstance();
		vo = dao.selectMember(vo); 

		request.setAttribute("vo", vo);
	}
	
	// 비밀번호 확인
	@Override
	public void pwdCheck(HttpServletRequest request, HttpServletResponse response) {
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		MemberVO vo = new MemberVO();
		
		vo.setId((String)request.getSession().getAttribute("sessionId"));
		vo.setName((String)request.getSession().getAttribute("sessionName"));
		vo.setPwd(request.getParameter("pwd"));
		
		
		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();
		dao.idPwdCheck(vo);
		
		// 5-1단계. 비밀번호가 맞는지 확인
		int selectCnt = dao.idPwdCheck(vo);
		
		// 5-2단계. 있으면 로그인한 id로 정보 조회
		if(selectCnt == 1 || selectCnt == 2) {
			vo = dao.selectMember(vo);
			
			request.setAttribute("selectCnt", selectCnt);
			request.setAttribute("vo", vo);
		}
		
		// 6단계. request나 session에 처리 결과를 저장(jsp에 전달하기 위해서~)
		request.setAttribute("selectCnt", selectCnt);
		request.setAttribute("vo", vo);
	}

	// 회원정보 - 정보수정
	@Override
	public void modifyPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		MemberVO vo = new MemberVO();
		
		// 세션에 저장
		
		HttpSession session = request.getSession();
		int sizeLimit = 5 * 1024 * 1024;
	    String savePath = "images";
	    ServletContext context = session.getServletContext();
	    String uploadFilePath = context.getRealPath(savePath);
	    /*
	    String uploadFilePath = "D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images";
	    */
	    
	    // WEB-INF -> lib -> cos.jar 추가
	    MultipartRequest multi = new MultipartRequest(request, uploadFilePath, sizeLimit, "UTF-8");
	    InputStream in = null;
		OutputStream out  = null;
	
		try {
			in = new FileInputStream(uploadFilePath+"/"+multi.getFilesystemName("member_image")); // 톰캣의 이미지를 스트림에 저장
			/*out = new FileOutputStream("D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images"+"/"+multi.getFilesystemName("member_image"));*/
			out = new FileOutputStream("D:\\DEV43\\workspace\\Eclipse Photon 2018-06\\JSP_Pjt_Sonjh\\WebContent\\images"+"/"+multi.getFilesystemName("member_image")); // 실제 폴더로 스트림을 불러들여 이미지를 저장

			while(true) {
				int i = in.read();
				if(i == -1) {
					System.out.println("출력 완료");
					break;
				}
				out.write(i);
			}
		} catch(FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			if(in != null) in.close();
			if(out != null) out.close();
		}
		
		String strId = (String)session.getAttribute("sessionId");
		vo.setId(strId);
		vo.setPwd(multi.getParameter("pwd"));
		vo.setName(multi.getParameter("name"));
		vo.setEmail(multi.getParameter("email"));
		vo.setPhone(multi.getParameter("phone"));
		vo.setAddress(multi.getParameter("address"));
		vo.setMember_image(multi.getFilesystemName("member_image"));
		
		// 4단계. 다형성 적용, 싱글톤 방식으로  dao 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// 5단계. 회원정보 수정
		int updateCnt = dao.updateMember(vo);
		
		// 6단계. request나 session에 처리 결과를 저장(jsp에 전달하기 위함)
		request.setAttribute("updateCnt", updateCnt);
	}

	// 회원탈퇴
	@Override
	public void deletePro(HttpServletRequest request, HttpServletResponse response) {
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		String strId = (String)request.getSession().getAttribute("sessionId");
		String strPwd = request.getParameter("pwd");
		
		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// 5단계. 중복된 id가 있는지 확인
		int deleteCnt = dao.deleteMember(strId, strPwd);
		
		// 6단계. request나 session에 처리 결과를 저장(jsp에 전달하기 위해서~)
		// cnt와 id의 값을 MemberController로 넘겨준다
		request.setAttribute("deleteCnt", deleteCnt); // vo를 담은 cnt를 넘긴다.
	}

	// 관리자 메인 회원정보 로딩
	@Override
	public void loadingMember(HttpServletRequest request, HttpServletResponse response, int setPageSize) {
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
		
		// 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// 회원수 구하기
		cnt = dao.getMemberCnt();
		
		pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";	// 첫 페이지를 1페이지로 지정
		}
		
		currentPage = Integer.parseInt(pageNum);	// 현재 페이지 : 1
		
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize - 1;
		if(end > cnt) end = cnt;
		number = cnt - (currentPage - 1) * pageSize; // 출력용 글번호(현재 페이지까지 등록된 글 갯수)
		
		
		
		if(cnt > 0) {
			// 회원 목록 조회
			ArrayList<MemberVO> dtos = dao.getMemberList(start, end);
			
			// request나 session에 처리결과를 저장(jsp에 전달하기 위함)
			request.setAttribute("memberDtos", dtos); // 큰바구니(게시글(vo, 작은 바구니) 목록)를 jsp로 넘김.
		}
		
		// 시작 페이지
		startPage = (currentPage / pageBlock) * pageBlock + 1;
		if(currentPage % pageBlock == 0) startPage -= pageBlock;
		System.out.println("startPage : " + startPage);
		
		// 마지막 페이지
		endPage = startPage + pageBlock - 1;
		if(endPage > pageCount) endPage = pageCount;
		
		request.setAttribute("memberCnt", cnt);						// 글 갯수
		request.setAttribute("memberNumber", number);				// 출력용 글번호(현재 페이지까지 등록된 글 갯수)
		request.setAttribute("memberPageNum", pageNum);				// 페이지 번호
		
		if(cnt > 0) {
			request.setAttribute("memberStartPage", startPage);		// 시작페이지
			request.setAttribute("memberEndPage", endPage);			// 마지막 페이지
			request.setAttribute("memberPageBlock", pageBlock);		// 출력할 페이지 갯수(한 블록당 페이지 갯수)
			request.setAttribute("memberPageCount", pageCount);		// 페이지 갯수
			request.setAttribute("memberCurrentPage", currentPage);	// 현재 페이지
		}
	}

	// 관리자 회원정보 상세
	@Override
	public void loadingMemberInfo(HttpServletRequest request, HttpServletResponse response) {
		MemberVO vo = new MemberVO();
		vo.setId((String)request.getParameter("id"));

		MemberDAO dao = MemberDAOImpl.getInstance();
		vo = dao.selectMember(vo); 

		request.setAttribute("vo", vo);
	}

	// 관리자 - 회원탈퇴
	@Override
	public void adminDeletePro(HttpServletRequest request, HttpServletResponse response) {
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		String strId = (String)request.getParameter("id");
		
		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// 5단계. 중복된 id가 있는지 확인
		int deleteCnt = dao.adminDeleteMember(strId);
		
		// 6단계. request나 session에 처리 결과를 저장(jsp에 전달하기 위해서~)
		// cnt와 id의 값을 MemberController로 넘겨준다
		request.setAttribute("deleteCnt", deleteCnt); // vo를 담은 cnt를 넘긴다.
	}
	
	// 관리자 - 정보수정
	@Override
	public void adminModifyPro(HttpServletRequest request, HttpServletResponse response) {
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		MemberVO vo = new MemberVO();
		 
		vo.setId(request.getParameter("id"));
		vo.setPwd(request.getParameter("pwd"));
		vo.setName(request.getParameter("name"));
		vo.setEmail(request.getParameter("email"));
		vo.setPhone(request.getParameter("phone"));
		vo.setAddress(request.getParameter("address"));
		
		// 4단계. 다형성 적용, 싱글톤 방식으로  dao 객체 생성
		MemberDAO dao = MemberDAOImpl.getInstance();
		
		// 5단계. 회원정보 수정
		int updateCnt = dao.adminUpdateMember(vo);
		
		// 6단계. request나 session에 처리 결과를 저장(jsp에 전달하기 위함)
		request.setAttribute("updateCnt", updateCnt);
	}
	
}
