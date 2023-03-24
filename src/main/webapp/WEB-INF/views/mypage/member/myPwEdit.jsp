<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<% String context2 = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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

<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<div id="all">
<div id="bodyall">
<form action="/mypage/member/myPwEditAfter" name="pwForm" id="pwForm" method="post" onsubmit="return pwchk()">
	<span class="fs-2 fw-semibold my-5 mx-5">비밀번호 수정 (회원)</span>
	<!-- <h1 style=" width:60%; height: 100px; margin: auto; text-align: center; text-align:left;">현재 비밀번호 확인</h1> -->
	<table class="table caption-top my-5" style="margin-left: 100; text-align: center; text-align:left;">
	<input type="hidden" value="${myPwEdit.id }" name="id">
	<tr><th>아이디</th><td>
	
	  <tr><th><input type="text" value="${myPwEdit.username }" readonly="readonly"></th></tr>
	
<%-- 
 	<div class="content_title">현재 비밀번호</div>
	<div class="content_content">
	    <input type="hidden" id="password_1" class="pw" value="${myPwEdit.password }" readonly="readonly">
	</div> --%>

	<div class="content_title"><tr><th>비밀번호 확인</th><td></div>
	<div class="content_content">
	    <tr><th><input type="password" id="password_2" name= "${myPwEdit.password }" class="pw" placeholder="비밀번호 확인"></th></tr>
<!-- 	    <span id="alert-success1" style="display: none;">비밀번호가 일치합니다.</span>
	    <span id="alert-danger1" style="display: none; color: #d92742; font-weight: bold; ">비밀번호가 일치하지 않습니다.</span> -->
	</div>
		<tr><td><input type="submit" value="확인" id="btn" class="btn btn-warning"	></td></tr>
	</table>
</form> 
</div>
<div id="sidebar"><%@ include file="/WEB-INF/views/include/memberSidebar.jsp" %></div>
</div>
<script>
 function pwchk(){
	 var pwd2 = $("#password_2").val();
	 var pwchk= false;

	 $.ajax({
		 url : "/mypage/member/myPwEditChk",
		 data : {passwd:pwd2},
		 dataType : 'text',
		 async : false,
		 success : function(passwddata){
			 var result = passwddata;
			 
			 if(result=="true"){
				 pwchk= true;
			 } else {
				 alert("비밀번호를 다시 입력해 주세요");
				 pwchk= false;
			 }
		 }
	 });
	 return pwchk;
 }
/*     function myPwEdit(){
    	 var pwd1 = $("#password_1").val();
         var pwd2 = $("#password_2").val();
         
         if (pwd1 == pwd2 && pwd2 != null){
        	 return true;
         } else {
        	 alert("비밀번호를 다시 확인해 주세요.")
        	 $('#password_2').focus();
        	 return false;
         }
    } */

    
</script>




<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>