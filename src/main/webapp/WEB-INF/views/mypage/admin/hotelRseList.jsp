<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ include file="/WEB-INF/views/include/adminSidebar.jsp" %>le="/WEB-INF/views/include/sidebar.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">

	#memberResList {
		margin-left: 400px;
	
	}
</style>
<body>

 <div id="memberResList">
  <h1>관리자 호텔관리 페이지 </h1>
  <table border="1">
  	<tr><th >예약번호</th><th>회원아이디</th><th>애완견명</th><th>방번호</th><th>예약일</th></tr>
  	<c:forEach var="reservation" items="${memberResList}">
  	<tr><td>	
  		<a href="/mypage/admin/memberResDetail?resNo=${reservation.resNo}">${reservation.resNo}</a></td>
  		<td>${reservation.id}</td>
  		<td>${reservation.dogName}</td>
  		<td>${reservation.roomNum}</td>
  		<td>
	  		${fn:substring(reservation.resStartdate , 0 , 10)}~
	  		${fn:substring(reservation.resEnddate , 0 , 10)}
  		</td>
    </tr>
  	</c:forEach>
  </table>
  </div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>