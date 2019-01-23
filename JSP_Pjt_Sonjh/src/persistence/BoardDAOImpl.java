package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.InvoiceVO;
import vo.NoticeVO;

public class BoardDAOImpl implements BoardDAO {
	
	DataSource datasource;
	
	private static BoardDAOImpl instance = new BoardDAOImpl();
	public static BoardDAOImpl getInstance() {
		return instance;
	}
	
	private BoardDAOImpl() {
		try {
			Context context = new InitialContext();
			datasource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g_JSP_Pjt_Sonjh");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 공지사항 갯수
	@Override
	public int getNoticeCnt() {
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM notice";
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

	// 공지사항 목록
	@Override
	public ArrayList<NoticeVO> getNoticeList(int start, int end) {
		// 1. 큰 바구니 선언
		ArrayList<NoticeVO> dtos = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT * " + 
					"FROM (SELECT no, country, city, subject, content, readCnt, reg_date, rownum as rNum " + 
							"FROM (SELECT * FROM notice ORDER BY no DESC) " +	// 1. 최신글부터 SELECT
						") " +
						"WHERE rNum >= ? AND rNum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()) {
				
				// 2. 큰 바구니 생성(dtos)
				dtos = new ArrayList<NoticeVO>(end - start + 1);
				do {
					
					// 3. 작은바구니 생성
					NoticeVO vo = new NoticeVO();
					
					// 4. 게시글 1건을 읽어서 rs를 작은 바구니(vo)에 담는다.
					vo.setNo(rs.getInt("no"));
					vo.setSubject(rs.getString("subject"));
					vo.setContent(rs.getString("content"));
					vo.setReadCnt(rs.getInt("readCnt"));
					vo.setReg_date(rs.getTimestamp("reg_date"));
					vo.setCountry(rs.getString("country"));
					vo.setCity(rs.getString("city"));
					
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
	
	// 문의사항 갯수
	@Override
	public int getInvoiceCnt() {
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM invoice";
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
	
	// 문의사항 목록
	@Override
	public ArrayList<InvoiceVO> getInvoiceList(int start, int end) {
		// 1. 큰 바구니 선언
		ArrayList<InvoiceVO> dtos = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT * " + 
					"FROM (SELECT no, name, pwd, subject, content, readCnt, cmt, reg_date, rownum as rNum " + 
							"FROM (SELECT * FROM invoice ORDER BY no DESC) " +
						") " +
						"WHERE rNum >= ? AND rNum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				// 2. 큰 바구니 생성(dtos)
				dtos = new ArrayList<InvoiceVO>(end - start + 1);
				do {
					// 3. 작은바구니 생성
					InvoiceVO vo = new InvoiceVO();
					
					// 4. 게시글 1건을 읽어서 rs를 작은 바구니(vo)에 담는다.
					vo.setNo(rs.getInt("no"));
					vo.setName(rs.getString("name"));
					vo.setPwd(rs.getString("pwd"));
					vo.setSubject(rs.getString("subject"));
					vo.setContent(rs.getString("content"));
					vo.setReadCnt(rs.getInt("readCnt"));
					vo.setCmt(rs.getString("cmt"));
					vo.setReg_date(rs.getTimestamp("reg_date"));
					
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
	
	// 공지 조회수 증가
	@Override
	public void addNoticeReadCnt(int no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE notice SET readCnt = readCnt + 1 WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
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
	
	// 공지 상세페이지 조회
	@Override
	public NoticeVO getNotice(int no) {
		NoticeVO vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM notice WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 바구니 생성
				vo = new NoticeVO();
				
				// 게시글 1건을 읽어서 작은 바구니에 컬럼별로 담는다.
				vo.setNo(rs.getInt("no"));
				vo.setSubject(rs.getString("subject"));
				vo.setContent(rs.getString("content"));
				vo.setReadCnt(rs.getInt("readCnt"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setCountry(rs.getString("country"));
				vo.setCity(rs.getString("city"));
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
	
	// 공지 조회수 증가
	@Override
	public void addInvoiceReadCnt(int no) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE invoice SET readCnt = readCnt + 1 WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
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
	
	// 문의 상세페이지 조회
	@Override
	public InvoiceVO getInvoice(int no) {
		InvoiceVO vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM invoice WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// 바구니 생성
				vo = new InvoiceVO();
				
				// 게시글 1건을 읽어서 작은 바구니에 컬럼별로 담는다.
				vo.setNo(rs.getInt("no"));
				vo.setName(rs.getString("name"));
				vo.setPwd(rs.getString("pwd"));
				vo.setSubject(rs.getString("subject"));
				vo.setContent(rs.getString("content"));
				vo.setReadCnt(rs.getInt("readCnt"));
				vo.setCmt(rs.getString("cmt"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
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
	
	// 비밀번호 확인
	@Override
	public int noPwdCheck(int no, String pwd) {
		int pwdCnt = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM invoice WHERE no = ? AND pwd = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pwdCnt = 1;
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
		
		return pwdCnt;
	}

	// 문의 수정 처리
	@Override
	public int updateInvoice(InvoiceVO vo) {
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE invoice SET subject = ?, content = ? WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());
			
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
	
	// 공지 수정 처리
	@Override
	public int updateNotice(NoticeVO vo) {
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE notice SET subject = ?, content = ? WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getSubject());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNo());
			
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
	
	
	// 공지쓰기 처리
	@Override
	public int insertNotice(NoticeVO vo) {
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = datasource.getConnection();
			
			sql = "INSERT INTO notice(no,country,city,subject,content,readCnt,reg_date) " + 
					"VALUES (notice_seq.nextval,?,?,?,?,0,?)";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getCountry());
			pstmt.setString(2, vo.getCity());
			pstmt.setString(3, vo.getSubject());
			pstmt.setString(4, vo.getContent());
			pstmt.setTimestamp(5, vo.getReg_date());
			
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
	
	// 문의쓰기 처리
	@Override
	public int insertInvoice(InvoiceVO vo) {
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = datasource.getConnection();
			
			sql = "INSERT INTO invoice(no,name,pwd,subject,content,readCnt,reg_date,cmt) " + 
					"VALUES (invoice_seq.nextval,?,?,?,?,0,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getSubject());
			pstmt.setString(4, vo.getContent());
			pstmt.setTimestamp(5, vo.getReg_date());
			pstmt.setString(6, vo.getCmt());
			
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

	// 문의 답변
	@Override
	public int updateCmt(int no, String cmt) {
		int updateCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE invoice SET cmt = ? WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cmt);
			pstmt.setInt(2, no);
			
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

	// 공지 삭제 처리
	@Override
	public int deleteNotice(int no) {
		int deleteCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = null;
		
		try {
			conn = datasource.getConnection();

			sql = "DELETE notice WHERE no=?";

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

	// 문의 삭제 처리
	@Override
	public int deleteInvoice(int no) {
		int deleteCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = null;
		
		try {
			conn = datasource.getConnection();

			sql = "DELETE invoice WHERE no=?";

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
}
