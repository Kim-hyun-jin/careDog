<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>호텔 예약 내역(관리자)</title>
<link href="/css/mypage/adminRes.css" rel="styleSheet" type="text/css">
<style type="text/css">
.all {
	position: absolute;
	top: 120px;
}
.side {
	float: left;
}
.con {
	float: right;
	margin-left: 100px;
}
</style>
</head>
<body>
	<div class="all">
	
	<div class="side"><%@ include file="/WEB-INF/views/include/adminSidebar.jsp"%></div>
	
	<div class="con">
		<h1>호텔 예약 내역 (관리자)</h1>
		<form class="frm" action="/mypage/admin/resListSearch">
			<input class="form-control" name="name" type="text" placeholder="회원 명 or 반려견 명" aria-label="default input example">
			<button type="submit" class="btn btn-warning">검색🔍</button>
		</form>
		
		<table class="table table-striped table-hover">
			<tr>
				<th>예약 번호</th>
				<th>시작 일</th>
				<th>종료 일</th>
				<th>방번호</th>
				<th>회원아이디</th>
				<th>견 명</th>
				<th>견 사이즈</th>
				<th>중성화 여부</th>
				<th>상태</th>
			</tr>
			<tr>
				<td colspan="9"><h1>검색 결과가 존재하지 않습니다.</h1></td>
			</tr>
		</table>


		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li
					class="page-item ${page.startPage > page.pageBlock ? '' : 'disabled'}">
					<a class="page-link"
					href="/mypage/admin/resList?currentPage=${page.startPage-page.pageBlock}">Previous</a>
				</li>

				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
					<li class="page-item ${page.currentPage == i ? 'active' : ''}">
						<a class="page-link" href="/mypage/admin/resList?currentPage=${i}">${i}</a>
					</li>
				</c:forEach>

				<li
					class="page-item ${page.startPage < page.pageBlock ? 'disabled' : ''}">
					<a class="page-link"
					href="/mypage/admin/resList?currentPage=${page.startPage+page.pageBlock}">Next</a>
				</li>
			</ul>
		</nav>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>