<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BhMemberUpdateConfirm</title>
</head>
<body>
<div class="container">
	<div class="row">
	<span class="fs-2 fw-semibold my-4">행동교정 신청 수정이 완료되었습니다.</span>
	<p>행동교정 신청 목록 페이지로 이동합니다.</p>
	<button type="submit" class="btn btn-outline-secondary"
		onclick="location.href='/school/BHCorrection/Member/BhMemberList'">확인</button>
	</div>
</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>