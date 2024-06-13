<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="pack.board.BoardFormBean" />
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr" />

<%
// 수정 작업
// 수정 작업 후에 원래 페이지로 돌아가기 위해 현재 페이지 번호를 가져오는 역할
String spage = request.getParameter("page");//page는 못들어오니 데이터값을 받는다.

boolean b = boardMgr.checkPass(bean.getNum(), bean.getPass()); // 비번을 비교

if(b){
	boardMgr.saveEdit(bean);
	//spage 변수에 저장된 현재 페이지 번호를 이용한다.
	// 수정 작업을 완료 후에 원래 페이지로 돌아갈수 있도록 URL을 생섯
	response.sendRedirect("boardlist.jsp?page=" + spage);
}else{
%>
	<script>
		alert("비밀번호 불일치!");
		history.back();
	</script>
<%		
}
%>
