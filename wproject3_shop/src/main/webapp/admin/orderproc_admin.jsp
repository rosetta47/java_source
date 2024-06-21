<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:useBean id="orderMgr" class="pack.order.OrderMgr" />

<%
String flag = request.getParameter("flag");
String no = request.getParameter("no");
String state = request.getParameter("state");

boolean b = false;

if(flag.equals("update")){ 
	b = orderMgr.updateOrder(no, state); // no, state 변경되니깐 
}else if(flag.equals("delete")){
	b = orderMgr.deleteOrder(no); //no만 지우꺼니까
}else{
	response.sendRedirect("ordermanager.jsp");// ordermanager로 가 update, delete 아닌 경우
}

if(b){
%>	
	<script>
	alert("정상적으로 처리되었습니다");
	location.href="ordermanager.jsp";
	</script>	
<%	
}else{
%>
	<script>
	alert("정상적으로 처리되었습니다");
	location.href="ordermanager.jsp";
	</script>	
<%	
}
%>