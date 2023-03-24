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
<div id="all">
<div id="bodyall">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<form action="/mypage/member/updateMyPw" name="pwForm" id="pwForm" method="post" class="form-inline justify-content-end">
	<input type="hidden" value="${memberDog.id }" name="id">
	<table class="table caption-top" style="margin-left: 100; text-align: center; text-align:left;">
		<tr><th>새 비밀번호</th><td>
		<tr><th><input type="password" id="newPassword" name= "password" class="pw" placeholder="비밀번호"></td></tr>
	  
		<tr><th>새 비밀번호 확인</th><td>
	    <tr><th><input type="password" id="password_4"  class="pw" placeholder="비밀번호 확인">
	    <span id="alert-success2" style="display: none;">비밀번호가 일치합니다.</span>
	    <span id="alert-danger2" style="display: none; color: #d92742; font-weight: bold; ">비밀번호가 일치하지 않습니다.</span></th></tr>
		<tr><th><input type="submit" value="수정하기" id="btn" onclick="myPwEdit()" class="btn btn-warning"></th></tr>
	</table>
</form>
</div>
<div id="sidebar"><%@ include file="/WEB-INF/views/include/memberSidebar.jsp" %></div>
</div> 
<script>

    $('.pw').focusout(function () {
        var pwd3 = $("#newPassword").val();
        var pwd4 = $("#password_4").val();
  
        if ( pwd3 != '' && pwd4 == '' ) {
            null;
        } else if (pwd3 != "" || pwd4 != "") {
            if (pwd3 == pwd4) {
                $("#alert-success2").css('display', 'inline-block');
                $("#alert-danger2").css('display', 'none');
            } else {
             /*    alert("비밀번호가 일치하지 않습니다. 비밀번호를 재확인해주세요."); */
                $("#alert-success2").css('display', 'none');
                $("#alert-danger2").css('display', 'inline-block');
                $('#password_4').focus();
            }
        }
    });
    
    
    function myPwEdit(){

         var pwd3 = $("#newPassword").val();
         var pwd4 = $("#password_4").val();
         
         if (pwd3 == pwd4){
        	 alert("비밀번호가 변경되었습니다.")
        	 pwForm.submit();
         } else {
        	 alert("비밀번호를 다시 확인해 주세요.")
         }
    }

    
</script>




<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>