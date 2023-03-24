<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BhTeacherDelete</title>
</head>
<body>
	<h1>확정 훈련 취소</h1>
	<p>확정 훈련이 취소되었습니다. 회원님들의 행동교정 신청 목록으로 이동합니다.</p>
	<button type="button" class="btn btn-outline-secondary"
		onclick="location.href='/school/BHCorrection/Teacher/BhTeacherDeleteConfirm'">
		확인</button>
	</td>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>