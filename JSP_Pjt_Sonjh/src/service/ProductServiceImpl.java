package service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import persistence.ProductDAO;
import persistence.ProductDAOImpl;
import vo.ProductVO;

public class ProductServiceImpl implements ProductService {

	// 메인 상품목록
	@Override
	public void mainProductList(HttpServletRequest request, HttpServletResponse response) {
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		// 페이징
		int pageSize = 4;		// 한 페이지당 출력할 글 갯수
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
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		// 5-1단계. 글 갯수 구하기
		cnt = dao.getArticleCnt();
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
			ArrayList<ProductVO> dtos = dao.getArticleList(start, end);
			
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

	// 상품목록
	@Override
	public void productList(HttpServletRequest request, HttpServletResponse response) {
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
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		// 5-1단계. 글 갯수 구하기
		cnt = dao.getArticleCnt();
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
			ArrayList<ProductVO> dtos = dao.getArticleList(start, end);
			
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
	
	// 상품 상세보기
	@Override
	public void productView(HttpServletRequest request, HttpServletResponse response) {
		
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		// 5-2단계. 상세페이지 조회
		ProductVO vo = dao.getArticle(p_no);
		
		// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
		request.setAttribute("dto", vo);
		request.setAttribute("pageNum", pageNum);
	}
	
	// 상품정보 로딩
	@Override
	public void lodingProduct(HttpServletRequest request, HttpServletResponse response, int setPageSize) {
		// 3단계. 화면으로부터 입력받은 값을 받아온다.
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
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		// 5-1단계. 글 갯수 구하기
		cnt = dao.getArticleCnt();
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
			ArrayList<ProductVO> dtos = dao.getArticleList(start, end);
			
			// 6단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
			request.setAttribute("productDtos", dtos); // 큰바구니(게시글(vo, 작은 바구니) 목록)를 jsp로 넘김.
		}
		
		// 시작 페이지
		// 1 = (1 / 3) * 3 + 1
		startPage = (currentPage / pageBlock) * pageBlock + 1;
		if(currentPage % pageBlock == 0) startPage -= pageBlock;
		
		// 마지막 페이지
		// 3 = 1 + 3 - 1;
		endPage = startPage + pageBlock - 1;
		if(endPage > pageCount) endPage = pageCount;
				
		request.setAttribute("productCnt", cnt);						// 글 갯수
		request.setAttribute("productNumber", number);					// 출력용 글번호(현재 페이지까지 등록된 글 갯수)
		request.setAttribute("productPageNum", pageNum);				// 페이지 번호
		
		if(cnt > 0) {
			request.setAttribute("productStartPage", startPage);		// 시작페이지
			request.setAttribute("productEndPage", endPage);			// 마지막 페이지
			request.setAttribute("productPageBlock", pageBlock);		// 출력할 페이지 갯수(한 블록당 페이지 갯수)
			request.setAttribute("productPageCount", pageCount);		// 페이지 갯수
			request.setAttribute("productCurrentPage", currentPage);	// 현재 페이지
		}
	}
	
	// 상품 수정처리
	@Override
	public void productModifyPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 3단계. 입력받은 값을 받아온다.
	int p_no = Integer.parseInt(request.getParameter("p_no"));
	int pageNum = Integer.parseInt(request.getParameter("pageNum"));
	
	HttpSession session = request.getSession();
	int sizeLimit = 5 * 1024 * 1024;
    String savePath = "images/product";
    ServletContext context = session.getServletContext();
    String uploadFilePath = context.getRealPath(savePath);
    
    MultipartRequest multi = new MultipartRequest(request, uploadFilePath, sizeLimit, "UTF-8");
    InputStream in = null;
	OutputStream out  = null;

	try {
		in = new FileInputStream(uploadFilePath+"/"+multi.getFilesystemName("p_thumbs")); // 톰캣의 이미지를 스트림에 저장
		/*out = new FileOutputStream("D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_thumbs"));*/
		out = new FileOutputStream("D:\\DEV43\\workspace\\Eclipse Photon 2018-06\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_thumbs")); // 실제 폴더로 스트림을 불러들여 이미지를 저장

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
	
	try {
		in = new FileInputStream(uploadFilePath+"/"+multi.getFilesystemName("p_image_1")); // 톰캣의 이미지를 스트림에 저장
		/*out = new FileOutputStream("D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_1"));*/
		out = new FileOutputStream("D:\\DEV43\\workspace\\Eclipse Photon 2018-06\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_1")); // 실제 폴더로 스트림을 불러들여 이미지를 저장

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
	
	try {
		in = new FileInputStream(uploadFilePath+"/"+multi.getFilesystemName("p_image_2")); // 톰캣의 이미지를 스트림에 저장
		/*out = new FileOutputStream("D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_2"));*/
		out = new FileOutputStream("D:\\DEV43\\workspace\\Eclipse Photon 2018-06\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_2")); // 실제 폴더로 스트림을 불러들여 이미지를 저장

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
	
	try {
		in = new FileInputStream(uploadFilePath+"/"+multi.getFilesystemName("p_image_3")); // 톰캣의 이미지를 스트림에 저장
		/*out = new FileOutputStream("D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_3"));*/
		out = new FileOutputStream("D:\\DEV43\\workspace\\Eclipse Photon 2018-06\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_3")); // 실제 폴더로 스트림을 불러들여 이미지를 저장

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
	
	try {
		in = new FileInputStream(uploadFilePath+"/"+multi.getFilesystemName("p_image_4")); // 톰캣의 이미지를 스트림에 저장
		/*out = new FileOutputStream("D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_4"));*/
		out = new FileOutputStream("D:\\DEV43\\workspace\\Eclipse Photon 2018-06\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_4")); // 실제 폴더로 스트림을 불러들여 이미지를 저장

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
	
	// 화면으로부터 입력받은 값을 vo에 담자
	ProductVO vo = new ProductVO();
	vo.setP_no(p_no);
	vo.setP_name(multi.getParameter("p_name"));
	vo.setCountry(multi.getParameter("country"));
	vo.setCity(multi.getParameter("city"));
	vo.setP_thumbs(multi.getFilesystemName("p_thumbs"));
	vo.setP_image_1(multi.getFilesystemName("p_image_1"));
	vo.setP_image_2(multi.getFilesystemName("p_image_2"));
	vo.setP_image_3(multi.getFilesystemName("p_image_3"));
	
	// 해결이 필요함
	if(multi.getFilesystemName("p_image_4") != null) {
		vo.setP_image_4(multi.getFilesystemName("p_image_4"));		
	} else {
		vo.setP_image_4("p_default.jpg");
	}
	vo.setP_content(multi.getParameter("p_content"));
	
	// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
	ProductDAO dao = ProductDAOImpl.getInstance();
	
	// 5단계. 글 수정 실행(vo를 DAO로 전달하여 SQL 실행)
	int updateCnt = dao.updateProduct(vo);
	
	// 6단계. request나 session에 처리 결과를 저장(jsp에서 받아야 하니깐!)
	request.setAttribute("updateCnt", updateCnt);
	request.setAttribute("pageNum", pageNum);
	request.setAttribute("p_no", p_no);
	}

