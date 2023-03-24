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
	<span class="fs-2 fw-semibold my-5 mx-5">회원탈퇴 (회원)</span>
	<form action="/mypage/member/myInfoDropAfter" name="mdForm" id="mdForm" method="post" onsubmit="return pwchk()" class="form-inline justify-content-end">
	<input type="hidden" value="${memberDog.id }" name="id">
	<table class="table caption-top my-5" style="margin-left: 100; text-align: center; text-align:left;">
		<tr><th>아이디</th><td>
		<tr><th><input type="text" value="${memberDog.username }" readonly="readonly"></th></tr>
		<tr><th>비밀번호</th><td>
		<tr><th><input type="password" id="password_2" name= "${memberDog.password }" class="pw" placeholder="비밀번호"></th></tr>
		<tr><th>탈퇴 확인</th><td>
		<tr><th><input type="text" id="memberDrop" class="memberDrop" placeholder="'탈퇴합니다' 입력"></th></tr>
		<tr><th><input type="submit" value="탈퇴하기" id="btn" class="btn btn-warning"></th></tr>
	</table>
	</form>
</div>
<div id="sidebar"><%@ include file="/WEB-INF/views/include/memberSidebar.jsp" %></div>
</div>

<script>
function pwchk(){
	 var pwd2 = $("#password_2").val();
	 var pwchk= false;
	 var drop = $("#memberDrop").val();

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
	 if (drop != '탈퇴합니다' || drop == null) {
    	 alert("탈퇴 확인 메세지를 다시 작성해 주세요.")
    	 $('#memberDrop').focus();
    	 pwchk=false;
     } else if(pwd2 == null) {
    	 pwchk=false;
     } 
	 return pwchk;
}

 /*     $('.pw').focusout(function () {
        var pwd1 = $("#password_1").val();
        var pwd1 = $("#password_2").val();
        var drop = $("#memberDrop").val();
  
        if ( pwd1 != '' && pwd2 == '' ) {
            null;
        } else if (pwd1 != "" || pwd2 != "") {
            if (pwd1 == pwd2) {
                $("#alert-success1").css('display', 'inline-block');
                $("#alert-danger1").css('display', 'none');
            } else {
               /*  alert("비밀번호가 일치하지 않습니다. 비밀번호를 재확인해주세요."); 
                $("#alert-success1").css('display', 'none');
                $("#alert-danger1").css('display', 'inline-block');
                $('#password_2').focus();
            }
        }
    });
   
    function myPwEdit(){
    	 var pwd1 = $("#password_1").val();
         var pwd2 = $("#password_2").val();
         var drop = $("#memberDrop").val();
         
         if (pwd1 != pwd2 || pwd2==null){
        	 alert("비밀번호를 다시 확인해 주세요.")
        	  $('#password_2').focus();
        	 return false;
         } if (drop != '탈퇴합니다' || drop == null) {
        	 alert("탈퇴 확인 메세지를 다시 작성해 주세요.")
        	 $('#memberDrop').focus();
        	 return false;
         } else {
        	 return true;
         }
    }  */
</script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>