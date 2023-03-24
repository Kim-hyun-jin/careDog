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

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="all">
<div id="bodyall">

		<span class="fs-2 fw-semibold my-5 mx-5">회원관리 (관리자)</span>

	<form class="form-inline justify-content-end" style="overflow:overlay; width:1400px; height:400px;">
		<!-- <h1 style=" width:60%; height: 100px; margin: auto; text-align: center; text-align:left;">회원 관리</h1> -->
		<table class="table caption-top" style="margin-left: 100; text-align: center; text-align:left;">
	
				<tr><th>회원번호</th><th>이름</th><th>회원아이디</th><th>주소</th><th>이메일</th><th>회원등급</th></tr>
				<c:forEach var="member" items="${selMemberDogList}">
					<tr><td>${member.id }</td>
						<td><a href="detailMemberDog?id=${member.id}">${member.memberName }</a></td>
						<td>${member.username }</td><td>${member.memberAddress }</td>
						<td>${member.memberEmail }</td><td>${member.role }</td></tr>
				</c:forEach>
		</table>	
	</form>
</div>
<div id="sidebar"><%@ include file="/WEB-INF/views/include/adminSidebar.jsp" %></div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>

</body>
</html>