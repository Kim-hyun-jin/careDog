<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>호텔 예약 내역</title>
<link href="/css/hotel/reservation.css" rel="styleSheet" type="text/css">
<script type="text/javascript" src="/js/hotel/hotelRes.js"></script>
<script type="text/javascript">
	function dayD(st, en) {
		const start = new Date(st);
		const end = new Date(en);
		
		const diffTime = (end.getTime() - start.getTime()) / (1000*60*60*24);
		
		return diffTime;
	}
</script>
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid" style="margin-top: 40px;">
		<div class="row">
			<div class="col-lg-2">
				<%@ include file="/WEB-INF/views/include/memberSidebar.jsp"%>
			</div>
			<div class="col-lg-10">
				<span class="fs-2 fw-semibold">예약 내역</span>
				<form action="/mypage/member/memberResListSearch" style="margin-top: 20px;">
					<select name="period" class="form-select" aria-label="Default select example" style="width: 200px; float: left; margin-right: 10px;">
						<option value="three" <c:if test="${'three' eq period}">selected="selected"</c:if> selected="selected">최근 3개월</option>
						<option value="half" <c:if test="${'half' eq period}">selected="selected"</c:if>>최근 6개월</option>
						<option value="year" <c:if test="${'year' eq period}">selected="selected"</c:if>>최근 1년</option>
						<option value="whole" <c:if test="${'whole' eq period}">selected="selected"</c:if>>전체</option>
					</select>
					<button type="submit" class="btn btn-warning">조회🔍</button>
				</form>

				<c:set var="num" value="${1}"></c:set>
				<c:set var="no" value="${page.total-page.start+1 }"></c:set>
				<c:forEach var="list" items="${resList}" varStatus="vs">
					<c:choose>
						<c:when test="${today < list.resEnddate}">
							<div class="card mb-4" style="max-width: 1400px; margin-top: 40px;">
						</c:when>
						<c:otherwise>
							<div class="card mb-4" style="max-width: 1400px; opacity: 0.5; margin-top: 40px;">
						</c:otherwise>
					</c:choose>
					<div class="card-header">
						<h4 class="card-title" style="display: flex; float: left;">${list.resStartdate }
							(
							<script type="text/javascript">document.write(getDayOfWeek('20${list.resStartdate}'));</script>
							)
						</h4>
						<button class="detail" id="det-${num}"
							style="display: flex; float: right;">
							상세보기 <span id="det-${num}-toggle"> 🔽 </span>
						</button>

					</div>
					<div class="row g-3">
						<div class="col-md-3">
							<c:choose>
								<c:when test="${list.roomNum > 400 }">
									<img src="/img/hotel/sweetyroom.jpg"
										class="img-fluid rounded-start" alt="...">
								</c:when>
								<c:when test="${list.roomNum > 300 }">
									<img src="/img/hotel/premiumroom.jpg"
										class="img-fluid rounded-start" alt="...">
								</c:when>
								<c:otherwise>
									<img src="/img/hotel/standardroom.jpg"
										class="img-fluid rounded-start" alt="...">
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-md-8">
							<div class="card-body">
								<p class="card-text">
									<small class="text-muted">숙소 예약번호 : ${list.resNo}</small>
								</p>
								<p class="card-text">
									<c:choose>
										<c:when test="${list.orderStatus == '결제취소' }">
											<span
												style="background: #FF2424; border-radius: 5px; padding: 5px;">예약
												취소</span>
										</c:when>
										<c:when test="${today >= list.resEnddate}">
											<span
												style="background: #98F791; border-radius: 5px; padding: 5px;">이용
												완료</span>
										</c:when>
										<c:otherwise>
											<span
												style="background: #2478FF; border-radius: 5px; padding: 5px;">예약
												완료</span>
										</c:otherwise>
									</c:choose>
								<p>
								<h5 class="card-title">${list.roomType}</h5>
								<p class="card-text">
									${list.resStartdate} (
									<script type="text/javascript">document.write(getDayOfWeek('20${list.resStartdate}'));</script>
									) ~ ${list.resEnddate} (
									<script type="text/javascript">document.write(getDayOfWeek('20${list.resEnddate}'));</script>
									) |
									<script type="text/javascript">document.write(dayD('20${list.resStartdate}', '20${list.resEnddate}'));</script>
									박
								</p>
							</div>
						</div>
					</div>
				</div>
					<form id="cancel${vs.index }"
						action="/mypage/member/memberResUpdate" method="post">
						<div class="content" id="con-${num}" style="display: none;">
							<input type="hidden" name="resNo" value="${list.resNo }">
							<input type="hidden" name="currentPage"
								value="${page.currentPage }">
							<table class="table table-striped table-hover">
								<tr>
									<td colspan="2"><h5>반려견 정보</h5></td>
								</tr>
								<tr>
									<td><small class="text-muted">이름</small></td>
									<th>${list.dogName}</th>
								</tr>
								<tr>
									<td><small class="text-muted">크기</small></td>
									<c:choose>
										<c:when test="${list.dogSize == 3 }">
											<th>대형</th>
										</c:when>
										<c:when test="${list.dogSize == 2 }">
											<th>중형</th>
										</c:when>
										<c:otherwise>
											<th>소형</th>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr>
									<td><small class="text-muted">중성화 여부</small></td>
									<c:choose>
										<c:when test="${list.deSexing == 1 }">
											<th>O</th>
										</c:when>
										<c:otherwise>
											<th>X</th>
										</c:otherwise>
									</c:choose>
								</tr>
								<tr>
									<td colspan="2"><h5>금액 정보</h5></td>
								</tr>
								<tr>
									<td><small class="text-muted">금액</small></td>
									<th><script type="text/javascript">
									document.write(addComma(${list.resPrice}));
								</script></th>
								</tr>
							</table>
							<!-- Button trigger modal -->
							<c:choose>
								<c:when test="${list.orderStatus == '결제취소'}">
									<button type="button" class="btn btn-primary"
										data-bs-toggle="modal" data-bs-target="#staticBackdrop"
										disabled="disabled">예약 취소</button> / 이미 취소된 예약입니다.
					</c:when>
								<c:when test="${today < list.resStartdate}">
									<button type="button" class="btn btn-primary"
										data-bs-toggle="modal"
										data-bs-target="#staticBackdrop${vs.index }">예약 취소</button>
								</c:when>
								<c:otherwise>
									<button type="button" class="btn btn-primary"
										data-bs-toggle="modal" data-bs-target="#staticBackdrop"
										disabled="disabled">예약 취소</button> / 당일 혹은 이용중인 예약은 취소할 수 없습니다.
					</c:otherwise>
							</c:choose>

						</div>
						<!-- Modal -->
					</form>
					<div class="modal fade" id="staticBackdrop${vs.index }"
						data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
						aria-labelledby="staticBackdropLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h1 class="modal-title fs-5" id="staticBackdropLabel">예약
										취소 확인</h1>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
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
					<c:set var="num" value="${num+1}"></c:set>
					<c:set var="no" value="${no - 1 }"></c:set>
				</c:forEach>
				<nav aria-label="Page navigation example">
					<ul class="pagination justify-content-center">
						<li
							class="page-item ${page.startPage > page.pageBlock ? '' : 'disabled'}">
							<a class="page-link"
							href="/mypage/member/memberResListSearch?currentPage=${page.startPage-page.pageBlock}">Previous</a>
						</li>

						<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}">
							<li class="page-item ${page.currentPage == i ? 'active' : ''}">
								<a class="page-link"
								href="/mypage/member/memberResListSearch?currentPage=${i}&period=${period}">${i}</a>
							</li>
						</c:forEach>

						<li
							class="page-item ${page.startPage < page.pageBlock ? 'disabled' : ''}">
							<a class="page-link"
							href="/mypage/member/memberResListSearch?currentPage=${page.startPage+page.pageBlock}">Next</a>
						</li>
					</ul>
				</nav>
				<script>
	  const items = document.querySelectorAll('.detail');
	
	  function openCloseAnswer() {
	    const answerId = this.id.replace('det', 'con');
	
	    if(document.getElementById(answerId).style.display === 'block') {
	      document.getElementById(answerId).style.display = 'none';
	      document.getElementById(this.id + '-toggle').textContent = '🔽';
	    } else {
	      document.getElementById(answerId).style.display = 'block';
	      document.getElementById(this.id + '-toggle').textContent = '🔼';
	    }
	  }
	
	  items.forEach(item => item.addEventListener('click', openCloseAnswer));
	</script>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>