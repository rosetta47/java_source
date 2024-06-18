<%@page import="pack.order.OrderBean"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.Hashtable"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session" />
<jsp:useBean id="orderMgr" class="pack.order.OrderMgr" />
<jsp:useBean id="productMgr" class="pack.product.ProductMgr" />

<%
Hashtable hCart = cartMgr.getCartList();

Enumeration enu = hCart.keys();

if(hCart.isEmpty()){
%>
	<script>
		alert("주문 내역이 없습니다");
		location.href="orderlist.jsp";
	</script>

<%	
}else{
	while(enu.hasMoreElements()){ // 일단 자료를 읽어옴
		OrderBean orderBean = (OrderBean)hCart.get(enu.nextElement());
		orderMgr.insertOrder(orderBean);    // insertOrder을 만들어서 주문 정보를 DB에 저장함
		productMgr.reduceProduct(orderBean);// 주문 수량 만큼 재고량 빼기
		cartMgr.deleteCart(orderBean); 
		
	}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
%>
	<script>
		alert("주문 처리가 잘 되었습니다.\n고객님 감사합니다");
		location.href="orderlist.jsp"; // 학습하는 거라서 orderlist로 가는 거임 다른 페이지로 가도 되
	</script>
<%
}
%>