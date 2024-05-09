package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DbTest3Ex22222 {
	private  Connection conn;
	private  PreparedStatement pstmt;
	private  ResultSet rs, rs2;
	
	public DbTest3Ex22222() {
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
		try {
			
			Scanner sc = new Scanner(System.in);
			System.out.print("부서명 : ");
			String bname = sc.next();
			String sql = "SELECT jikwon_no as 사번, jikwon_name as 이름,  jikwon_jik as 직급,buser_name as 부서,buser_tel as 전화, jikwon_gen as 성별 FROM jikwon INNER JOIN buser ON jikwon.buser_num = buser.buser_no WHERE buser_name= " + bname;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
	
			System.out.println("사번 이름 직급 부서 전화 성별");
			while(rs.next()) {
				int jikwon_no = rs.getInt("사번");
				String jikwon_name = rs.getString("이름");
				String jikwon_jik = rs.getString("직급");
				String buser_name = rs.getString("부서");
				String buser_tel = rs.getString("전화");
				String jikwon_gen = rs.getString("성별");
				System.out.println(jikwon_no + " " + jikwon_name + " " + buser_name + " " + jikwon_jik + " " + buser_tel + " " + jikwon_gen);
				
			}
				String sql2 = "select count(*) as 건수, avg(jikwon_pay) as 평균연봉 from jikwon group by jikwon_pay where buser_name = " + bname;
				pstmt = conn.prepareStatement(sql2);
				rs2 = pstmt.executeQuery();
				rs2.next();
				System.out.println("건수 : " +rs2.getString("건수")+" "+ rs2.getString("평균연봉"));
				
			
		} catch (Exception e) {
			System.out.println("처리 실패 : " + e);
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				
			}
			
		}


	}
	
	public static void main(String[] args) {
		new DbTest3Ex22222();
	}

}