	// 상품 등록
	@Override
	public void productWritePro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int sizeLimit = 5 * 1024 * 1024;
	    String savePath = "images/product";
	    ServletContext context = session.getServletContext();
	    String uploadFilePath = context.getRealPath(savePath);
	    
	    MultipartRequest multi = new MultipartRequest(request, uploadFilePath, sizeLimit, "UTF-8");

	    InputStream in = null;
		OutputStream out  = null;

		try {
			in = new FileInputStream(uploadFilePath+"/"+multi.getFilesystemName("p_thumbs")); // 톰캣의 이미지를 스트림에 저장
			/*out = new FileOutputStream("D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_thumbs"));*/
			out = new FileOutputStream("D:\\DEV43\\workspace\\Eclipse Photon 2018-06\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_thumbs")); // 실제 폴더로 스트림을 불러들여 이미지를 저장

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
		
		try {
			in = new FileInputStream(uploadFilePath+"/"+multi.getFilesystemName("p_image_1")); // 톰캣의 이미지를 스트림에 저장
			/*out = new FileOutputStream("D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_1"));*/
			out = new FileOutputStream("D:\\DEV43\\workspace\\Eclipse Photon 2018-06\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_1")); // 실제 폴더로 스트림을 불러들여 이미지를 저장

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
		
