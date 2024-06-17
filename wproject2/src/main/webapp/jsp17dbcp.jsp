<%@page import="pack.order.SangpumDto"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="g con" class="w pack.order.ConnPooli" scope="page"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function funcUp(){
	//alert("a"); 수정
	let code = prompt("수정할 코드 입력", "");
	if(code !== "" && code !== null){//코드가 공백이 아니거나 null이 아닌 경우
		location.href="jsp17up.jsp?code=" +code;
	}
}
function funDel(){
	//alert("b"); 삭제
	let code = prompt("삭제할 코드 입력", "");
	if(code !== "" && code !== null){//코드가 공백이 아니거나 null이 아닌 경우
		if(confirm("정말 삭제할까요?") === true){// 삭제할때에는 한번 물어봐줘 
			location.href="jsp17del.jsp?code=" +code;
			
		}
	}
}

</script>
</head>
<body>
<h2>* 상품 정보(DBCP 사용) *</h2>
클라이언트와 서버 사이드인 웹 어플리케이션에서, 사용자의 요청에 따라 Connection이 생성된다면 수 많은 사용자가 요청을 했을 때 서버에 과부하가 걸리게 됩니다.
이러한 상황을 예방하기 위해 미리 일정 갯수의 Connection 객체를 만들어 Pool에 저장을 하고, 사용자의 요청이 발생하면 Connection을 제공하고 사용자와의 연결이 종료된다면 Pool에 다시 반환하여 보관하는 것을 의미합니다.
<hr>
<a href="jsp17ins.html">상품 추가</a>&nbsp;&nbsp;
<a href="javascript:funcUp()">수정</a>&nbsp;&nbsp;
<a href="javascript:funDel()">삭제</a>
<br><br>
<table border="1">
	<tr>
		<th>코드</th><th>품명</th><th>수량</th><th>단가</th>
	</tr>
<%
ArrayList<SangpumDto> slist = (ArrayList)connP.getDataAll();
//out.print(slist.size());
for(SangpumDto s:slist){
	%>
	<tr>
		<td><%=s.getCode() %></td>
		<td><%=s.getSang() %></td>
		<td><%=s.getSu() %></td>
		<td><%=s.getDan() %></td>
	</tr>
	<%
}

%>
</table>

</body>
</html>