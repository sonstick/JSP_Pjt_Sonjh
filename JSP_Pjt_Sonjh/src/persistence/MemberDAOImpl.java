package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {

	private static MemberDAOImpl instance = new MemberDAOImpl();
	public static MemberDAOImpl getInstance() {
		return instance;
	}

	// 커넥션 풀 객체를 보관
	DataSource datasource;

	// 생성자
	private MemberDAOImpl() {
		try {
			/*
			 * dbcp(DataBase Connection Pool) : 설정을 읽어서 컨넥션을 발급받겠다.
			 * 1. Context : Server > context.xml 파일의 resource를 분석할 객체
			 */
			Context context = new InitialContext();

			/*
			 * 2. context.xml 검색(lookup)시 resource name으로 찾겠다.(컨넥션풀 name : jdbc/Oracle11g)
			 * 3. db서버가 startup시 이미 컨넥션이 50개 만들어진 상태
			 * 4. DataSource에 dbcp 설정된 정보를 읽어들여서 담는다.
			 * 5. 아래 각 메소드에서 datasource.getConnection()시 50개 중 1개 컨넥션을 받고
			 * 6. 사용이 끝나면 finally에서 conn.close() 해서 반납
			 */
			datasource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g_JSP_Pjt_Sonjh");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	// 로그인
	@Override
	public int idPwdCheck(MemberVO vo) {
		int selectCnt = 0;
		
		String strId = (String)vo.getId();
		String strPwd = (String)vo.getPwd();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM member WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);

			rs = pstmt.executeQuery();

			// 로그인한 id에 해당하는 데이터가 있고
			if(rs.next()) {
				vo.setId(rs.getString("id"));
				/*vo.setPwd(rs.getString("pwd"));*/
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setAuth(rs.getString("auth"));
				vo.setMember_image(rs.getString("member_image"));
				if (strPwd.equals(rs.getString("pwd"))) {
					// 비밀번호가 일치하면 selectCnt = 1
					// 비밀번호가 일치하지 않으면 selectCnt = -1
					System.out.println("비밀번호가 일치함" + selectCnt);

					sql = "SELECT * FROM member WHERE id=? AND auth='H'";
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, strId);

					rs.close();
					rs = pstmt.executeQuery();

					if(rs.next()) {
						selectCnt = 1;
						System.out.println("관리자로 로그인됨" + selectCnt);
					} else {
						selectCnt = 2;
						System.out.println("방문자로 로그인됨" + selectCnt);
					}
				} else {
					selectCnt = -1;
					System.out.println("비밀번호가 일치하지 않음" + selectCnt);
				}	
			} else {
				// 로그인을 시도한 id에 해당하는 데이터가 없으면 selectCnt = 0
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
	
	// 문의사항 비밀번호 확인
	@Override
	public int invoiceIdPwdCheck(MemberVO memVo) {
		int selectCnt = 0;
		
		String strId = (String)memVo.getId();
		String strPwd = (String)memVo.getPwd();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM member WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);

			rs = pstmt.executeQuery();

			// 로그인한 id에 해당하는 데이터가 있고
			if(rs.next()) {
				memVo.setId(rs.getString("id"));
				/*vo.setPwd(rs.getString("pwd"));*/
				memVo.setName(rs.getString("name"));
				memVo.setEmail(rs.getString("email"));
				memVo.setPhone(rs.getString("phone"));
				memVo.setAddress(rs.getString("address"));
				memVo.setReg_date(rs.getTimestamp("reg_date"));
				memVo.setAuth(rs.getString("auth"));
				memVo.setMember_image(rs.getString("member_image"));
				if (strPwd.equals(rs.getString("pwd"))) {
					// 비밀번호가 일치하면 selectCnt = 1
					// 비밀번호가 일치하지 않으면 selectCnt = -1
					System.out.println("비밀번호가 일치함" + selectCnt);

					sql = "SELECT * FROM member WHERE id=? AND auth='H'";
					pstmt.close();
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, strId);

					rs.close();
					rs = pstmt.executeQuery();

					if(rs.next()) {
						selectCnt = 1;
						System.out.println("관리자로 로그인됨" + selectCnt);
					} else {
						selectCnt = 2;
						System.out.println("방문자로 로그인됨" + selectCnt);
					}
				} else {
					selectCnt = -1;
					System.out.println("비밀번호가 일치하지 않음" + selectCnt);
				}	
			} else {
				// 로그인을 시도한 id에 해당하는 데이터가 없으면 selectCnt = 0
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

	// id 중복 확인
	@Override
	public int idCheck(String strId) {

		int selectCnt = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection(); // 컨넥션을 한개 읽어오겠다.
			String sql = "SELECT * FROM member WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);

			rs = pstmt.executeQuery();
			if(rs.next()) {
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

	// 회원가입
	@Override
	public int insertMember(MemberVO vo) {
		int insertCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "INSERT INTO member(id, pwd, name, email, phone, address, reg_date) VALUES (?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getPhone());
			pstmt.setString(6, vo.getAddress());
			pstmt.setTimestamp(7, vo.getReg_date());
			
			insertCnt = pstmt.executeUpdate();
			
			sql = "SELECT * FROM member WHERE id=?";
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setAuth(rs.getString("auth"));
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

	// 회원정보 - 정보조회
	@Override
	public MemberVO selectMember(MemberVO vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = datasource.getConnection();
			String sql = "SELECT * FROM member WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());

			rs = pstmt.executeQuery();

			// 로그인한 id에 해당하는 데이터가 있다면
			if(rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setAuth(rs.getString("auth"));
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

		return vo;
	}

	// 회원정보 - 정보수정
	@Override
	public int updateMember(MemberVO vo) {
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE member SET pwd = ?, email = ?, phone = ?, address = ?, member_image = ? WHERE id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getMember_image());
			pstmt.setString(6, vo.getId());
			
			updateCnt = pstmt.executeUpdate();
			
			sql = "SELECT * FROM member WHERE id=?";
			pstmt.close();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo.setId(rs.getString("id"));
				vo.setPwd(rs.getString("pwd"));
				vo.setName(rs.getString("name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setAuth(rs.getString("auth"));
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
		
		return updateCnt;
	}

	// 회원탈퇴
	@Override
	public int deleteMember(String strId, String strPwd) {
		int deleteCnt=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("strId"+strId);
		System.out.println(strPwd);
		
		try {
			conn = datasource.getConnection();
			String sql = "DELETE member WHERE id = ? AND pwd = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			pstmt.setString(2, strPwd);
			
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

	// 회원 수
	@Override
	public int getMemberCnt() {
		int selectCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT COUNT(*) as cnt FROM member";
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

	// 회원 목록
	@Override
	public ArrayList<MemberVO> getMemberList(int start, int end) {
		// 1. 큰 바구니 선언
		ArrayList<MemberVO> dtos = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "SELECT * " + 
					"FROM (SELECT id, pwd, name, email, phone, address, reg_date, auth, member_image, rownum as rNum " + 
							"FROM (SELECT * FROM member ORDER BY reg_date DESC) " +
						") " +
						"WHERE rNum >= ? AND rNum <= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()) {
				
				// 2. 큰 바구니 생성(dtos)
				dtos = new ArrayList<MemberVO>(end - start + 1);
				do {
					
					// 3. 작은바구니 생성
					MemberVO vo = new MemberVO();
					
					// 4. 게시글 1건을 읽어서 rs를 작은 바구니(vo)에 담는다.
					vo.setId(rs.getString("id"));
					vo.setPwd(rs.getString("pwd"));
					vo.setName(rs.getString("name"));
					vo.setEmail(rs.getString("email"));
					vo.setPhone(rs.getString("phone"));
					vo.setAddress(rs.getString("address"));
					vo.setReg_date(rs.getTimestamp("reg_date"));
					vo.setAuth(rs.getString("auth"));
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
	
	// 관리자 - 회원탈퇴
	@Override
	public int adminDeleteMember(String strId) {
		int deleteCnt=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("strId"+strId);
		
		try {
			conn = datasource.getConnection();
			String sql = "DELETE member WHERE id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			
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
	
	// 관리자 - 정보수정
	@Override
	public int adminUpdateMember(MemberVO vo) {
		int updateCnt = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = datasource.getConnection();
			String sql = "UPDATE member SET pwd = ?, email = ?, phone = ?, address = ? WHERE id = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getPwd());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getId());
			
			updateCnt = pstmt.executeUpdate();
			System.out.println("확인" + updateCnt);
			
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
		
		return updateCnt;
	}
	
}
