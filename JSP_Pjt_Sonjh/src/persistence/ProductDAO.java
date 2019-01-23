package persistence;

import java.util.ArrayList;

import vo.ProductVO;

public interface ProductDAO {

	// 메인 상품정보
	public ProductVO getProductInfo(ProductVO vo);
	
	// 상품 갯수 구하기
	public int getArticleCnt();
	
	// 상품 목록 조회
	public ArrayList<ProductVO> getArticleList(int start, int end);
	
	// 상품 조회수 증가
	public void addReadCnt(int p_no);
	
	// 상품 상세페이지 조회
	public ProductVO getArticle(int p_no);
	
	// 상품 수정처리
	public int updateProduct(ProductVO vo);
	
	// 상품등록 처리
	public int insertProduct(ProductVO vo);
	
	// 상품 삭제
	public int ProductDelete(int p_no);
}
