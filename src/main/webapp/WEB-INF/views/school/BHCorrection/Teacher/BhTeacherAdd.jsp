<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BhTeacherAdd</title>
<script type="text/javascript">
	function updateChk() {
		return confirm("훈련을 확정하시겠습니까?");
	}
</script>
</head>
<body>

<div class="container">
	<div class="col-xl-10 my-5 mx-3">
		<span class="fs-2 fw-semibold mx-5 ">행동교정 훈련 담당</span>
	</div>
	
	<form action="/school/BHCorrection/Teacher/BhTeacherAddConfirm"
		method="post" onsubmit="return updateChk() ">
		<c:forEach var="btas" items="${BhTeacherAddSelect}">
			<input name="appliNo" type="hidden" value="${btas.appliNo }">
			<table class="table caption-top my-5" style="margin-left: 100; text-align: center; text-align:left;">
				<tr>
					<th>제목</th>
					<td>${btas.title }</td>
				</tr>
				<tr>
					<th>성함</th>
					<td>${btas.memberName }</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>${btas.memberCall }</td>
				</tr>
				<tr>
					<th>담당 선생님</th>
					<c:forEach var="ti" items="${teacherId }">
						<td>${ti.memberName }</td>
					</c:forEach>
				</tr>
				<tr>
					<th>시작일</th>
					<td><input type="date" name="startDate" required="required"></td>
				</tr>
				<tr>
					<th>종료일</th>
					<td><input type="date" name="finishDate" required="required"></td>
				</tr>
				<tr>
					<th>신청 반려견</th>
					<td>${btas.dogName }</td>
				</tr>
				<tr>
					<th>신청 내용</th>
					<td>${btas.beforeContent }</td>
				</tr>
				<tr>
					<th>상담 내역</th>
					<td><textarea rows="10" cols="70" maxlength="330"
									name="afterContent" required="required">${bh.beforeContent }</textarea>
					
					<!-- <input type="text" name="afterContent" maxlength="330" required="required"> --></td>
				</tr>
				<tr>
					<th></th>
					<th class="d-flex justify-content-end">
						<button type="button" class="btn btn-outline-secondary mx-2"
						onclick="location.href='/school/BHCorrection/Teacher/BhAllList?appliNo=${bls.appliNo}'">
						목록 보기</button>	

						<button type="submit" class="btn btn-outline-secondary  mx-2 justify-content-end">훈련 확정</button>
					</th>
				</tr>

			</table>
		</c:forEach>
	</form>
</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>