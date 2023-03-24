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
	var getKeyCode = "";
	function ajaxSendCode(){
		var formData = $(".findJoinPassForm").serialize();
		$(".ajaxCodeInput .ajaxMessage").remove();
		$(".ajaxCodeInput").text("전송중...");
		$('#sendCodeBtn').attr("disabled","disabled");
		$.ajax({
			url		:'/main/ajaxPassChkSendCode',
			type	:'POST',
			data	:formData,
			dataType:'json',
			success	:function(results){
				data = results.data;
				$(".ajaxCodeInput .ajaxMessage").remove();
				var str = '<a class="ajaxMessage">입력하신 정보가 잘못되었습니다.</a>';
				if(data>0){
					str	='<div class="col-md-12 ajaxMessage"><br/><a class="ajaxMessage">코드가 발송되었습니다.</a>'+
						'<input type="text" name="mailKey" placeholder="6자리 코드를 입력해주세요"'+
						' class="form-control mailKey" required="required"><br/><button type="submit" class="btn '+
						'btn-outline-secondary submitBtn" id="CodeChkBtn">확인</button><br/></div>';
					$('#sendCodeBtn').text("재전송하기");
					$("#email").attr("disabled","disabled");
					$("#id").attr("disabled","disabled");
					getKeyCode = results.CODE;
				}else if(data==0) {
					str = "메일 발송중 오류가 발생하였습니다.";
					$('#sendCodeBtn').text("재전송하기");
				}
				$('#sendCodeBtn').removeAttr("disabled");
				$(".ajaxCodeInput").text("");
				$(".ajaxCodeInput").append(str);
			},
			error : function(request,status,error){
	            	alert("request.status = "+request.status+"\nmessage = "+request.responseText+"\nerror = "+error);
			}
		});
	}
	function ajaxChkCode(){
		if($(".mailKey").val() == getKeyCode){
			alert('인증성공');
			return true;
		} else{
			alert('코드가 일치하지 않습니다.');
			return false;
		}
/* 		var formData = $(".findJoinPassForm").serialize();
		var result = 0;
		$.ajax({
			url		:'/main/ajaxChkCode',
			type	:'POST',
			async	:false,
			data	:formData,
			dataType:'text',
			success	:function(data){
				result = data;
				}
		});
		if(result==1){
			return true;
		} else {
			$('.ajaxMessage a').text('코드가 일치하지 않습니다.');
			return false;
		} 
		*/
	}
</script>
</head>
<body>
	<div class="mainContents">
		<div class="container col-md-3">
			<br>
			<a class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
				<span class="fs-5 fw-semibold">비밀번호 재설정</span>
				&nbsp
				<span class="pageDocument">비밀번호를 재설정하기 위해 <br>이메일로 발송되는 6자리 코드를 입력 해주세요.</span>
			</a>
			<form action="/main/resetPassForm" method="post" class="findJoinPassForm" onsubmit="return ajaxChkCode()">
				<div class="col-md-12">
					<label for="name" class="form-label">아이디</label><label for="name" class="form-label" id=nameChk></label>
					<input type="text" id="name" class="form-control" name="username" 
							placeholder="6자 이상의 영문,숫자조합" 
							required="required" 
							pattern="^([a-z0-9_]){6,20}$" 
							title="6자 이상 20자 이내로 입력해주세요."/>
				</div><br/>
				<div class="col-md-12">
					<label for="email" class="form-label">이메일:</label>
					<input type="email" id="email" name="memberEmail" placeholder="my@email.com" class="form-control" required="required">
				</div><br/>
				<div class=" col-md-6">
					<button type="button" class="btn btn-outline-secondary submitBtn" onclick="ajaxSendCode()" id="sendCodeBtn">코드전송하기</button><br/>
				</div>
				<div class="ajaxCodeInput"></div>
			</form>
		</div>
	</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>