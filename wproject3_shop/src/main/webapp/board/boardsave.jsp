<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="pack.board.BoardFormBean" />
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr"></jsp:useBean>
<%
bean.setBip(request.getRemoteAddr());// 클라이언트에 ip주소가 들어감
bean.setBdate();// 현재 년월일이 보여짐 
int newNum = boardMgr.currentMaxNum() + 1; // 가장큰번호에 +1를 한거임
bean.setNum(newNum); 
bean.setGnum(newNum);

boardMgr.saveData(bean);
response.sendRedirect("boardlist.jsp?page=1");

%>