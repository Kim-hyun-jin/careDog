<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ include file="/WEB-INF/views/include/adminSidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

	#container {
		margin-left: 400px;
	
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="container">
	<h1>호텔 결제 관리 페이지 </h1>
	<table border="1">
		<tr><th>예약번호</th><th>승인번호</th><th>결제금액</th>
			<th>결제자명</th><th>결제자연락처</th><th>결제일</th><th>결제상태</th></tr>
		<c:forEach var="resPay" items="${resPayList}">
		<tr><td>${resPay.resNo}</td><td>${resPay.orderNum}</td><td>${resPay.resPrice}</td>
			<td>${resPay.memberName}</td><td>0${resPay.memberCall}</td><td>${fn:substring(resPay.orderDate , 0 , 10)}</td><td>${resPay.orderStatus}</td></tr>
		</c:forEach>
	</table>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>