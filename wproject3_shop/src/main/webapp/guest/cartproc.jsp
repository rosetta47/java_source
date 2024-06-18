<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!--  scope="session" 세션이 살아있는 동안 있어야되니까 꼭 필요 -->
<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session" /> 
<jsp:useBean id="order" class="pack.order.OrderBean" />
<jsp:setProperty property="*" name="order"/>

<%
String orderFlag = request.getParameter("flag"); 
// 구매 목록 보기위한 변수 설정 : 보기, 수정 삭제 판단용 변수

String id = (String)session.getAttribute("idkey");//로그인해야지 들어갈 수 있게 해야되서 필요

//out.print(order.getProduct_no()+",주문 수량 : "+ order.getQuantity());
if(id == null){ //로그인을 안했다면 
	response.sendRedirect("../member/login.jsp"); //회원 로그인을 안한경우 
}else{
	if(orderFlag == null){ //빈손이라고 카드에 아무것도 없다면
		// 어떤 상품을 몇개 까지 구했음 -> 누가를 구해야되(id)연결해줘야되
		order.setId(id); // order : id, quantity, product_no가 연결되어 있음
		cartMgr.addCart(order); // cart에 주문 상품 담기
%>
	<script>
	alert("장바구니에 담았습니다");
	location.href="cartlist.jsp"; // cart에 등록된 주문 상품 목록 보기
	</script>
<%		
	}else if(orderFlag.equals("update")){
		order.setId(id);
		cartMgr.updateCart(order);	
%>
	<script>
		alert("장바구니의 내용을 수정했습니다.");
		location.href="cartlist.jsp"; // cart에 등록된 주문 상품 목록 보기
	</script>
<%			
	}else if(orderFlag.equals("del")){
		cartMgr.deleteCart(order);
%>
	<script>
		alert("해당 상품의 주문을 삭제했습니다.");
		location.href="cartlist.jsp"; // cart에 등록된 주문 상품 목록 보기
	</script>
<%	
	}
}

%>