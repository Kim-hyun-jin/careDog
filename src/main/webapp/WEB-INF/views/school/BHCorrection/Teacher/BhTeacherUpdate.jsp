<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BhTeacherUpdate</title>
<script type="text/javascript">
	function updateChk() {
		return confirm("훈련을 수정하시겠습니까?");
	}
	function phone_format(num) {
		return num.replace(/^(\d{2,3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);
		
	}
</script>
</head>
<body>

<div class="container justify-content-cente">
	<div class="col-xl-10 my-5 mx-3">
		<span class="fs-2 fw-semibold mx-5 ">확정 훈련 수정</span>
	</div>
	
	<form action="/school/BHCorrection/Teacher/BhTeacherUpdateConfirm" method="post"
		onsubmit="return updateChk() ">
		<c:forEach var="btus" items="${BhTeacherUpdateSelect}">
			<input name="appliNo" type="hidden" value="${btus.appliNo }">
			<table class="table caption-top my-5" style="margin-left: 100; text-align: center; text-align:left;">
				<tr>
					<th>제목</th>
					<td>${btus.title }</td>
				</tr>
				<tr>
					<th>성함</th>
					<td>${btus.memberName }</td>
				</tr>
				<tr>
					<th>연락처</th>
					<td>${btus.memberCall}</td> 
					<%-- <td value="return phone_format(${btus.memberCall })"></td> --%>
				</tr>
				<tr>
					<th>담당 선생님</th>
					<td>${btus.tname }</td>
				</tr>
				<tr>
					<th>시작일</th>
					<td><input type="date" name="startDate" required="required" value="${btus.startDate }"></td>
				</tr>
				<tr>
					<th>종료일</th>
					<td><input type="date" name="finishDate" required="required" value="${btus.finishDate }"></td>
				</tr>
				<tr>
					<th>신청 반려견</th>
					<td>${btus.dogName }</td>
				</tr>
				<tr>
					<th>신청 내용</th>
					<td>${btus.beforeContent }</td>
				</tr>
				<tr>
					<th>상담 내역</th>
					<td><textarea rows="10" cols="70" maxlength="330"
									name="afterContent" required="required">${btus.afterContent }</textarea>
					<%-- <input type="text" name="afterContent" required="required" maxlength="330" value="${btus.afterContent }"> --%></td>
				</tr>
				<tr>
					<th ></th>
					<th class="d-flex justify-content-end">
						<button type="submit" class="btn btn-outline-secondary">수정 완료</button>
					</th>
				</tr>
			</table>
		</c:forEach>
	</form>
	
</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>