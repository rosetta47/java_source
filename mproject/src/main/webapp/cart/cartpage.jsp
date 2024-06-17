<%@page import="pack.order.ProductMgrex1"%>
<%@page import="pack.order.ProductDtoex1"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
</head>
<body>
	장바구니 화면
	<hr>
	<table border="1">
		<tr style="background-color: pink" width="90%">
			<th>제품번호</th>
			<th>이미지</th>
			<th>제품이름</th>
			<th>수량</th>
			<th>가격</th>
		</tr>

		<% 
		HttpSession session2 = request.getSession();
		ArrayList<ProductDtoex1> plist = ProductMgrex1.getProductAll();
		for (ProductDtoex1 p : plist) {
			//System.out.println(p.getProduct_name());
		%>

		<tr style="text-align: center;">
			<td><input type="checkbox" name="product_name"
				value="<%=p.getProduct_name()%>"></td>
			<td><img src="<%=p.getProduct_pic()%>"> <input type="file"
				name="image" size="30"></td>
			<td><%=p.getProduct_name()%></td>
			<td><input type="number" name="getProduct_quantity"
				value="<%=p.getProduct_count()%>"></td>
			<td><%=p.getProduct_price()%></td>
		</tr>
		<tr>
			<td>주문 수량:</td>
			<td><input type="number" min="1" value="1" name="quantity"
				style="text-align: center; width: 3cm"></td>
		</tr>
		<%
		}
		%>

	</table>
</body>
</html>