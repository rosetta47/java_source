package pack;
// 풀이 : 스캐너 위치 오류
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DbTest3 {

	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	public DbTest3() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");

		} catch (Exception e) {
			System.out.println("로딩 실패 : " + e);
			return;
		}
		try {
			String url = "jdbc:mariadb://localhost:3306/test";
			conn = DriverManager.getConnection(url, "root", "123");
		} catch (Exception e) {
			System.out.println("연결 실패 : " + e.getMessage());
		}

		// String sql2 = "";

		try {

			Scanner sc = new Scanner(System.in);
			System.out.println("부서입력 : ");
			int num = sc.nextInt();
		//	sc.close();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT jikwon_no, jikwon_name, jikwon_jik, jikwon_pay FROM jikwon INNER JOIN buser ON jikwon.buser_num = buser.buser_no");

			while (rs.next()) { // 자료가 있는 동안 반복한다 true
				int jikwon_no = rs.getInt("jikwon_no"); // 타입 맞춰줘야되
				String jikwon_name = rs.getString("jikwon_name");
				String jikwon_jik = rs.getString("jikwon_jik");
				int jikwon_pay = rs.getInt("jikwon_pay");

				System.out.println(jikwon_no + " " + jikwon_name + " " + jikwon_jik + " " + jikwon_pay);
			}


			String sql = "select count(*) as 건수 from jikwon where buser_num = " + num;
			rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println("건수 : " + rs.getString("건수"));

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			
			} catch (Exception e2) {
				// err
			}

		}
	}

	public static void main(String[] args) {
		// Scanner sc = new Scanner(System.in);
		// System.out.println("부서입력 : " );
		// int num = sc.nextInt();

		new DbTest3();

	}

}
