<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BhAllSelect</title>
<script type="text/javascript">
	function deleteChk(appliNo) {
		if (confirm("훈련을 취소하시겠습니까?")) {
			location.href = "BhTeacherDelete?appliNo="+appliNo;
		}
	}
</script>
</head>
<body>
<%-- 	<c:forEach var="bls" items="${bhAllSelect}">
		<c:if test="${bls.status eq '미정' }">
			<h1>행동교정 신청 내역</h1>
		</c:if>
		<c:if test="${bls.status eq '훈련 확정' }">
			<h1>확정 훈련 조회</h1>
		</c:if>
		<table>
			<tr>
				<th>제목</th>
				<td>${bls.title }</td>
			</tr>
			<tr>
				<th>성함</th>
				<td>${bls.memberName }</td>
			</tr>
			<tr>
				<th>연락처</th>
				<td>${bls.memberCall}</td>
			</tr>
			<c:if test="${bls.status != '미정'}">
				<tr>
					<th>담당 선생님</th>
					<td>${bls.tname }</td>
				</tr>
			</c:if>
			<c:if test="${bls.status != '미정'}">
				<tr>
					<th>훈련 결정 날짜</th>
					<td>${bls.startDate }~${bls.finishDate }</td>
					<fmt:parseDate value="${bh.startDate }" type="date" pattern="yyyy-MM-dd hh:mm:ss"/>
					<fmt:formatDate value="${bh.finishDate }" type="date" pattern="yyMMdd"/>
				</tr>
			</c:if>
			<tr>
				<th>신청 반려견</th>
				<td>${bls.dogName}</td>
			</tr>
			<tr>
				<th>신청 내용</th>
				<td>${bls.beforeContent }</td>
			</tr>
			<c:if test="${bls.status != '미정'}">
				<tr>
					<th>상담 내역</th>
					<td>${bls.afterContent }</td>
				</tr>
			</c:if>
			<tr>
				<th>
					<button type="button" class="btn btn-outline-secondary"
						onclick="location.href='/school/BHCorrection/Teacher/BhAllList?appliNo=${bls.appliNo}'">
						목록 보기</button>
				</th>
			</tr>
			<c:if test="${bls.status == '미정' }">
				<tr>
					<th><button type="button" class="btn btn-outline-secondary"
							onclick="location.href='/school/BHCorrection/Teacher/BhTeacherAdd?appliNo=${bls.appliNo}'">
							훈련 담당하기</button></th>
				</tr>
			</c:if>
			<c:if test="${bls.status == '훈련 확정' }">
				<tr>
					<th><button type="button" class="btn btn-outline-secondary"
							onclick="location.href='/school/BHCorrection/Teacher/BhTeacherUpdate?appliNo=${bls.appliNo}'">
							상담 내역 수정하기</button></th>
				</tr>
				<tr>
					<th><button type="button" class="btn btn-outline-secondary"
							onclick="deleteChk(${bls.appliNo})">훈련 취소하기</button></th>
			</c:if>
		</table>
	</c:forEach> --%>
	
	
	
	
	<div class="container-fluid  justify-content-center mx-5">
		<div class="row">


			<div class="col-xl-10">
				


			</div>
		</div>
	</div>
	
	
	
<div class="container">
	<c:forEach var="bls" items="${bhAllSelect}">
		<c:if test="${bls.status eq '미정' }">
			<div class="col-xl-10 my-5 mx-3">
				<span class="fs-2 fw-semibold mx-5 ">행동교정 신청내역</span>
			</div>
		</c:if>
		<c:if test="${bls.status eq '훈련 확정' }">
			<div class="col-xl-10 my-5 mx-5">
				<span class="fs-2 fw-semibold mx-3 ">확정 훈련 조회</span>
			</div>
			
		</c:if>	
	
		<table class="table caption-top my-5" style="margin-left: 100; text-align: center; text-align:left;">
	
			<tr><th>[제목]</th><td>${bls.title }</td></tr>
			<tr><th>[성함]</th><td>${bls.memberName }</td></tr>
			<tr><th>[연락처]</th><td>${bls.memberCall}</td></tr>
			
			<c:if test="${bls.status != '미정'}">
				<tr>
					<th>[담당 선생님]</th>
					<td>${bls.tname }</td>
				</tr>
			</c:if>
			
			<c:if test="${bls.status != '미정'}">
				<tr>
					<th>[훈련 결정 날짜]</th>
					<td>${bls.startDate }~${bls.finishDate }</td>
					<%--<fmt:parseDate value="${bh.startDate }" type="date" pattern="yyyy-MM-dd hh:mm:ss"/>
					<fmt:formatDate value="${bh.finishDate }" type="date" pattern="yyMMdd"/>--%>
				</tr>
			</c:if>
			
			<tr><th>[신청 반려견]</th><td>${bls.dogName}</td></tr>
			<tr><th>[신청 내용]</th><td>${bls.beforeContent }</td></tr>
			<c:if test="${bls.status != '미정'}">
				<tr>
					<th>[상담 내역]</th>
					<td>${bls.afterContent }</td>
				</tr>
			</c:if>


			<c:if test="${bls.status == '미정' }">
				<tr >
					<th></th>
					<th class="d-flex justify-content-end">
						<button type="button" class="btn btn-outline-secondary mx-2"
						onclick="location.href='/school/BHCorrection/Teacher/BhAllList?appliNo=${bls.appliNo}'">
						목록 보기</button>	
		
						<button type="button" class="btn btn-outline-secondary"
							onclick="location.href='/school/BHCorrection/Teacher/BhTeacherAdd?appliNo=${bls.appliNo}'">
							훈련 담당하기</button>
					</th>
				</tr>
			</c:if>
			<c:if test="${bls.status == '훈련 확정' }">
				<tr>
					<th></th>
					<th class="d-flex justify-content-end"><button type="button" class="btn btn-outline-secondary"
							onclick="location.href='/school/BHCorrection/Teacher/BhTeacherUpdate?appliNo=${bls.appliNo}'">
							상담 내역 수정하기</button>
					</th>
				</tr>
				<tr>
					<th></th>
					<th class="d-flex justify-content-end">
						<button type="button" class="btn btn-outline-secondary mx-2"
						onclick="location.href='/school/BHCorrection/Teacher/BhAllList?appliNo=${bls.appliNo}'">
						목록 보기</button>					
						<button type="button" class="btn btn-outline-secondary"
							onclick="deleteChk(${bls.appliNo})">훈련 취소하기</button>
					</th>
				</tr>

			</c:if>						
			</table>
	</c:forEach>
</div>

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>