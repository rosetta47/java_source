package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// PreparedStatement : 선처리 방식이 가능하고 SQL문에 입력 적용시 ? 연산자 사용 가능함
public class DbTest6Prepared {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DbTest6Prepared() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
			
			String sql = "";
			
			// 자료추가
	/*		sql = "insert into sangdata values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "10");
			pstmt.setString(2, "신상품");
			pstmt.setInt(3, 12);
			pstmt.setString(4, "5000");
			
			int re = pstmt.executeUpdate();
			System.out.println("insert 반환값 :" + re); */
			
	/*		// 자료 수정
			sql = "update sangdata set sang=?,su=? where code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "아메리카노");
			pstmt.setInt(2, 33);
			pstmt.setInt(3, 10);
			
			int re = pstmt.executeUpdate();
			System.out.println("update 반환값 :" + re); */
			
			// 자료 삭제
			sql = "delete from sangdata where code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 10);
			if(pstmt.executeUpdate() >= 1) {
				System.out.println("삭제 성공");
			} else {
				System.out.println("삭제 실패");
			}
			
			// 전체 자료 읽기
			sql = "select * from sangdata";
			pstmt = conn.prepareStatement(sql); // 여기다가 쿼리문 주면 되
			rs = pstmt.executeQuery(); // 여기()다가는 안써
			
			while(rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) 
				+ " " +rs.getString(3) + " " + rs.getString(4));
			}
			
			System.out.println();
			// 부분 자료 읽기
			String no = "2"; //외부에서 맞았다고 가정
		//	sql = "select * from sangdata where code=" + no; //SQL injection 공격대상이 됨.
		// secure coding guideline에 맞춰서 코딩한다면
			sql = "select * from sangdata where code=?";
			
			pstmt = conn.prepareStatement(sql);  //위에 ?가 있으면 바로 다 받지말고 아래 문장 써야되
			pstmt.setString(1, no); // 첫번째 ?에 no가 대응, 위에 36줄 no랑 타입맞퉈야되
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getString("code") + " " + rs.getString("sang") 
				+ " " +rs.getString("su") + " " + rs.getString("dan"));
			}
			
		} catch (Exception e) {
			System.out.println("err : " + e);
		}
	}
	
	
	public static void main(String[] args) {
		new DbTest6Prepared();

	}

}
