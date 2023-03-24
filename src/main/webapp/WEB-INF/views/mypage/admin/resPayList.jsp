<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>호텔 결제 내역(관리자)</title>
<script type="text/javascript" src="/js/hotel/hotelRes.js"></script>
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-xl-2"><%@ include
					file="/WEB-INF/views/include/adminSidebar.jsp"%></div>

			<div class="col-xl-10">
				<span class="fs-2 fw-semibold">호텔 결제 내역 (관리자)</span>
				<table class="table table-striped table-hover"
					style="margin-top: 30px;">
					<tr>
						<th>예약 번호</th>
						<th>회원아이디</th>
						<th>회원 이름</th>
						<th>회원 연락처</th>
						<th>결제 금액</th>
						<th>결제 일시</th>
						<th>결제 상태</th>
					</tr>
					<c:set var="num" value="${page.total-page.start+1 }"></c:set>
					<c:forEach items="${resPay }" var="res">
						<tr>
							<td>${res.resNo}</td>
							<td>${res.id}</td>
							<td>${res.memberName}</td>
							<td>${res.memberCall}</td>
							<td><script type="text/javascript">
							document.write(addComma(${res.resPrice}));
						</script></td>
							<td>${res.orderDate}</td>
							<c:choose>
								<c:when test="${res.orderStatus == '결제취소' }">
									<td><span
										style="background: #FF2424; border-radius: 5px; padding: 5px;">결제
											취소</span></td>
								</c:when>
								<c:otherwise>
									<td><span
										style="background: #98F791; border-radius: 5px; padding: 5px;">결제
											완료</span></td>
								</c:otherwise>
							</c:choose>
						</tr>
						<c:set var="num" value="${num - 1 }"></c:set>
					</c:forEach>
				</table>

				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<li
							class="page-item ${page.startPage > page.pageBlock ? '' : 'disabled'}">
							<a class="page-link"
							href="/mypage/admin/resPayList?currentPage=${page.startPage-page.pageBlock}">Previous</a>
						</li>

						<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
							<li class="page-item ${page.currentPage == i ? 'active' : ''}">
								<a class="page-link"
								href="/mypage/admin/resPayList?currentPage=${i}">${i}</a>
							</li>
						</c:forEach>

						<li
							class="page-item ${page.startPage < page.pageBlock ? 'disabled' : ''}">
							<a class="page-link"
							href="/mypage/admin/resPayList?currentPage=${page.startPage+page.pageBlock}">Next</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>