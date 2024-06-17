<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!--  scope="session" 세션이 살아있는 동안 있어야되니까 꼭 필요 -->
<jsp:useBean id="cartMgr" class="pack.order.CartMgr" scope="session" /> 
<jsp:useBean id="order" class="pack.order.OrderBean" />
<jsp:setProperty property="*" name="order"/>

<%
String orderFlag = request.getParameter("flag"); 
// 구매 목록 보기위한 변수 설정 : 보기, 수정 삭제 판단용 변수

String id = (String)session.getAttribute("idkey");

out.print(order.getProduct_no()+",주문 수량 : "+ order.getQuantity());
%>