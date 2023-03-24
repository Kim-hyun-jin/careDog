<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
#numbering {
	position: absolute;
	bottom: 300px;
	left: 900;
	position: fixed;
	position: absolute;
}
</style>
<meta charset="UTF-8">
<title>dogNotice</title>
</head>
<body>
	<div class=container>
		<div class="col-l-10 my-3">
            <span class="fs-2 fw-semibold  ">DOG NOTICE</span>
         </div>
		
		<button type="button" class="btn btn-outline-warning my-4" 
		onclick="location.href='/school/dogNotice/writeForm?id=${id}'" >알림장 쓰기</button>
		

	

		<input type="hidden" name="id" value="${id}">

		<p>
		<table class="table">
			
			<thead>
				<tr>
					<th scope="col">글번호</th>
					<th scope="col">제목</th>
					<th scope="col">선생님</th>
					<th scope="col">날짜</th>
					<th scope="col">강아지이름</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="num" value="${page.total-page.start+1 }"></c:set>
				<c:forEach var="list" items="${dogList}">
					<tr>
						<th scope="row">${list.rn}</th>
						<td><a
							href="/school/dogNotice/detailContent2?noticeNo=${list.noticeNo}">${list.title }</a></td>
						<td>${list.tname}</td>
						<td>${list.regdate}</td>
						<td>${list.dogName}</td>
					</tr>

				</c:forEach>



			</tbody>
		</table>

		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
					<li class="page-item"><a class="page-link"
						href="/school/dogNotice/main?currentPage=${i}">${i}</a></li>
				</c:forEach>
			</ul>
		</nav>


	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>