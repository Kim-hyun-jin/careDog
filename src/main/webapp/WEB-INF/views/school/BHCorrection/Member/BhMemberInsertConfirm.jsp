<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BhMemberInsertConfirm</title>
</head>
<body>
	<div class="container my-5">
		<span class="fs-2 fw-semibold my-4">행동교정 신청이 완료되었습니다.</span>
		<p>행동 교정 신청 목록 페이지로 이동합니다.</p>
<%-- 		<c:forEach var="bcf" items="${BhConfirm }">
			<table class="table-secondary table table-striped my-5" id="table">

				<tbody>
					<tr>
						<!-- <th scope="col">#</th> -->
						<th>성함</th>
						<th>연락처</th>
						<th>신청 반려견</th>
						<th>신청 내용</th>
					</tr>
					<tr>
						<!-- <th scope="row"></th> -->
						<td>${bcf.memberName}</td>
						<td>${bcf.memberCall}</td>
						<td>${bcf.dogName}</td>
						<td>${bcf.beforeContent}</td>
					</tr>

				</tbody>
			</table>
		</c:forEach> --%>

		<%-- 		 
			결제일시:     [[${info.approved_at}]]<br/>
			주문번호:    [[${info.partner_order_id}]]<br/>
			상품명:    [[${info.item_name} 룸]]<br/>
			숙박일:    [[${info.quantity} 일]]<br/>
			결제금액:    [[${info.amount.total} 원]]<br/>
			 --%>

		<button type="button" class="btn btn-outline-secondary"
			onclick="location.href='/school/BHCorrection/Member/BhMemberList';">
			목록으로</button>

	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>