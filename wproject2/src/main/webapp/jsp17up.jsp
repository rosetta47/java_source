<%@page import="pack.order.SangpumDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
    p
     String code = request.getParameter("code")
    %>

<jsp:useBean id="g con" class="w pack.order.ConnPooli"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
SangpumDto dto = connP.updateData(code);//updateData만들어야되
if(dto == null){
%>
<script type="text/javascript">
	alert("등록된 상품 코드가 아닙니다.\n수정 불가");
	//history.back();//돌아가(이전으로)
	location.href="jsp17dbcp.jsp";// 돌아가는 페이지 내가 정해줌
</script>
<%
	return;
}

%>

** 상품 수정 **<br>
<form action="jsp17upok.jsp" method="post">
코드 : <%=dto.getCode() %><br>  <!-- 코드는 수정 대상이 아니야 그냥 보여주는 거야 -->
<input type="hidden" name="code" value="<%=dto.getCode() %>">
품명 : <input type="text" name="sang" value="<%=dto.getSang() %>"><br>
수령 : <input type="text" name="su" value="<%=dto.getSu() %>"><br>
단가 : <input type="text" name="dan" value="<%=dto.getDan() %>"><br>
<br>
<input type="submit" value="자료 수정"><!-- 코드는 말고 품명, 수령, 단가만 넘어감 코드는 hidden을 해줌으로써 넘어감-->
<input type="button" value="수정 취소" onclick="javascript:location.href='jsp17dbcp.jsp'">
</form>
</body>
</html>