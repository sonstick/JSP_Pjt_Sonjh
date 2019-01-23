package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*import java.sql.Timestamp;*/
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.ProductVO;

public class ProductDAOImpl implements ProductDAO {

	DataSource datasource;
	
	private static ProductDAOImpl instance = new ProductDAOImpl();
	public static ProductDAOImpl getInstance() {
		return instance;
	}
	
	private ProductDAOImpl() {
		try {
			Context context = new InitialContext();
			datasource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g_JSP_Pjt_Sonjh");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 메인 상품정보
	@Override
	public ProductVO getProductInfo(ProductVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM product";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setP_no(rs.getInt("p_no"));
				vo.setCountry(rs.getString("country"));
				vo.setCity(rs.getString("city"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_thumbs(rs.getString("p_thumbs"));
				vo.setP_image_1(rs.getString("p_image_1"));
				vo.setP_image_2(rs.getString("p_image_2"));
				vo.setP_image_3(rs.getString("p_image_3"));
				vo.setP_image_4(rs.getString("p_image_4"));
				vo.setP_readCnt(rs.getInt("p_readCnt"));
				vo.setP_reg_date(rs.getTimestamp("p_reg_date"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	// 상품 갯수 구하기
	@Override
	public int getArticleCnt() {
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM product";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				selectCnt = rs.getInt("cnt");
			} else {
				selectCnt = 0;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return selectCnt;
	}

	// 상품 목록 조회
	@Override
	public ArrayList<ProductVO> getArticleList(int start, int end) {
		// 1. 큰 바구니 선언
		ArrayList<ProductVO> dtos = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT * " + 
					"FROM (SELECT p_no,country,city,p_name,p_content,p_thumbs,p_image_1,p_image_2,p_image_3,p_image_4,p_readCnt,p_reg_date, rownum as rNum " + 
							"FROM (SELECT * FROM product ORDER BY p_no DESC) " +
						") " +
						"WHERE rNum >= ? AND rNum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				// 2. 큰 바구니 생성(dtos)
				dtos = new ArrayList<ProductVO>(end - start + 1);
				do {
					// 3. 작은바구니 생성
					ProductVO vo = new ProductVO();
					
					// 4. 게시글 1건을 읽어서 rs를 작은 바구니(vo)에 담는다.
					vo.setP_no(rs.getInt("p_no"));
					vo.setCountry(rs.getString("country"));
					vo.setCity(rs.getString("city"));
					vo.setP_name(rs.getString("p_name"));
					vo.setP_content(rs.getString("p_content"));
					vo.setP_thumbs(rs.getString("p_thumbs"));
					vo.setP_image_1(rs.getString("p_image_1"));
					vo.setP_image_2(rs.getString("p_image_2"));
					vo.setP_image_3(rs.getString("p_image_3"));
					vo.setP_image_4(rs.getString("p_image_4"));
					vo.setP_readCnt(rs.getInt("p_readCnt"));
					vo.setP_reg_date(rs.getTimestamp("p_reg_date"));
					
					// 5. 큰바구니(ArrayList dtos)에 작은바구니(vo 또는 DTO, 게시글 1건)를 담는다.
					dtos.add(vo);
					
				} while(rs.next());
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	}

	// 상품 조회수 증가
	@Override
	public void addReadCnt(int p_no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE product SET p_readCnt = p_readCnt + 1 WHERE p_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_no);
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 상품 상세페이지 조회
	@Override
	public ProductVO getArticle(int p_no) {
		ProductVO vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM product WHERE p_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 바구니 생성
				vo = new ProductVO();
				
				// 게시글 1건을 읽어서 작은 바구니에 컬럼별로 담는다.
				vo.setP_no(rs.getInt("p_no"));
				vo.setCountry(rs.getString("country"));
				vo.setCity(rs.getString("city"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_content(rs.getString("p_content"));
				vo.setP_thumbs(rs.getString("p_thumbs"));
				vo.setP_image_1(rs.getString("p_image_1"));
				vo.setP_image_2(rs.getString("p_image_2"));
				vo.setP_image_3(rs.getString("p_image_3"));
				vo.setP_image_4(rs.getString("p_image_4"));
				vo.setP_readCnt(rs.getInt("p_readCnt"));
				vo.setP_reg_date(rs.getTimestamp("p_reg_date"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		// 바구니를 리턴한다.
		return vo;
	}

	// 상품 수정 처리
	@Override
	public int updateProduct(ProductVO vo) {
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE product SET p_name=?, country=?, city=?, p_thumbs=?, p_image_1=?, p_image_2=?, p_image_3=?, p_image_4=?, p_content=? WHERE p_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getP_name());
			pstmt.setString(2, vo.getCountry());
			pstmt.setString(3, vo.getCity());
			pstmt.setString(4, vo.getP_thumbs());
			pstmt.setString(5, vo.getP_image_1());
			pstmt.setString(6, vo.getP_image_2());
			pstmt.setString(7, vo.getP_image_3());
			pstmt.setString(8, vo.getP_image_4());
			pstmt.setString(9, vo.getP_content());
			pstmt.setInt(10, vo.getP_no());
			
			updateCnt = pstmt.executeUpdate();

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return updateCnt;
	}

	@Override
	public int insertProduct(ProductVO vo) {
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = datasource.getConnection();
			
			sql = "INSERT INTO product(p_no, p_name, country, city, p_thumbs, p_image_1, p_image_2, p_image_3, p_image_4, p_content) " + 
					"VALUES (product_seq.nextval,?,?,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getP_name());
			pstmt.setString(2, vo.getCountry());
			pstmt.setString(3, vo.getCity());
			pstmt.setString(4, vo.getP_thumbs());
			pstmt.setString(5, vo.getP_image_1());
			pstmt.setString(6, vo.getP_image_2());
			pstmt.setString(7, vo.getP_image_3());
			pstmt.setString(8, vo.getP_image_4());
			pstmt.setString(9, vo.getP_content());
			
			insertCnt = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) pstmt.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return insertCnt;
	}

	// 상품 삭제
	@Override
	public int ProductDelete(int p_no) {
		int deleteCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = null;
		
		try {
			conn = datasource.getConnection();

			sql = "DELETE product WHERE p_no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, p_no);
			deleteCnt = pstmt.executeUpdate();


		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}

		return deleteCnt;
	}
	
	
}
