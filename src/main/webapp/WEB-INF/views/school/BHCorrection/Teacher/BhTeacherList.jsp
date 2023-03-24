<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width" initial-scale"="1">
<link rel="stylesheet"
	href="/WEB-INF/views/school/BHCorrection/bhCorrection.css">
<title>BhTeacherList</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table table-striped my-5"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="5"
							style="background-color: #eeeeee; text-align: center;">내가
							담당한 행동교정 훈련 목록</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th style="background-color: #eeeeee; text-align: center;">글
							번호</th>
						<th style="background-color: #eeeeee; text-align: center;">제목</th>
						<th style="background-color: #eeeeee; text-align: center;">신청자</th>
						<th style="background-color: #eeeeee; text-align: center;">담당
							선생님</th>
						<th style="background-color: #eeeeee; text-align: center;">훈련
							결정 날짜</th>
					</tr>
					<c:set var="num" value="${page.total-page.start+1 }"></c:set>
					<c:forEach var="btl" items="${bhTeacherList}">
						<tr>
							<td scope="row">${btl.rn}</td>
							<%-- <td>${btl.appliNo }</td> --%>
							<td><a
								href="/school/BHCorrection/Teacher/BhAllSelect?appliNo=${btl.appliNo}">${btl.title }</td>
							<td>${btl.memberName }</td>
							<td>${btl.tname }</td>
							<td>${btl.startDate }~${btl.finishDate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<button type="button" class="btn btn-outline-secondary"
					onclick="location.href='/school/BHCorrection/Teacher/BhAllList'">
					회원님들의 행동교정 신청 목록 보기</button>

		</div>
	</div>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
				<li class="page-item"><a class="page-link"
					href="/school/BHCorrection/Teacher/BhTeacherList?currentPage=${i}">${i}</a></li>
			</c:forEach>
		</ul>
	</nav>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>