<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- post 방식으로 요청했을 때 한글 깨짐 방지를 위해 아래문장 적음 -->
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="bean" class="pack.board.BoardFormBean" />
<!-- 데이터 값 설정(set)하고 객체를 생성함(useBean) -->
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr"></jsp:useBean>
<%
bean.setBip(request.getRemoteAddr());// 클라이언트에 ip주소가 들어감(사용자 컴 주소값이 반환된걸 Bip(등록일)에 담아준다)
bean.setBdate();// 현재 년월일이 보여짐 
int newNum = boardMgr.currentMaxNum() + 1; // 새로운 작성글에 번호를 부여(가장큰번호에 +1를 한거임)
//새로 작성한 글에 번호를 부여하기 위해 bean 객체의 num과 gnum 속성을 설정
bean.setNum(newNum); 
bean.setGnum(newNum);
boardMgr.saveData(bean);
//작업 완료 후 boardlist.jsp Redirect 방식으로 페이지로 이동
response.sendRedirect("boardlist.jsp?page=1");

%>