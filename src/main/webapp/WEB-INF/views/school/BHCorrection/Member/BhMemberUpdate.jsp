<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BhMemberUpdate</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<span class="fs-2 fw-semibold my-4">행동교정 신청 수정</span>
			<form action="/school/BHCorrection/Member/BhMemberUpdateConfirm"
				method="post">
				<c:forEach var="bh" items="${bhList }">
					<input name="appliNo" type="hidden" value="${bh.appliNo }">
					<table class="table table-striped my-5">
						<%-- <c:forEach var="bul" items="${bhUpList }"> --%>
						<tr>
							<th>제목</th>
							<td><input type="text" name="title" required="required"
								maxlength="30" value="${bh.title }"></td>
						</tr>
						<tr>
							<th>성함</th>
							<td>${bh.memberName }</td>
						</tr>
						<tr>
							<th>연락처</th>
							<td>${bh.memberCall }</td>
						</tr>
						<tr>
							<th>신청 반려견</th>
							<td><select name="dogNo">
									<c:forEach var="dog" items="${dogList}">
										<c:if test="${bh.dogNo == dog.dogNo }">
											<option value="${dog.dogNo}" selected="selected">${dog.dogName }</option>
										</c:if>
										<c:if test="${bh.dogNo != dog.dogNo }">
											<option value="${dog.dogNo}">${dog.dogName }</option>
										</c:if>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th>상세 내용</th>
							<td><textarea rows="10" cols="70" maxlength="330"
									name="beforeContent" required="required">${bh.beforeContent }</textarea>
								<%-- <input type="text" name="beforeContent" required="required"
								maxlength="330" value="${bh.beforeContent }"
								style="width: 600px; height: 700px; text-align: justify;  white-space:pre;"> --%>
							</td>
						</tr>
					</table>
				</c:forEach>

				<input type="submit" class="btn btn-outline-secondary" value="수정 완료" />
			</form>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>