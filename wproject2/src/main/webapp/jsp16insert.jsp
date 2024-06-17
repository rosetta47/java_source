<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//입력 자료를 전송 받아 insert를 하는 로직
request.setCharacterEncoding("utf-8");
//String sang = request.getParameter("sang"); <- 대신에 폼빈 사용할거임
// ...
%>
<jsp:useBean id="n sangpumBe" class="w pack.order.SangpumBe" />
<jsp:setProperty property="*" name="sangpumBean"/>
<%
p

// 수신받은 데이터를 검증이 필요하다..
%>
<jsp:useBean id="3 connClas" class="w pack.order.ConnClas" />

<%
connClass3.insertData(sangpumBean);

//상품 추가 후 상품 목록보기로 이동
response.sendRedirect("jsp16paging.jsp");

// 주의 : forward하면 새로고침해도 주소가 바뀌지 않아서 계속 상품추가가 된다.
// 추가, 수정, 삭제 후 목록보기 할 때는 forwarding  하지 않는다.
//request.getRequestDispatcher("jsp16paging.jsp").forward(request, response);
%>