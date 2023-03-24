<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale"="1">
<!-- <link rel="stylesheet"
	href="/WEB-INF/views/school/BHCorrection/bhCorrection.css"> -->
<title>BhMemberInsert</title>
<style type="text/css">
th.explain {
	font-weight: normal;
	color: gray;
}

#beforeContent {
	padding: 1px 2px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table table-striped my-5" style="text-align: center;">
				<thead style="border: 1px solid #dddddd">
					<tr>
						<th colspan="3"
							style="background-color: #eeeeee; text-align: center;">행동교정
							신청</th>
					</tr>
				</thead>
				<tr>
					<th class="explain" colspan="5"
						style="background-color: #eeeeee; text-align: left;">하단 양식을
						작성하시면 작성하신 연락처로 연락을 드려 일정 상담 하겠습니다^^ 연락처 변경 또는 새로운 반려견은 마이페이지에서 추가
						부탁드립니다.
						</p>
					</th>
				</tr>
				<tr>
					<th class="explain" colspan="3"
						style="background-color: #eeeeee; text-align: left;"></th>
				</tr>

				<tbody>
					<form action="/school/BHCorrection/Member/BhMemberInsertConfirm"
						method="post" name="frm">


						<c:forEach var="ml" items="${meList}">
							<tr>
								<th style="width: 20%;">제목</th>
								<td colspan="2"><input type="text" name="title" id="title"
									size="70" maxlength="30" required="required"></td>
							</tr>
							<tr>
								<th>성함</th>
								<td colspan="2">${ml.memberName }</td>
							</tr>
							<tr>
								<th>연락처</th>
								<td colspan="2">${ml.memberCall }</td>
							</tr>
							<tr>
								<th>신청 반려견</th>
								<td colspan="2"><select name="dogNo" id="dogNo"
									class="form">
										<c:forEach var="dog" items="${dogList }" varStatus="i">
											<option value="${dog.dogNo }">${dog.dogName}</option>
										</c:forEach>
								</select>
							</tr>
							<tr>
								<th>상세 내용</th>
								<td><textarea rows="10" cols="70" maxlength="330"
										name="beforeContent" required="required"></textarea>
									<!-- colspan="2" text-align:left;"><input type="text"
									name="beforeContent" id="beforeContent"
									width = "200px" height="100px" maxlength="330"
									required="required"> --></td>
							</tr>
						</c:forEach>
				</tbody>
			</table>
			<button type="submit" class="btn btn-outline-secondary">
				<!-- onclick="location.href='/school/BHCorrection/BhConfirm';" -->
				신청하기
			</button>
			</form>
			</tbody>
			</table>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>