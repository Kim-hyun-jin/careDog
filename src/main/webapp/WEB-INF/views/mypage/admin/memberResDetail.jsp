<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<%@ include file="/WEB-INF/views/include/adminSidebar.jsp" %>
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
  <h1>관리자->호텔 예약번호 별 상세페이지 </h1>
  <table border="1">
  	<tr><th>보호자명</th><td>${reservation.memberName}</td></tr> 
  	<tr><th>연락처</th><td>${reservation.memberCall}</td></tr> 
  	<tr><th>주소</th><td>${reservation.memberAddress}</td></tr> 
  	<tr><th>반려견명</th><td>${reservation.dogName}</td></tr>
  	<tr><th>반려견 사이즈</th><td>
  			${reservation.dogSize == '1' ? '소' : '2' ? '중' : '대'}</td></tr>
  	<tr><th>예약시작일</th><td>${reservation.resStartdate}</td></tr>
  	<tr><th>예약종료일</th><td>${reservation.resEnddate}</td></tr>
  	<tr><th>예약번호</th><td>${reservation.resNo}</td></tr>
  	<tr><th>방번호</th><td>${reservation.roomNum}</td></tr>
  	<tr><th>방타입</th><td>${reservation.roomType}</td></tr> 
  	<tr><th>결제금액</th><td>${reservation.resPrice}</td></tr>	
  </table>
  	<input type="button" value="목록" onclick="location.href='/mypage/admin/hotelRseList'">
  </div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>