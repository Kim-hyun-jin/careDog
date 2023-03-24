<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<meta name="viewport" content="width=device-width" initial-scale="1">
<link rel="stylesheet"
	href="/WEB-INF/views/school/BHCorrection/bhCorrection.css">
<title>BhMemberList</title>
<style type="text/css">
a {
	color: #222;
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">

			<table class="table table-striped my-5"
				style="text-align: center; border: 1px solid #dddddd">
				<thead>
					<tr>
						<th colspan="5"
							style="background-color: #eeeeee; text-align: center;">행동교정
							신청 목록</th>
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
						<th style="background-color: #eeeeee; text-align: center;">확정
							여부</th>
					</tr>
					<c:set var="num" value="${page.total-page.start+1 }"></c:set>
					<c:forEach var="bh" items="${bhList }">
						<tr>
							<td scope="row">${bh.rn}</td>
							<%-- <td>${bh.appliNo }</td> --%>
							<td><a
								href="/school/BHCorrection/Member/BhMemberSelect?appliNo=${bh.appliNo}"
								class="link">${bh.title }</a></td>
							<td>${bh.memberName }</td>
							<td>${bh.tname }</td>
							<td>${bh.status }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<button type="button" class="btn btn-outline-secondary"
				onclick="location.href='/school/BHCorrection/Member/BhMemberInsert';">새로운
				신청하기</button>
		</div>
	</div>
	<nav aria-label="Page navigation example">
		<ul class="pagination justify-content-center">
			<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
				<li class="page-item"><a class="page-link"
					href="/school/BHCorrection/Member/BhMemberList?currentPage=${i}">${i}</a></li>
			</c:forEach>
		</ul>
	</nav>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>