<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<% String context2 = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin</title>

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
<input type="hidden" value="${memberDog.id }">
<form action="/mypage/mailTransport" name="dropForm" id="dropForm" class="form-inline justify-content-end" style="margin-left: 100; text-align: center; text-align:left;">
<h1>탈퇴가 완료되었습니다.</h1>
<input type="submit" value="확인" class="btn btn-warning" style="float: center;">
</form>
</div>
<div id="sidebar"><%@ include file="/WEB-INF/views/include/memberSidebar.jsp" %></div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>