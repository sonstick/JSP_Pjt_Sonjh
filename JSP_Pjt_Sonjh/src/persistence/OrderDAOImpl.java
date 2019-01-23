package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.OrderVO;
import vo.ProductVO;

public class OrderDAOImpl implements OrderDAO {
	DataSource datasource;
	
	private static OrderDAOImpl instance = new OrderDAOImpl();
	public static OrderDAOImpl getInstance() {
		return instance;
	}
	
	private OrderDAOImpl() {
		try {
			Context context = new InitialContext();
			datasource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g_JSP_Pjt_Sonjh");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 상품정보 로딩
	@Override
	public ProductVO getProductInfo(int p_no) {
		ProductVO vo = new ProductVO();
		
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

	// 예약 하기
	@Override
	public int insertBooking(OrderVO vo) {
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "INSERT INTO booking(no,depart_date,return_date,reg_date,p_no,id) VALUES (booking_seq.nextval,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setDate(1, vo.getDepart_date());
			pstmt.setDate(2, vo.getReturn_date());
			pstmt.setTimestamp(3, vo.getReg_date());
			pstmt.setInt(4, vo.getP_no());
			pstmt.setString(5, vo.getId());
			
			insertCnt = pstmt.executeUpdate();
			
			sql = "SELECT b.no, b.depart_date, b.return_date, b.reg_date, b.p_no, p.country, p.city, p.p_name, p.p_thumbs,m.id,m.name,m.email,m.phone,m.address,m.member_image FROM booking b, product p, member m WHERE p.p_no = b.p_no AND m.id = b.id AND no=?";
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setNo(rs.getInt("no"));
				vo.setDepart_date(rs.getDate("depart_date"));
				vo.setReturn_date(rs.getDate("return_date"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setP_no(rs.getInt("p_no"));
				vo.setId(rs.getString("id"));
				vo.setCountry(rs.getString("country"));
				vo.setCity(rs.getString("city"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_thumbs(rs.getString("p_thumbs"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setMember_image(rs.getString("member_image"));
				
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
		
		return insertCnt;
	}

	// 예약정보 로딩
	@Override
	public int selectBooking(OrderVO vo) {
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT b.no, b.depart_date, b.return_date, b.reg_date, b.p_no, p.country, p.city, p.p_name, p.p_content, p.p_thumbs,m.id,m.name,m.email,m.phone,m.phone,m.address,m.member_image FROM booking b, product p, member m WHERE p.p_no = b.p_no AND m.id = b.id AND m.id=? AND b.no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setInt(2, vo.getNo());

			rs = pstmt.executeQuery();

			// 로그인한 id에 해당하는 데이터가 있다면
			if(rs.next()) {
				vo.setNo(rs.getInt("no"));
				vo.setDepart_date(rs.getDate("depart_date"));
				vo.setReturn_date(rs.getDate("return_date"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setP_no(rs.getInt("p_no"));
				vo.setId(rs.getString("id"));
				vo.setCountry(rs.getString("country"));
				vo.setCity(rs.getString("city"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_thumbs(rs.getString("p_thumbs"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setMember_image(rs.getString("member_image"));
				selectCnt = 1;
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
	
	// 예약 갯수
	@Override
	public int getBookingCnt() {
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM booking";
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
		
	// 예약 정보 리스트 로딩
	public ArrayList<OrderVO> getBookingList(int start, int end) {
	// 1. 큰 바구니 선언
	ArrayList<OrderVO> dtos = null;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		conn = datasource.getConnection();
		String sql = "SELECT * FROM(SELECT no, depart_date, return_date, reg_date, p_no, country, city, p_name, p_thumbs, id, name, email, phone, address, member_image, rNum FROM (SELECT * FROM bpm_view)) WHERE rNum >= ? AND rNum <= ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			// 2. 큰 바구니 생성(dtos)
			dtos = new ArrayList<OrderVO>(end - start + 1);
			do {
				// 3. 작은바구니 생성
				OrderVO vo = new OrderVO();
				
				// 4. 게시글 1건을 읽어서 rs를 작은 바구니(vo)에 담는다.
				vo.setNo(rs.getInt("no"));
				vo.setDepart_date(rs.getDate("depart_date"));
				vo.setReturn_date(rs.getDate("return_date"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setP_no(rs.getInt("p_no"));
				vo.setId(rs.getString("id"));
				vo.setCountry(rs.getString("country"));
				vo.setCity(rs.getString("city"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_thumbs(rs.getString("p_thumbs"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setMember_image(rs.getString("member_image"));
				
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

	// 예약 취소
	@Override
	public int BookingCalcel(int no) {
		int deleteCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = null;
		
		try {
			conn = datasource.getConnection();

			sql = "DELETE booking WHERE no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
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
	
	// 예약 하기
	@Override
	public int insertScrap(OrderVO vo) {
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "INSERT INTO scrap(no,p_no,id) VALUES (scrap_seq.nextval,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getP_no());
			pstmt.setString(2, vo.getId());
			
			insertCnt = pstmt.executeUpdate();
			
			sql = "SELECT s.no, s.p_no, p.p_name, p.p_thumbs, m.id FROM scrap s, product p, member m WHERE p.p_no = s.p_no AND m.id = s.id AND no=?";
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setNo(rs.getInt("no"));
				vo.setP_no(rs.getInt("p_no"));
				vo.setId(rs.getString("id"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_thumbs(rs.getString("p_thumbs"));				
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
		
		return insertCnt;
	}

	// 스크랩 갯수
	@Override
	public int getScrapCnt() {
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM scrap";
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
	
	// 예약 정보 리스트 로딩
	public ArrayList<OrderVO> getScrapList(int start, int end) {
	// 1. 큰 바구니 선언
	ArrayList<OrderVO> dtos = null;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	try {
		conn = datasource.getConnection();
		String sql = "SELECT * FROM(SELECT no, p_no, p_name, p_thumbs, id, rNum FROM (SELECT * FROM spm_view)) WHERE rNum >= ? AND rNum <= ?";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, start);
		pstmt.setInt(2, end);
		
		rs = pstmt.executeQuery();
		
		if(rs.next()) {
			
			// 2. 큰 바구니 생성(dtos)
			dtos = new ArrayList<OrderVO>(end - start + 1);
			do {
				// 3. 작은바구니 생성
				OrderVO vo = new OrderVO();
				
				// 4. 게시글 1건을 읽어서 rs를 작은 바구니(vo)에 담는다.
				vo.setNo(rs.getInt("no"));
				vo.setP_no(rs.getInt("p_no"));
				vo.setId(rs.getString("id"));
				vo.setP_name(rs.getString("p_name"));
				vo.setP_thumbs(rs.getString("p_thumbs"));
				
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

	// 스크랩 삭제
	@Override
	public int ScrapDelete(int p_no) {
		int deleteCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = null;
		
		try {
			conn = datasource.getConnection();

			sql = "DELETE scrap WHERE p_no=?";

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