<%@page import="java.util.Map"%>
<%@page import="pack.product.ProductDto"%>
<%@page import="pack.product.ProductMgr"%>
<%@page import="pack.order.OrderBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session" />
<jsp:useBean id="productMgr" class="pack.product.ProductMgr" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 주문</title>
<link href="../css/style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../js/script.js"></script>
</head>
<body>
<h2> ** 장바구니 목록 **</h2>
<%@ include file="guest_top.jsp" %>
<table>
	<tr style="background-color: orange;">
		<th>주문상품</th><th>가격(소계)</th><th>수량</th><th>수정/삭제</th><th>조회</th>
	</tr>
<%
int totalPrice = 0; // 총계
// Hashtable hCart = cartMgr.getCartList();
Hashtable<String, OrderBean> hCart = (Hashtable<String, OrderBean>)cartMgr.getCartList();

// if(hCart.isEmpty(){ // 이렇게 써도 되
if(hCart.size() == 0){ // 카드에 아무것도 안담겨 있다면
%>
	<tr>
		<td colspan="5">주문 건수가 없어요</td>
	</tr>
<%
}else{
	
	/* 옛날 방식임
	//Enumeration : 반복처리 할 때 씀 (Map 타입의 컬렉션 읽어서 반복처리)
}
	Enumeration enu = hCart.keys();
	while(enu.hasMoreElements()){
		OrderBean orderbean = (OrderBean)hCart.get(enu.nextElement()); // 하나씩 뽑아내
		ProductDto product = productMgr.getProduct(orderbean.getProduct_no()); // 상품정보 읽음
		int price = Integer.parseInt(product.getPrice()); // string price라서 형변환
		int quantity = Integer.parseInt(orderbean.getQuantity());
		int subTotal = price * quantity; // 소계
		totalPrice += subTotal; // 총계
		*/
		
		// Map.Entry를 이용하면 Map에 저장된 모든 key-value 쌍을 
		// 각각의 key-value를 갖고 있는 하나의 객체로 얻을 수 있다. (최신 방식임)
		for(Map.Entry<String, OrderBean> entry : hCart.entrySet()){
			OrderBean orderbean = entry.getValue(); // value값을 가져야되서 
			
			ProductDto product = productMgr.getProduct(orderbean.getProduct_no()); // 상품정보 읽음
			int price = Integer.parseInt(product.getPrice()); // string price라서 형변환
			int quantity = Integer.parseInt(orderbean.getQuantity());
			int subTotal = price * quantity; // 소계
			totalPrice += subTotal; // 총계
		
%>
<form action="cartproc.jsp" method="get">
	<input type="hidden" name="flag"> <!-- 수정 삭제 버튼 연결 cart에서 -->
	<input type="hidden" name="product_no" value="<%=product.getNo()%>">
	
	<tr>
		<td><%=product.getName() %></td>
		<td><%=subTotal %></td>
		<td><!-- 장바구니에서 수량 수정할 수 있게 -->
			<input type="text" name="quantity" size="5" value="<%=quantity %>"
			style="text-align: center;">
		</td>
		<td>
			<input style="background-color: aqua;" type="button" value="수정" onclick="cartUpdate(this.form)">
			<input style="background-color: aqua;" type="button" value="삭제" onclick="cartDelete(this.form)">
		</td>
		<td>
			<a href="javascript:productDetail_guest('<%=product.getNo() %>')">상세보기</a>
		</td>		
	</tr>
</form>

<%				
	}
%>
	<tr>
		<td colspan="5">
			<br>
			<b>총 결제 금액 : <%=totalPrice %>원</b>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="orderproc.jsp">[주문하기]</a>
		</td>
	</tr>
<%
}
%>	
</table>
<%@ include file="guest_bottom.jsp"%>

<form action="productdetail_g.jsp" name="detailFrm">
	<input type="hidden" name="no">
</form>
</body>
</html>