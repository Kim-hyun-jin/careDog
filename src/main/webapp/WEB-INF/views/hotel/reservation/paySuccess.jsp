<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/hotel/reservation.css" rel="styleSheet" type="text/css">
</head>
<body style="background-color: #FFC720">
	<div class="container my-5">
			<h2>카카오페이 결제가 정상적으로 완료되었습니다.</h2>
			<h2>예약 내용은 email을 통해 재확인 가능합니다!</h2>
			

		<table class="table-secondary table table-striped my-5" id="table">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">결제일시</th>
		      <th scope="col">주문자</th>
		      <th scope="col">상품명</th>
		      <th scope="col">숙박일</th>
		      <th scope="col">결제금액</th>
		    </tr>
		  </thead>
		  <tbody>
		    <tr>
		      <th scope="row"></th>
		      <td>
		      	<fmt:formatDate value="${info.approved_at}" pattern="y-M-d a h:mm:ss" type="date"/>
		      </td>
		      <td>${info.partner_user_id}</td>
		      <td>${info.item_name} 룸</td>
		      <td>${info.quantity} 일</td>
		      <td>${info.amount.total} 원</td>
		    </tr>

		  </tbody>
		</table>
	
	
<%-- 		 
			결제일시:     [[${info.approved_at}]]<br/>
			주문번호:    [[${info.partner_order_id}]]<br/>
			상품명:    [[${info.item_name} 룸]]<br/>
			숙박일:    [[${info.quantity} 일]]<br/>
			결제금액:    [[${info.amount.total} 원]]<br/>
			 --%>
			 
			<a href="/mypage/member/memberResList">
				<div class="goButton">
				    <p class="goBtnText">예약 확인</p>
				    <div class="goBtnTwo">
				      <p class="goBtnText2">Go!</p>
				    </div>
				 </div>
			</a>
	
	</div>
	 	
	
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>