<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale"="1">
<!-- <link rel="stylesheet"
	href="/WEB-INF/views/school/BHCorrection/bhCorrection.css"> -->
<title>BhMemberSelect</title>
<script type="text/javascript">
	function deleteChk(appliNo) {
		if (confirm("행동교정 신청을 취소하시겠습니까?")) {
			location.href = "BhMemberDelete?appliNo="+appliNo;
		}
	}
</script>
<style type="text/css">
div.button {
	text-align: right;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<%-- <form
				action="/school/BHCorrection/Member/BhMemberDeleteConfirm?appliNo=${bh.appliNo}'"
				method="post" onsubmit="return deleteChk"> --%>
				<c:forEach var="bh" items="${bhList}">
					<table class="table table-striped my-5"
						style="text-align: center; border: 1px solid #dddddd">
						<thead>
							<tr>
								<c:if test="${bh.status eq '미정'}">
									<th class="title" colspan="3"
										style="background-color: #eeeeee; text-align: center; padding: 30px; font-size: 20;">행동교정
										신청 내역</th>
								</c:if>
								<c:if test="${bh.status eq '훈련 확정'}">
									<th colspan="3"
										style="background-color: #eeeeee; text-align: center; padding: 30px; font-size: 20;">행동교정
										훈련이 확정되었습니다</th>
								</c:if>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>제목</th>
								<td colspan="2">${bh.title }</td>
							</tr>
							<tr>
								<th>성함</th>
								<td colspan="2">${bh.memberName }</td>
							</tr>
							<tr>
								<th>연락처</th>
								<td colspan="2">${bh.memberCall}</td>
							</tr>
							<c:if test="${bh.status != '미정'}">
								<tr>
									<th>담당 선생님</th>
									<td colspan="2">${bh.tname }</td>
								</tr>
							</c:if>
							<c:if test="${bh.status != '미정'}">
								<tr>
									<th>훈련 결정 날짜</th>
									<td colspan="2">${bh.startDate }~${bh.finishDate }</td>
								</tr>
							</c:if>
							<tr>
								<th>신청 반려견</th>
								<td colspan="2">${bh.dogName}</td>
							</tr>
							<tr>
								<th>신청 내용</th>
								<td colspan="2">${bh.beforeContent }</td>
							</tr>
							<c:if test="${bh.status != '미정'}">
								<tr>
									<th>상담 내역</th>
									<td colspan="2">${bh.afterContent }</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<div class="button">
						<c:if test="${bh.status eq '미정'}">
							<button type="button" class="btn btn-outline-secondary"
								onclick="location.href='/school/BHCorrection/Member/BhMemberUpdate?appliNo=${bh.appliNo}'">
								수정</button>
						</c:if>


						<c:if test="${bh.status eq '미정'}">
							<%-- <button type="button" class="btn btn-outline-secondary  mx-2 justify-content-end"
							onclick="location.href='/school/BHCorrection/Member/BhMemberDeleteConfirm?appliNo=${bh.appliNo}'" onsubmit="return deleteChk()">
							신청 취소</button>
 --%>
							<button type="button"
								class="btn btn-outline-secondary  mx-2 justify-content-end"
								onclick="deleteChk(${bh.appliNo})">신청 취소</button>
						</c:if>

						<button type="button" class="btn btn-outline-secondary"
							onclick="location.href='/school/BHCorrection/Member/BhMemberList'">목록보기</button>
					</div>
				</c:forEach>
			<!-- </form> -->
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>