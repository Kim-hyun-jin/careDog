<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>호텔 예약 내역(관리자)</title>
<link href="/css/mypage/adminRes.css" rel="styleSheet" type="text/css">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-2"><%@ include
					file="/WEB-INF/views/include/adminSidebar.jsp"%></div>
			<div class="col-lg-10">
				<span class="fs-2 fw-semibold">호텔 예약 내역 (관리자)</span>
				<form class="frm" action="/mypage/admin/resListSearch">
					<input class="form-control" name="name" type="text"
						placeholder="회원 명 or 반려견 명" aria-label="default input example">
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
					<c:if test="${size == 0 }">
						<tr>
							<td colspan="9"><h1>검색 결과가 존재하지 않습니다.</h1></td>
						</tr>
					</c:if>
					<c:set var="num" value="${page.total-page.start+1 }"></c:set>
					<c:forEach items="${res}" var="res" varStatus="vs">
						<form id="cancel${vs.index }"
							action="/mypage/admin/memberResAdminUpdate" method="post">
							<input type="hidden" name="resNo" value="${res.resNo }">
							<input type="hidden" name="currentPage"
								value="${page.currentPage }">
							<tr>
								<td>${res.resNo}</td>
								<td>${res.resStartdate}</td>
								<td>${res.resEnddate}</td>
								<td>${res.roomNum}</td>
								<td>${res.memberName}</td>
								<td>${res.dogName}</td>
								<c:choose>
									<c:when test="${res.dogSize == 3 }">
										<td>대형</td>
									</c:when>
									<c:when test="${res.dogSize == 2 }">
										<td>중형</td>
									</c:when>
									<c:otherwise>
										<td>소형</td>
									</c:otherwise>
								</c:choose>
								<c:choose>
									<c:when test="${res.deSexing == 1 }">
										<td>O</td>
									</c:when>
									<c:otherwise>
										<td>X</td>
									</c:otherwise>
								</c:choose>

								<td><c:choose>
										<c:when test="${res.orderStatus == '결제취소'}">
											<button type="button" style="background-color: #FF2424;"
												class="btn btn-primary" data-bs-toggle="modal"
												data-bs-target="#staticBackdrop" disabled="disabled">
												예약 취소됨</button>
										</c:when>
										<c:when test="${today < res.resStartdate}">
											<button type="button" class="btn btn-primary"
												style="width: 111px;" data-bs-toggle="modal"
												data-bs-target="#staticBackdrop${vs.index }">예약 취소</button>
										</c:when>
										<c:otherwise>
											<button type="button"
												style="background-color: #23A41A; width: 111px;"
												class="btn btn-primary" data-bs-toggle="modal"
												data-bs-target="#staticBackdrop" disabled="disabled">
												이용 완료</button>
										</c:otherwise>
									</c:choose></td>
							</tr>
						</form>
						<div class="modal fade" id="staticBackdrop${vs.index }"
							data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
							aria-labelledby="staticBackdropLabel" aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h1 class="modal-title fs-5" id="staticBackdropLabel">예약
											취소 확인</h1>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">진짜 진짜 진심으로 취소를 하시겠습니까?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">닫기</button>
										<button type="submit" form="cancel${vs.index }"
											class="btn btn-primary">취소하기</button>
									</div>
								</div>
							</div>
						</div>
						<c:set var="num" value="${num - 1 }"></c:set>
					</c:forEach>
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
								<a class="page-link"
								href="/mypage/admin/resListSearch?currentPage=${i}&name=${name}">${i}</a>
							</li>
						</c:forEach>

						<li
							class="page-item ${page.startPage < page.pageBlock ? 'disabled' : ''}">
							<a class="page-link"
							href="/mypage/admin/resList?currentPage=${page.startPage+page.pageBlock}">Next</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>