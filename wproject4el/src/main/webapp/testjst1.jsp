<%@page import="pack.Student"%>
<%@page import="pack.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
전통적인 방식 : 안녕 <%=request.getAttribute("irum") %>
<%
out.println(request.getAttribute("irum"));
%>
<br>
EL 방식 : 반가워 ${irum} ${requestScope.irum} 
<br><br>
전통적인 방식 :  
<% Person p = (Person)request.getAttribute("person"); %>
<%=p.getName() %>
			
<% Student s = (Student)request.getAttribute("student"); %>
<%=s.getAge() %>
<br>
EL 방식 : ${person.name} ${student.age}
<br><br>
동물 : ${animal[0]} ${animal[1]} ${animal["2"]}
<br><br>
<c:if test="${list !=null}">
	<c:forEach var="a" items="${list}">
	 ${a[0]} , ${a[1]}, ${a[2]}
	</c:forEach>
</c:if>
<br>
<c:if test="${list !=null}">
	<c:forEach var="a" items="${list}">
		<c:forEach var="b" items="${a}">
			${b}
		</c:forEach>
		<br>
	</c:forEach>
</c:if>
<br>
<c:choose>
	<c:when test="${list eq null}">자료가 없음</c:when>
	<c:otherwise>자료있음</c:otherwise>
</c:choose>

<hr>
예외 처리<br>
<c:catch var="myErr">
	<%
	int a = 10 / 0;
	out.println("a : " + a); //에러 발생시 나오는 변수 선언:myErr
	%>
</c:catch>
<c:if test="${myErr != null }">
	에러 발생 : ${myErr.message} <!-- 결과값 : 에러 발생 : / by zero -->
</c:if>
<hr>
계속
<br>다른 문서 포함<br>
inclue 지시어 사용 : <%@include file="poham.jsp" %>
<br>
jstl 사용 : <c:import url="poham.jsp" />
<hr>
<%--  
<c:import url="https://www.daum.net" />
--%>

<c:set var="url" value="https://www.naver.com" />
<c:import url="${url}" var="u" />
<c:out value="${url}"></c:out>
<c:out value="${u}" />



</body>
</html>