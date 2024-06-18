package pack.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class ProductMgrex1 {
	
	private Connection conn; 
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public ProductMgrex1() {
		try {
			Context context = new InitialContext();
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria");
			
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : " + e);
		}
	}
	
	public ArrayList<ProductDtoex1> getProductAll(){
		ArrayList<ProductDtoex1> list = new ArrayList<ProductDtoex1>();
		 
		try {
			conn = ds.getConnection();
			String sql = "select * from product";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDtoex1 dto = new ProductDtoex1();
				dto.setProduct_name(rs.getString("product_name"));
				dto.setProduct_pic(rs.getString("product_pic"));
				dto.setProduct_price(rs.getInt("product_price"));
				dto.setProduct_contents(rs.getString("product_contents"));
				dto.setProduct_stock(rs.getInt("product_stock"));
				dto.setProduct_date(rs.getString("product_date"));
				dto.setProduct_category(rs.getString("product_category"));
				dto.setProduct_count(rs.getInt("product_count"));
				list.add(dto);
			
			}
		} catch (Exception e) {
			System.out.println("getProductAll err: " + e);
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
}
