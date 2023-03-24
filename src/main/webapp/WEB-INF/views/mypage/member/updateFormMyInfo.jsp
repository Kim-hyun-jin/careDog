<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<% String context2 = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>

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

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
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

</script>

<meta charset="UTF-8">
<title>admin</title>
</head>
<body>
<div id="all">
<div id="bodyall">
	<form action="/mypage/member/updateMyInfo" method="post" class="form-inline justify-content-end">
		<input type="hidden" name="id" value="${memberDog.id }">
		<table class="table caption-top" style="margin-left: 100; text-align: center; text-align:left;">
			<tr><th>회원번호</th><td>${memberDog.id }</td></tr>
			<tr><th>아이디</th><td>${memberDog.username }</td></tr>
			<tr><th>이름</th><td>
				<input type="text" name="memberName" required="required" 	value="${memberDog.memberName }"></td></tr>
			<tr><th>연락처</th><td>
				<input type="text" name="memberCall" required="required"  		value="${memberDog.memberCall }">
			<tr><th>이메일</th><td>
				<input type="text" name="memberEmail" required="required" 	value="${memberDog.memberEmail }"></td></tr>
			<tr><th>주소</th><td>
				<input type="text" value="${memberDog.memberAddress }" readonly="readonly"></td></tr>
			<div class="col-12">
	        	<tr><th><label for="inputAddress" class="form-label">주소검색</label></th><td>
	            <input type="text" class="form-control" id="inputAddress" placeholder="도로명주소검색" readonly="readonly" onclick="daumPostSearch()">
            </div>
            <div class="col-12">
	        	<tr><th><label for="inputAddress2" class="form-label">상세주소</label></th><td>
	            <input type="text" class="form-control" id="inputAddress2" placeholder="상세주소를 입력해주세요" onkeyup="addressSet(inputAddress.value,inputAddress2.value)">
	            <input type="hidden" value="${memberDog.memberAddress }" name="memberAddress" id="memberAddress">
            </div>
                    
			<tr><td colspan="2">
				<input type="submit" value="확인" class="btn btn-warning"	>
				</td>
			</tr>			
		</table>

	</form>
</div>
<div id="sidebar"><%@ include file="/WEB-INF/views/include/memberSidebar.jsp" %></div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>