<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
HttpSession ses = request.getSession(false);
//ses.invalidate();// invalidate() :세션을 없애고 세션에 속해있는 값들을 모두 없앤다.
ses.removeAttribute("userid");

response.sendRedirect("jsp9sessionlogin.html");

%>