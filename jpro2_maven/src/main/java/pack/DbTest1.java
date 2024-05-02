package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// MariaDB(원격 DB 연동 프로그래밍)
public class DbTest1 {

	private Connection conn; // conn은 하나야
	private Statement stmt; // sql 실행할 수 있음: Statement ,여러개 가능
	private ResultSet rs; // 쿼리의 결과를 받아옴: ResultSet , 여러개 가능

	public DbTest1() {
		// 1. Driver file loading : 마리아db 연결하는법
		try {
			Class.forName("org.mariadb.jdbc.Driver"); // 이걸써서 연결해줘(외우지말고 따라써)
			//
			//
		} catch (Exception e) {
			System.out.println("로딩 실패 : " + e);
			return; // 빠져나가
		}
		// 2. DB server와 연결 :마리아db에 있는 자료와 연결하는 법
		try {
			String url = "jdbc:mariadb://localhost:3306/test"; // 3306(포트번호)
			conn = DriverManager.getConnection(url, "root", "123"); // 원래는 "root", "123"를 잠가서 파일 만들어서 사용
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}

		//3. 연결되 DB server에 SQL 실행 : 만들어 놓은 쿼리 갖고오기(select * from sangdata)
		try {
			// sangdata 테이블 자료 읽기
			stmt = conn.createStatement(); // conn에 stmt를 인스턴스함
			
		//	rs = stmt.executeQuery("select * from sangdata");
			rs = stmt.executeQuery("select code, sang, su, dan from sangdata"); // 원래는 칼람명을 다 써주는 게 좋아
		//	rs.next(); // 커서 이동 시켜줘야되 (레코드 포인터) 이클립스는 한행씩 읽음
		//	System.out.println(rs.next()); // true : 그 다음 자료가 있다라는 의미
		//	System.out.println(rs.getString("sang"));
			while(rs.next()) { // 자료가 있는 동안 반복한다 true
				String code = rs.getString("code"); // 타입 맞춰줘야되
				String sangname = rs.getString("sang");
				int su = rs.getInt("su");
				int dan = rs.getInt("dan");
				System.out.println(code + " " + sangname + " " + su + " " + dan);
			}
		
			System.out.println("-----------"); 	// 줄 바꾸고 다시 설명
			rs.close(); // resultset를 닫고, 아래에서 다시 시작함
			
			rs = stmt.executeQuery("select code as 코드, sang as 상품명, su, dan from sangdata");
					while(rs.next()) { // 자료가 있는 동안 반복한다 true
						String code = rs.getString("코드"); // 타입 맞춰줘야되
						String sangname = rs.getString("상품명");
						int su = rs.getInt(3); // 컬럼명은 순서가 1부터 부여됨, 별명 쓰거나 숫자 쓰거나 원래 칼럼 쓰기
						int dan = rs.getInt("dan");
						System.out.println(code + " " + sangname + " " + su + " " + dan);
					}
			
			
		//	String sql =  "select count(*) as 건수 from sangdata"; // 별명 건수 씀
			String sql =  "select count(*)  from sangdata";
			rs = stmt.executeQuery(sql); // 만들어놓은 sql를 rs에 집어넣고
			rs.next();
		//	System.out.println("건수 : " + rs.getString("건수")); // as 건수 같은 느낌(별명)
		//	System.out.println("건수 : " + rs.getString("count(*)"));
			System.out.println("건수 : " + rs.getString(1)); // 안에 칼럼이 길어질수 있으니까 숫자 순서대로 쓸수 있다
		} catch (Exception e) {
			System.out.println("처리 실패 : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}

	public static void main(String[] args) {
		new DbTest1();

	}

}