		try {
			in = new FileInputStream(uploadFilePath+"/"+multi.getFilesystemName("p_image_2")); // 톰캣의 이미지를 스트림에 저장
			/*out = new FileOutputStream("D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_2"));*/
			out = new FileOutputStream("D:\\DEV43\\workspace\\Eclipse Photon 2018-06\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_2")); // 실제 폴더로 스트림을 불러들여 이미지를 저장

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
		
		try {
			in = new FileInputStream(uploadFilePath+"/"+multi.getFilesystemName("p_image_3")); // 톰캣의 이미지를 스트림에 저장
			/*out = new FileOutputStream("D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_3"));*/
			out = new FileOutputStream("D:\\DEV43\\workspace\\Eclipse Photon 2018-06\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_3")); // 실제 폴더로 스트림을 불러들여 이미지를 저장

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
		
		try {
			in = new FileInputStream(uploadFilePath+"/"+multi.getFilesystemName("p_image_4")); // 톰캣의 이미지를 스트림에 저장
			/*out = new FileOutputStream("D:\\eclipse-workspace\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_4"));*/
			out = new FileOutputStream("D:\\DEV43\\workspace\\Eclipse Photon 2018-06\\JSP_Pjt_Sonjh\\WebContent\\images\\product"+"/"+multi.getFilesystemName("p_image_4")); // 실제 폴더로 스트림을 불러들여 이미지를 저장

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
		
		// 화면으로부터 입력받은 값을 vo에 담자
		ProductVO vo = new ProductVO();
		vo.setP_name(multi.getParameter("p_name"));
		vo.setCountry(multi.getParameter("country"));
		vo.setCity(multi.getParameter("city"));
		vo.setP_thumbs(multi.getFilesystemName("p_thumbs"));
		vo.setP_image_1(multi.getFilesystemName("p_image_1"));
		vo.setP_image_2(multi.getFilesystemName("p_image_2"));
		vo.setP_image_3(multi.getFilesystemName("p_image_3"));
		
		// 해결이 필요함
		if(multi.getFilesystemName("p_image_4") != null) {
			vo.setP_image_4(multi.getFilesystemName("p_image_4"));		
		} else {
			vo.setP_image_4("p_default.jpg");
		}
		vo.setP_content(multi.getParameter("p_content"));
		
		// 4단계. 다형성 적용, 싱글톤 방식으로 dao 객체 생성
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		// 5단계. 글쓰기 처리(vo를 DAO로 전달하여 SQL 실행)
		int insertCnt = dao.insertProduct(vo);
		
		// 6단계. request나 session에 처리 결과를 저장(jsp에서 받아야 하니깐!)
		request.setAttribute("insertCnt", insertCnt);
	}

	// 상품 삭제
	@Override
	public void productDeletePro(HttpServletRequest request, HttpServletResponse response) {
		int p_no = Integer.parseInt(request.getParameter("p_no"));
		
		ProductDAO dao = ProductDAOImpl.getInstance();
		
		int deleteCnt = 0;
		deleteCnt = dao.ProductDelete(p_no);
		
		request.setAttribute("deleteCnt", deleteCnt);
	}
	
	
}
