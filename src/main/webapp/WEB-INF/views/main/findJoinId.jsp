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
	function ajaxEnableChk(){
		var formData = $(".findJoinIdForm").serialize();
		$.ajax({
			url		:'/main/ajaxEnableChk',
			type	:'POST',
			data	:formData,
			dataType:'json',
			success	:function(data){
				$(".ajaxFindMessageDiv .ajaxFindMessage").remove();
				var str = '<a class="ajaxFindMessage">가입된 회원정보를 찾을 수 없습니다.</a>';
				if(data!=null && data.length!=0){
					var str = '<a class="ajaxFindMessage">회원정보가 ['+data.length+']건 확인되었습니다.</a>';
					var str2 = '<ul class="list-group ajaxFindMessage col-md-4">';
					$.each(data,function(index,item){
						str2 += '<li class="list-group-item">'+item.username+'</li>';
					});
					str2+='</ul><a href="/main/findJoinPass"><b>비밀번호</b> 찾으러 가기</a>';
					$(".ajaxFindMessageDiv").append(str);
					$(".ajaxFindMessageDiv").append(str2);
				} else {
					$(".ajaxFindMessageDiv").append(str);
				}
			},
			error : function(request,status,error){
	            	alert("request.status = "+request.status+"\nmessage = "+request.responseText+"\nerror = "+error);
			}
		});
	}
</script>
</head>
<body>
	<div class="mainContents">
		<div class="container col-md-3">
			<br>
			<a class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
				<span class="fs-5 fw-semibold">이메일로 아이디 찾기</span>
			</a>
			<form action="/main/findJoinId" method="post" class="findJoinIdForm">
				<div class="col-md-12">
					<label for="name" class="form-label">이름:</label>
					<input type="text" id="name" name="memberName" placeholder="홍길동" class="form-control" required="required">
				</div><br/>
				<div class="col-md-12">
					<label for="email" class="form-label">이메일:</label>
					<input type="email" id="email" name="memberEmail" placeholder="my@email.com" class="form-control" required="required">
				</div><br/>
				<div class=" col-md-6">
					<button type="button" class="btn btn-outline-secondary submitBtn" onclick="ajaxEnableChk()">아이디찾기</button>
				</div>
				<div class="ajaxFindMessageDiv"><br/></div>
			</form>
		</div>
	</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>