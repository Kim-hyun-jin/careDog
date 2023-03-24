<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QA My question 등록</title>
<link href="/css/customerService/QA/QA.css" rel="styleSheet" type="text/css">
<script type="text/javascript">
$(document).ready(function() {
	$("#exampleFormControlInput2").focus();
});
</script>
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
				<form action="/customerService/QA/qaMyInsert" method="post" id="insert">
					<input type="hidden" name="id" value="${principal.id }">
					<span class="fs-2 fw-semibold">문의 내용</span>
					<hr>
					<div class="mb-3">
						<label for="exampleFormControlInput1" class="form-label"><b>작성자</b></label>
						<input type="text" readonly="readonly" class="form-control" id="exampleFormControlInput1" value="${principal.role eq 'ROLE_ADMIN' ? '관리자' : principal.memberName}">
					</div>
					<div class="mb-3">
						<label for="exampleFormControlInput1" class="form-label"><b>제목</b></label>
						<input type="text" name="title" class="form-control" id="exampleFormControlInput2" required="required" placeholder="제목을 입력해주세요.">
					</div>
					<div class="mb-3">
						<hr style="border: 2px solid #EDA900;">
						<textarea class="form-control" name="content" id="exampleFormControlTextarea1" rows="10" required="required" placeholder="상세 내용을 입력해주세요."></textarea>
					</div>
					<button type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop1" class="btn btn-warning">글 등록</button>
					<a href="/customerService/QA/myQuestion?currentPage=${currentPage}" class="btn btn-warning">목록 보기</a>
				</form>
				<div class="modal fade" id="staticBackdrop1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="staticBackdropLabel">글 작성 확인</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">관리자에게 문의를 남기시겠습니까?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
								<button type="submit" form="insert" class="btn btn-primary">작성 완료</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>