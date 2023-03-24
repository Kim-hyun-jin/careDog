<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ include file="/WEB-INF/views/include/sidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		

	<h2>알림장 보기</h2> 
	<table>
			<tr><th>글번호</th><td>${dog.noticeNo }</td></tr>
			<tr><th>선생님이름</th><td>${dog.tname }</td></tr>
			<tr><th>회원아이디</th><td>${dog.memberName }</td></tr>
			<tr><th>제목</th><td>${dog.title  }</td></tr>
			<tr><th>내용</th><td>${dog.content  }</td></tr>
			<tr><th>기분</th><td>${dog.feel  }</td></tr>
			<tr><th>건강</th><td>${dog.health  }</td></tr>
			<tr><th>식사량</th><td>${dog.meal }</td></tr>
			<tr><th>배변</th><td>${dog.condition }</td></tr>
			<tr><th>등록일</th><td>${dog.regdate }</td></tr>
			<tr><th>강아지번호</th><td>${dog.dogName }</td></tr>
			
		<tr><td colspan="2">
		    <input type="button" value="목록" 
				onclick="location.href='/school/dogNotice/main'">
			<input type="button" value="수정" 
				onclick="location.href='/school/dogNotice/modFormContent?noticeNo=${dog.noticeNo}'">
			<input type="button" value="삭제" 
				onclick="location.href='delete?empno=${emp.empno}'"></td>
		</tr>
	</table>

</body>
</html>