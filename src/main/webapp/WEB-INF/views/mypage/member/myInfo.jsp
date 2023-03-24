<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<% String context2 = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin</title>

<script type="text/javascript">

    $('.selId').focusout(function () {
var selId = $("#selId").val();


    
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

<div>
<div id="all">
<div id="bodyall">
	<span class="fs-2 fw-semibold my-5 mx-5">내정보 (회원)</span>
	<form class="form-inline justify-content-end my-5">
		<table class="table caption-top"  style="margin-left: 100; text-align: center; text-align:left;">
	
			<tr><th>회원번호</th><td><input type="hidden" value="${selMyInfo1.id}" class="selId" id="selId">${selMyInfo1.id }</td></tr>
			<tr><th>아이디</th><td>${selMyInfo1.username }</td></tr>
			
			<tr><th>이름</th><td>${selMyInfo1.memberName }</td></tr>
			<tr><th>연락처</th><td>${selMyInfo1.memberCall }</td></tr>
			<tr><th>이메일</th><td>${selMyInfo1.memberEmail }</td></tr>
			<tr><th>주소</th><td>${selMyInfo1.memberAddress }</td></tr>
			<tr><td colspan="2">
			<input type="button" value="수정" class="btn btn-warning"	
					onclick="location.href='updateFormMyInfo?selMyInfo1=${selMyInfo1.id}'"></td></tr>
				
		</table>
	</form>
</div>
<div id="sidebar"><%@ include file="/WEB-INF/views/include/memberSidebar.jsp" %></div>
</div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>