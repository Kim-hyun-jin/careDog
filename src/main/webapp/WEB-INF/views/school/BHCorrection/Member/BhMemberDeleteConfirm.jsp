<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BhMemberDeleteConfirm</title>
</head>
<body>
	<div class="container my-5">
	<c:choose>
		<c:when test="${bhDelAppli >= 1 }">
			<h1>행동교정 신청 취소가 완료되었습니다.</h1>
		</c:when>
		<c:otherwise>
			<h1>행동교정 신청 취소가 실패하였습니다.</h1>
		</c:otherwise>
	</c:choose>
	
	<p>행동교정 신청 목록 페이지로 이동합니다.</p>
	<button type="submit" class="btn btn-outline-secondary"
		onclick="location.href='/school/BHCorrection/Member/BhMemberList'">확인</button>
</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>

</body>
</html>