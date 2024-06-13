package pack.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class BoardMgr {
		private Connection conn; //DB 연결담당
		private PreparedStatement pstmt;
		private ResultSet rs;
		private DataSource ds;
		
		//페이징 처리용
		private int recTot; // 전체 레코드 수
		private int pList = 8; // 페이지 당 출력수
		private int pageSu; // 전체 페이지 수
		
	public BoardMgr() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}
	
	//public ArrayList<BoardDto> getDataAll(int page){
	public ArrayList<BoardDto> getDataAll(int page, String stype, String sword){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		
		String sql = "select * from board"; //order by gnum desc, onum asc";
		try {
			conn = ds.getConnection();
			
			if(sword == null) { //검색이 없는 경우
				sql += " order by gnum desc, onum asc";
				pstmt = conn.prepareStatement(sql);
			}else { // 검색이 있는 경우
				sql += " where "+ stype + " like ?";
				sql += " order by gnum desc, onum asc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + sword + "%");
			}
			
			rs = pstmt.executeQuery();
			
			// paging 위한 처리
			for(int i=0; i< (page -1)* pList; i++) {
				rs.next(); // 레코드 포인터 이동 흐름 (0, 4, 9,...)
			}
			
			int k= 0;
			while(rs.next() && k <pList) {
				BoardDto dto = new BoardDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setNested(rs.getInt("nested"));
				list.add(dto);
				k++;
			}
		} catch (Exception e) {
			System.out.println("getDataAll err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return list;
	}
	
	// 새 작성글 번호 입력용
	public int currentMaxNum() { // board 테이블의 최대 번호 반납함
		String sql = "select max(num) from board";
		int num = 0; // 자료가 없으면 0을 리턴
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) num = rs.getInt(1); // 자료가 있으면 가장 큰 번호가 나옴
			
		} catch (Exception e) {
			System.out.println("currentMaxNum err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return num;
	}
	
	public void saveData(BoardFormBean bean) { // board insert하는 공간
		String sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,?,?)";
		// 이 쿼리는 board 테이블에 데이터를 삽입하기 위한 insert 문이다.
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0); // readcnt:읽어오는거 없음
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, 0); //onum
			pstmt.setInt(12, 0); //nested
			// 클라이언트로 부터 name의 값을 서버가 전달 받았을 때
			// bean객체의 name값에 전달한다. 이걸 String 문자열로 설정한다는 의미
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("saveData err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public void totalList() { // 전체 레코드 수 구하기
		String sql = "select count(*) from board";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			recTot = rs.getInt(1);
			System.out.println("전체 레코드 수 : " + recTot);
			
		} catch (Exception e) {
			System.out.println("totalList err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public int getPageSu() {//전체 페이지 수 반환
		pageSu = recTot / pList;
		//짜두리 남는 경우에는 페이지수 하나 늘려
		if(recTot % pList >0) pageSu++;
		return pageSu;
	}
	
	public void updateReadcnt(String num) { //조회수 증가
		String sql = "update board set readcnt=readcnt + 1 where num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate(); // update일때
			
		} catch (Exception e) {
			System.out.println("updateReadcnt err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	public BoardDto getData(String num) { //하나만 넘기면 되
		String sql = "select * from board where num=?";
		BoardDto dto = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery(); //select이니까 쿼리로 받아
			if(rs.next()) { //하나만 읽어서 if인데 있으면 true
				//자료가 있어
				dto = new BoardDto(); //dto를 인스턴스 하고
				dto.setName(rs.getString("name"));//아래목록은 개발자 생각에 따라 씀
				dto.setPass(rs.getString("pass"));
				dto.setMail(rs.getString("mail"));
				dto.setTitle(rs.getString("title"));
				dto.setCont(rs.getString("cont"));
				dto.setBip(rs.getString("bip"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
			
			}
			
		} catch (Exception e) {
			System.out.println("getData err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return dto;
	}
	public BoardDto getReplyData(String num) { // 댓글용 데이터
		String sql = "select * from board where num=?";
		BoardDto dto = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery(); 
			
			if(rs.next()) { 
				dto = new BoardDto(); 
				dto.setTitle(rs.getString("title"));
				dto.setGnum(rs.getInt("gnum"));
				dto.setOnum(rs.getInt("onum"));
				dto.setNested(rs.getInt("nested"));
			}
			
		} catch (Exception e) {
			System.out.println("getReplyData err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return dto;
	}
	
	public void updateOnum(int gnum, int onum) { // 댓글용 : onum 갱신
		// 같은 그룹의 레코드는 모두 작업에 참여 - 같은 그룹의 onum 값 갱신
		// 댓글의 onum은 이미 db에 있는 onum 보다 크거나 같은 값을 변경함
		String sql = "update board set onum=onum + 1 where onum >= ? and gnum=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, onum);
			pstmt.setInt(2, gnum);
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("updateOnum err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public void replySave(BoardFormBean bean) {//댓글용 - 저장
		String sql = "insert into board values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0); // readcnt:읽어오는거 없음
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, bean.getOnum()); //onum 받아왔기 때문에 get
			pstmt.setInt(12, bean.getNested()); //nested
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("replySave err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public boolean checkPass(int num, String user_pass) { // 글 수정부분에서 비밀번호 비교용
		boolean b = false;
		
		String sql = "select pass from board where num=?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(user_pass.equals(rs.getString("pass"))) {
					b = true;
				}
			}
			
		} catch (Exception e) {
			System.out.println("checkPass err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return b;
	}
	
	public void saveEdit(BoardFormBean bean) {
		String sql = "update board set name=?,mail=?,title=?,cont=? where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getMail());
			pstmt.setString(3, bean.getTitle());
			pstmt.setString(4, bean.getCont());
			pstmt.setInt(5, bean.getNum());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("saveEdit err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public void delData(String num) {
		String sql = "delete from board where num=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("delData err: " + e);
		} finally {
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
}
