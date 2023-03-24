<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/main/findJoinData.css" rel="styleSheet" type="text/css">
<script type="text/javascript">
	var passCheck = false;
	function passChk(passChk,pass){
		if(passChk==pass && pass.length>7 && pass.length<=20){
			passCheck=true;
			submitChk();
			$('#passwdchk *').remove();
			$('#passwdchk').append("<a style='color: green;'>&nbsp&nbsp&nbsp&nbsp일치</a>");
		} else if(pass.length<8){
			passCheck=false;
			submitChk();
			$('#passwdchk *').remove();
			$('#passwdchk').append("<a style='color: red;'>&nbsp&nbsp&nbsp&nbsp8자 이상</a>");
		} else if(pass.length>19){
			passCheck=false;
			submitChk();
			$('#passwdchk *').remove();
			$('#passwdchk').append("<a style='color: red;'>&nbsp&nbsp&nbsp&nbsp20자 미만</a>");
		} else {
			passCheck=false;
			submitChk();
			$('#passwdchk *').remove();
			$('#passwdchk').append("<a style='color: red;'>&nbsp&nbsp&nbsp&nbsp불일치</a>");
		}
	}
	function submitChk(){
		if(passCheck==true){
			$('#resetPass').removeAttr('disabled'); 
		} else{
			$('#resetPass').attr('disabled','disabled'); 
		}
	}
	function submitResetPass(){
		var formData = $(".findJoinPassForm").serialize();
		$.ajax({
			url		:'/main/resetPass',
			type	:'POST',
			data	:formData,
			dataType:'text',
			success	:function(data){
				if(data>0){
					alert('비밀번호 변경에 성공하셨습니다.');
					location.href='/main/login';
				} else {
					alert('비밀번호 변경에 실패했습니다.');
				}
			}
		});
		return false;
	} 
</script>
</head>
<body>
	<div class="mainContents">
		<div class="container col-md-3" onsubmit="return submitResetPass()">
			<br>
			<a class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
				<span class="fs-5 fw-semibold">비밀번호 재설정</span>
				&nbsp
				<span class="pageDocument">새로운 비밀번호를 설정해주세요</span>
			</a>
			<form method="post" class="findJoinPassForm">
				<div class="col-md-12">
					<label for="inputPassword4" class="form-label">새 비밀번호</label>
					<input type="password" class="form-control" id="inputPassword4" name="password" 
							pattern="^(?=.*[a-z])(?=.*\d)[a-z\d!@#$%^&*]{8,20}$"
							title="영문(소문자),숫자를 조합해주세요 8~20자"
                    		required="required"
                    		placeholder="영문(소문자),숫자조합 8~20자"
							onkeyup="passChk(passchk.value, password.value)">
				</div><br/>
				<div class="col-md-12">
					<label for="inputPassword4" class="form-label">새 비밀번호 확인</label><label for="name" class="form-label" id=passwdchk></label>
					<input type="password" class="form-control" id="inputPassword4" placeholder="비밀번호 확인" name="passchk" onkeyup="passChk(passchk.value, password.value)">
				</div><br/>
				<div class=" col-md-6">
					<button type="submit" class="btn btn-outline-secondary submitBtn" id="resetPass" disabled="disabled">비밀번호변경</button><br/>
				</div>
				<div class="ajaxCodeInput"></div>
			</form>
		</div>
	</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>