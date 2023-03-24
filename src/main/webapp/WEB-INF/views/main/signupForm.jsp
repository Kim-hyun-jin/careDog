<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Signin Form</title>
	<style type="text/css">
		.redbold{
			color: red;
			font-size:large;
		}
	</style>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script type="text/javascript">
		var duplCheck = false;
		var passCheck = false;
		var emailCheck= false;
		var emailKey="";
		function nameChkFalse(){
			duplCheck = false;
			$('#nameChk *').remove();
			$('#nameChk').append("<a style='color: red;'>&nbsp&nbsp&nbsp&nbsp중복체크를 실행해주세요.</a>");
			submitChk();
		}
		function submitChk(){
			if(duplCheck==true && passCheck==true && emailCheck==true){
				$('#signUpSubmit').removeAttr('disabled'); 
			} else{
				$('#signUpSubmit').attr('disabled','disabled'); 
			}
		}
		function ajaxJoinMember(){
			var formData = $("#joinForm").serialize();
			$.ajax({
	            url : "/ajaxJoinMember",
	            type : 'POST', 
	            data : formData,
	            dataType : 'text',
	            async	: false,
	            success : function(data) {
	            	var result = data;
	                if(result>=1){
	                	alert("가입성공!");
	                	location.href="/main/login";
	                } else {
	                	alert("가입실패...");
	                };
	            },
	            error : function(request,status,error){
	            	alert("request.status = "+request.status+"\nmessage = "+request.responseText+"/nerror = "+error);
	            }
			});
			return false;
		}
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
		function nameDupChk(vName){
			if(vName.match('^([a-z0-9_]){6,20}$')){
				$.ajax({
					url		: "/ajaxJoinChkMemberName",
					data	: {username:vName},
					dataType: 'text',
					async	: false, 
					success	: function(data){
						var result = data;
						if(result>=1){
							duplCheck = false;
							$('#nameChk *').remove();
							$('#nameChk').append("<a style='color: red;'>&nbsp&nbsp&nbsp&nbsp중복된 아이디입니다.</a>");
							submitChk();
						} else{
							duplCheck = true;
							$('#nameChk *').remove();
							$('#nameChk').append("<a style='color: green;'>&nbsp&nbsp&nbsp&nbsp사용 가능한 아이디입니다.</a>");
							submitChk();
						}
					}
				});
			} else{
				$('#nameChk *').remove();
				$('#nameChk').append("<a style='color: green;'>&nbsp&nbsp&nbsp&nbsp6자 이상의 영문,숫자조합으로 작성해주세요.</a>");
			}
		}
		function daumPostSearch(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		            $("#inputAddress").val("("+data.zonecode+") "+data.address)
		        }
		    }).open();
		}
		function addressSet(main,detail){
			if(main==""){
				alert("주소검색을 먼저 실행 해 주세요.");
				$('#inputAddress2').val("");
			} else{
				$('#memberAddress').val(main+", "+detail);
			}
		}
		function ajaxMailSend(){
			$('.ajaxMailSendBtn').text('전송중...');
			$('.ajaxMailSendBtn').attr('disabled','disabled');
			$('#email').attr('readonly','readonly');
			$.ajax({
				url		: "/signupForm/ajaxMailSend",
				data	: {email:$('#email').val()},
				dataType: 'text',
				success	: function(data){
					if(data!=null && data.length!=0){
						$('.ajaxMailSendBtn').removeAttr('disabled');
						$('.ajaxMailSendBtn').text('재전송');
						emailKey=data;
					} else {
						alert('메일전송에 실패했습니다...');
						$('.ajaxMailSendBtn').removeAttr('disabled');
						$('#email').removeAttr('readonly');
						$('.ajaxMailSendBtn').text('전송');
					}
				}
			});
		}
		function mailChk(){
			if($('#mailKey').val()==emailKey){
				$('#mailChkBtn').attr('disabled','disabled').text('성공!');
				$('.ajaxMailSendBtn').attr('disabled','disabled');
				$('#mailKey').attr('disabled','disabled').val('인증에 성공했습니다.');
				emailCheck=true;
				submitChk();
			} else {
				alert("인증번호가 올바르지 않습니다.");
			}
		}
	</script>
