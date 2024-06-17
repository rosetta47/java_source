<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8");  %>

<jsp:useBean id="n be" class="w pack.order.SangpumBe" />
<jsp:setProperty property="*" name="bean" />
<jsp:useBean id="g con" class="w pack.order.ConnPooli" scope="page" />

<%
//boolean b = connP.updateDataOk(bean); // bean 가지고 감

if(connP.updateDataOk(bean))
	response.sendRedirect("jsp17dbcp.jsp"); // 수정 후 목록보기
else 
	response.sendRedirect("jsp17fail.html");
%>