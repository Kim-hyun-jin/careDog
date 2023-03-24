<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QA question</title>
<link href="/css/customerService/QA/QA.css" rel="styleSheet" type="text/css">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-2">
				<div class="flex-shrink-0 p-3 bg-white" style="width: 280px;">
					<a href="#" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
					<span class="fs-5 fw-semibold">Q n A</span>
					</a>
					<ul class="list-unstyled ps-0">
						<li class="mb-1">
							<a href="/customerService/QA/QA" class="link-dark d-inline-flex text-decoration-none rounded">자주 묻는 질문</a>
						</li>
						<li class="mb-1">
							<a href="/customerService/QA/question" class="link-dark d-inline-flex text-decoration-none rounded">문의하기</a>
						</li>
						<li class="mb-1">
							<a href="/customerService/QA/myQuestion" class="link-dark d-inline-flex text-decoration-none rounded"><b>나의 QnA</b></a>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-lg-10">
				<span class="fs-2 fw-semibold">나의 Q n A</span>
				<hr>
				<table class="table">
					<thead>
						<tr>
							<th>작성자</th>
							<th>제목</th>
							<th>답변 상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${QueList }" var="list">
							<tr>
								<td>${list.memberName }</td>
								<td><a class="qaTitle" href="/customerService/QA/questionMyContent?questionNo=${list.questionNo }&currentPage=${page.currentPage}">${list.title }</a></td>
								<td><c:if test="${!roleAdmin}">${list.status }</c:if></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<li
							class="page-item ${page.startPage > page.pageBlock ? '' : 'disabled'}">
							<a class="page-link" href="/customerService/QA/myQuestion?currentPage=${page.startPage-page.pageBlock}">Previous</a>
						</li>

						<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
							<li class="page-item ${page.currentPage == i ? 'active' : ''}">
								<a class="page-link" href="/customerService/QA/myQuestion?currentPage=${i}">${i}</a>
							</li>
						</c:forEach>

						<li
							class="page-item ${page.startPage < page.pageBlock ? 'disabled' : ''}">
							<a class="page-link" href="/customerService/QA/myQuestion?currentPage=${page.startPage+page.pageBlock}">Next</a>
						</li>
					</ul>
				</nav>
				<c:if test="${roleMember }">
					<button type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop1" class="btn btn-warning">글 등록</button>
				</c:if>
				<!-- modal 창 ----------------------------------------------------------------------------------------------------->
				<div class="modal fade" id="staticBackdrop1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="staticBackdropLabel">글 등록</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">
								<c:if test="${!roleAdmin }">
									관리자에게 문의 
								</c:if>
								<c:if test="${roleAdmin }">
									공지
								</c:if>
								를 남기시겠습니까?
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
								<a class="btn btn-primary" href="/customerService/QA/questionMyInsert?currentPage=${page.currentPage}">글 쓰기</a>
							</div>
						</div>
					</div>
				</div>
				<!-- modal 창 ----------------------------------------------------------------------------------------------------->
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>