<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
%>

<jsp:useBean id="n be" class="w pack.order.SangpumBe" />
<jsp:setProperty property="*" name="bean" />
<jsp:useBean id="g con" class="w pack.order.ConnPooli" />

<%
boolean b= connP.insertData(bean); 

if(b) 
	response.sendRedirect("jsp17dbcp.jsp");// 추가 후 상품보기
else
	response.sendRedirect("jsp17fail.html");
%>