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
				<span class="fs-2 fw-semibold">문의 내용</span>
				<hr>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label"><b>작성자</b></label>
					<input type="text" style="width: 200px;" readonly="readonly" class="form-control" id="exampleFormControlInput1" value="${qadto.role eq 'ROLE_ADMIN' ? '관리자' : qadto.memberName}">
				</div>
				<div class="mb-3">
					<label for="exampleFormControlInput1" class="form-label"><b>제목</b></label>
					<input type="text" readonly="readonly" class="form-control" id="exampleFormControlInput1" value="${qadto.title }">
				</div>
				<div class="mb-3">
					<hr style="border: 2px solid #EDA900;">
					<textarea class="form-control" readonly="readonly" id="exampleFormControlTextarea1" rows="10">${qadto.content}</textarea>
				</div>
				<a class="btn btn-warning" href="/customerService/QA/myQuestion?currentPage=${currentPage }">목록보기</a>
				<c:if test="${roleAdmin || principal.id == qadto.id }">
					<button type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop1" class="btn btn-warning" ${qadto.status == '답변 완료' ? 'disabled="disabled"' : ''}>수정 하기</button>
					<button type="button" data-bs-toggle="modal" data-bs-target="#staticBackdrop2" class="btn btn-warning">삭제 하기</button>
				</c:if>
				<hr>
				
				<!-- 수정 modal 창 ----------------------------------------------------------------------------------------------------->
				<div class="modal fade" id="staticBackdrop1" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="staticBackdropLabel">글 수정</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">작성한 문의를 수정하시겠습니까?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
								<a href="/customerService/QA/questionUpdateForm?currentPage=${currentPage }&chk=2&questionNo=${qadto.questionNo}" class="btn btn-primary">수정하기</a>
							</div>
						</div>
					</div>
				</div>
				<!-- 수정 modal 창  끝 ----------------------------------------------------------------------------------------------------->
				
				<!-- 삭제 modal 창 ----------------------------------------------------------------------------------------------------->
				<div class="modal fade" id="staticBackdrop2" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h1 class="modal-title fs-5" id="staticBackdropLabel">글 삭제 확인</h1>
								<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
							</div>
							<div class="modal-body">작성한 문의를 삭제하시겠습니까?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
								<a href="/customerService/QA/questionDelete?currentPage=${currentPage }&chk=2&questionNo=${qadto.questionNo}" class="btn btn-primary">삭제</a>
							</div>
						</div>
					</div>
				</div>
				<!-- 삭제 modal 창 끝 ----------------------------------------------------------------------------------------------------->
				
				<c:if test="${qadto.role ne 'ROLE_ADMIN'}">
					<div class="mb-3">
						<form action="" method="post">
						<label for="exampleFormControlInput1" class="form-label"><b>관리자 답변 내용</b></label>
						<c:choose>
							<c:when test="${qadto.status == null }">
							</c:when>
							<c:when test="${qadto.status == '답변 완료' }">
								<div class="d-inline p-2 text-bg-success">${qadto.status}</div>
								<textarea class="form-control" readonly="readonly" id="exampleFormControlTextarea2" rows="5">${qadto.answerContent}</textarea>
							</c:when>
							<c:otherwise>
								<div class="d-inline p-2 text-bg-secondary">${qadto.status}</div>
								<textarea class="form-control" id="exampleFormControlTextarea2" rows="5" required="required">${qadto.answerContent}</textarea>
							</c:otherwise>
						</c:choose>
					</div>
				</c:if>
				<c:choose>
					<c:when test="${roleAdmin && qadto.role ne 'ROLE_ADMIN' && qadto.status == '답변 미완료' }">
							<button type="submit" class="btn btn-warning">답변 등록</button>
					</c:when>
					<c:when test="${roleAdmin && qadto.role ne 'ROLE_ADMIN'}">
							<button type="submit" class="btn btn-warning">수정</button>
					</c:when>
				</c:choose>
				</form>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>