</head>
<body>
<div id="main">
	<div class="container">
		<div class="text-center my-5">
			<h2>회원가입</h2>
            <p class="lead">궁금한 점이 있으신가요?</p>
            <a href="/customerService/QA/QA"><button class="btn btn-warning" type="button">자주묻는 질문 바로가기</button></a>
        </div>
        <div class="row justify-content-center my-5">
            <div class="col-lg-6">
                <form class="row g-3" id="joinForm" onsubmit="return ajaxJoinMember()">
                	<div class="col-md-10">
	                    <label for="name" class="form-label"><b class="redbold">*</b>아이디</label><label for="name" class="form-label" id=nameChk></label>
	                    <input type="text" class="form-control" name="username" 
	                    		placeholder="6자 이상의 영문,숫자조합" 
	                    		required="required" 
	                    		pattern="^([a-z0-9_]){6,20}$" 
	                    		title="6자 이상 20자 이내로 입력해주세요."
	                    		onkeyup="nameChkFalse(username.value)"/>
                    </div>
                	<div class="col-md-2">
                		 <label for="name" class="form-label"> &nbsp &nbsp</label>
                		 <button type="button" class="btn btn-warning" onclick="nameDupChk(username.value)">중복체크</button>
                	</div>
                    <div class="col-md-6">
	                    <label for="inputPassword4" class="form-label"><b class="redbold">*</b>비밀번호</label>
	                    <input type="password" class="form-control" id="inputPassword4" name="password" 
	                    		pattern="^(?=.*[a-z])(?=.*\d)[a-z\d!@#$%^&*]{8,20}$"
	                    		title="영문(소문자),숫자를 조합해주세요 8~20자"
	                    		required="required"
	                    		placeholder="영문(소문자),숫자조합 8~20자"
	                    		onkeyup="passChk(passchk.value, password.value)">
                    </div>
                    <div class="col-md-6">
	                    <label for="inputPassword4" class="form-label"><b class="redbold">*</b>비밀번호 확인</label><label for="name" class="form-label" id=passwdchk></label>
	                    <input type="password" class="form-control" placeholder="비밀번호 확인" name="passchk" onkeyup="passChk(passchk.value, password.value)">
                    </div>
                	<div class="col-md-6">
	                    <label for="name" class="form-label"><b class="redbold">*</b>이름</label>
	                    <input type="text" id="name" name="memberName" placeholder="홍길동" class="form-control" required="required">
                    </div>
                	<div class="col-md-6">
	                    <label class="form-label"><b class="redbold">*</b>연락처</label>
	                    <input type="text" name="memberCall" placeholder="010-0000-0000" class="form-control" required="required"
	                    		maxlength="13" pattern="[0-9]{2,3}-[0-9]{3,4}-[0-9]{3,4}">
                    </div>
                	<div class="col-md-4">
	                    <label for="email" class="form-label"><b class="redbold">*</b>이메일</label>
	                    <input type="email" id="email" name="memberEmail" placeholder="my@email.com" class="form-control" required="required">
                    </div>
                	<div class="col-md-2">
	                    <label for="email" class="form-label">&nbsp</label>
	                    <button type="button" onclick="ajaxMailSend()" class="form-control btn btn-outline-secondary submitBtn ajaxMailSendBtn">전송</button>
                    </div>
                	<div class="col-md-4">
	                    <label class="form-label"><b class="redbold">*</b>인증번호</label>
	                    <input type="text" name="mailKey" id="mailKey" placeholder="6자리숫자" class="form-control">
                    </div>
                	<div class="col-md-2">
	                    <label for="email" class="form-label">&nbsp</label>
	                    <button type="button" id="mailChkBtn" onclick="mailChk()" class="form-control btn btn-outline-secondary submitBtn">인증</button>
                    </div>
                    <div class="col-12">
	                    <label for="inputAddress" class="form-label">주소검색</label>
	                    <input type="text" class="form-control" id="inputAddress" placeholder="도로명주소검색" readonly="readonly" onclick="daumPostSearch()">
                    </div>
                    <div class="col-12">
	                    <label for="inputAddress2" class="form-label">상세주소</label>
	                    <input type="text" class="form-control" id="inputAddress2" placeholder="상세주소를 입력해주세요" onkeyup="addressSet(inputAddress.value,inputAddress2.value)">
	                    <input type="hidden" value="" name="memberAddress" id="memberAddress">
                    </div>
                    <div class="col-12">
                    	<button type="submit" class="btn btn-warning" id="signUpSubmit" disabled="disabled">가입</button>
                    </div>
                  </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>