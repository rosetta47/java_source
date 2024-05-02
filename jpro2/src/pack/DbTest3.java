package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DbTest3 {

	private Connection conn;
	private Statement stmt1, stmt2, stmt3;
	private ResultSet rs1, rs2, rs3;

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

			stmt1 = conn.createStatement();
			rs1 = stmt1.executeQuery("select * from jikwon");

			while (rs1.next()) { // 자료가 있는 동안 반복한다 true
				int jikwon_no = rs1.getInt("jikwon_no"); // 타입 맞춰줘야되
				String jikwon_name = rs1.getString("jikwon_name");
				String jikwon_jik = rs1.getString("jikwon_jik");
				int jikwon_pay = rs1.getInt("jikwon_pay");

				System.out.println(jikwon_no + " " + jikwon_name + " " + jikwon_jik + " " + jikwon_pay);
			}

			stmt2 = conn.createStatement();
			rs2 = stmt2.executeQuery("select * from buser");
			while (rs2.next()) {

				String buser_name = rs2.getString("buser_name");

				System.out.println(buser_name);
			}
			
			String sql = "";
			sql = "SELECT jikwon_no FROM jikwon INNER JOIN buser ON jikwon.buser_num = buser.buser_no";
			stmt3 = conn.createStatement();
			rs3 = stmt3.executeQuery(sql); 
		
		
		
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs1 != null)
					rs1.close();
				if (stmt1 != null)
					stmt1.close();
				if (conn != null)
					conn.close();
				if (rs2 != null)
					rs2.close();
				if (stmt2 != null)
					stmt2.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
				// err
			}

		}
	}

	public static void main(String[] args) {
	//	Scanner sc = new Scanner(System.in);
	//	System.out.println("부서입력 : " );
	//	int su = sc.nextInt();
		
		new DbTest3();

	}

}
