<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<% String context2 = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>


<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/datepicker-ko.js"></script>

<script type="text/javascript">
	function changRole(obj) {
    alert(obj.value); // 선택된 option의 value가 출력된다!
    $("#role").val(obj.value);
	}
	
</script>

<style type="text/css">

   #all {
      position: absolute;
      top: 120px;
   }
   
   #bodyall {
      float: right;
   }
   
   #sidebar{
      float: left;
   }
</style>

</head>
<body>
<div id="all">
<div id="bodyall">
<!-- <h1>강아지 정보</h1> -->
	<form action="/mypage/admin/updateDogInfo" method="post" class="form-inline justify-content-end">

			<input type="hidden" value="${detailDogInfo.dogNo }" id="dogNo" name="dogNo">
	
	
		<table class="table caption-top" style="margin-left: 100; text-align: center; text-align:left;">
			<tr><th>반려견번호</th><td>${detailDogInfo.dogNo }</td></tr>
			<tr><th>회원번호</th><td>${detailDogInfo.id }</td></tr>
			<tr><th>반려견이름</th><td>${detailDogInfo.dogName }</td></tr>
			<tr><th>반려견성별</th><td>
				<c:if test="${detailDogInfo.dogGender==0 }">암컷</c:if>
				<c:if test="${detailDogInfo.dogGender==1 }">수컷</c:if></td></tr> 
			<tr><th>반려견나이</th><td>${detailDogInfo.dogAge }</td></tr>
			<tr><th>반려견몸무게</th><td>${detailDogInfo.dogWeight }</td></tr>
			<tr><th>중성화여부</th><td>
				<c:if test="${detailDogInfo.deSexibng==0 }">X</c:if>
				<c:if test="${detailDogInfo.deSexibng==1 }">O</c:if></td></tr> 
			<tr><th>견종명</th><td>${detailDogInfo.dogKind }</td></tr>
			<tr><th>유치원가입상태</th><td>
				<c:if test="${detailDogInfo.joinCheck==0 }">X</c:if>
				<c:if test="${detailDogInfo.joinCheck==1 }">O</c:if></td></tr> 
			<%-- <tr><th>유치원시작일</th><td>${detailDogInfo.scStartdate.substring(0, 11) }</td></tr> --%>
			<%-- <tr><th>유치원종료일</th><td>${detailDogInfo.scEnddate.substring(0, 11)  }</td></tr> --%>
			<tr><th>유치원시작일</th><td><input id="datepicker" name="scStartdate" value="${detailDogInfo.scStartdate.substring(0, 11)}" type="text" required="required"></td></tr>
			<tr><th>유치원종료일</th><td><input id="datepicker1" name="scEnddate" value="${detailDogInfo.scEnddate.substring(0, 11)}" type="text" required="required"></td></tr>
			
			<tr><th>특이사항</th><td>${detailDogInfo.dogMemo }</td></tr>
			<tr><th><input type="submit" value="저장" class="btn btn-warning"></th></tr>
		</table>
		<script>
					$(function () {
						 $("#datepicker").datepicker();
					});
					
					$(function () {
						 $("#datepicker1").datepicker();
					});
		</script>
	
	</form>
</div>
<div id="sidebar"><%@ include file="/WEB-INF/views/include/adminSidebar.jsp" %></div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>