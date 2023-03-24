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
							<a href="/customerService/QA/question" class="link-dark d-inline-flex text-decoration-none rounded"><b>문의하기</b></a>
						</li>
						<li class="mb-1">
							<a href="/customerService/QA/myQuestion" class="link-dark d-inline-flex text-decoration-none rounded">나의 QnA</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-lg-10">
				<span class="fs-2 fw-semibold">문의하기</span>
				<hr>
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>작성자</th>
							<th>제목</th>
							<th>답변 상태</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${admin }" var="admin">
							<tr style="background-color: #FFDDDD;">
								<td><b>관리자</b></td>
								<td><a class="qaAdminTitle" href="/customerService/QA/questionContent?questionNo=${admin.questionNo }&currentPage=${page.currentPage}">${admin.title }</a></td>
								<td></td>
							</tr>
						</c:forEach>
						<c:forEach items="${question }" var="que">
							<tr>
								<td>${que.memberName }</td>
								<td><a class="qaTitle" href="/customerService/QA/questionContent?questionNo=${que.questionNo }&currentPage=${page.currentPage}">${que.title }</a></td>
								<td><div class="btn btn-${que.status == '답변 완료' ? 'success' : 'secondary'}" style="width: 112px;">${que.status}</div></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<li
							class="page-item ${page.startPage > page.pageBlock ? '' : 'disabled'}">
							<a class="page-link" href="/customerService/QA/question?currentPage=${page.startPage-page.pageBlock}">Previous</a>
						</li>

						<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
							<li class="page-item ${page.currentPage == i ? 'active' : ''}">
								<a class="page-link" href="/customerService/QA/question?currentPage=${i}">${i}</a>
							</li>
						</c:forEach>

						<li
							class="page-item ${page.startPage < page.pageBlock ? 'disabled' : ''}">
							<a class="page-link" href="/customerService/QA/question?currentPage=${page.startPage+page.pageBlock}">Next</a>
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
									관리자에게 문의를 남기시겠습니까?
								</c:if>
								<c:if test="${roleAdmin }">
									공지를 남기시겠습니까?
								</c:if>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
								<a class="btn btn-primary" href="/customerService/QA/questionInsert?currentPage=${page.currentPage}">글 쓰기</a>
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