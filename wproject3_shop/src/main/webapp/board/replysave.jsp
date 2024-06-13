<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 비즈니스 로직을 가지고 있는 부분임 -->
<% request.setCharacterEncoding("utf-8");  %>
<jsp:useBean id="bean" class="pack.board.BoardFormBean" />
<jsp:setProperty property="*" name="bean"/>
<jsp:useBean id="boardMgr" class="pack.board.BoardMgr" />
<%
String spage = request.getParameter("page"); // BoardFormBean에 없으니까 여기서 함

int num = bean.getNum();
int gnum = bean.getGnum();
int onum = bean.getOnum() + 1; // 댓글이므로 1증가
int nested = bean.getNested() + 1;

// 같은 그룹 내에서 새로운 댓글의 onum보다 크거나 같은 댓글 입력 전에 먼주 수정하기.
// 작으면 수정하지 않음
boardMgr.updateOnum(gnum, onum); // onum  갱신

// 댓글 저장
bean.setOnum(onum); // 바뀐 값을 다시 집어 넣어야되
bean.setNested(nested);
bean.setBip(request.getRemoteAddr());
bean.setBdate();
bean.setNum(boardMgr.currentMaxNum() + 1); // 댓글(새글) 번호(num)

boardMgr.replySave(bean);

response.sendRedirect("boardlist.jsp?page=" + spage);


%>