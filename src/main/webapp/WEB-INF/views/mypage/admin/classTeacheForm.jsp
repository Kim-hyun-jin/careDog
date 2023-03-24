<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">

   #all {
      position: absolute;
      top: 120px;
      
   }
   
   #bodyall {
      float: right;
      padding-left: 260px;
      padding-top: 100px;
   }
   
   #sidebar{
      float: left;
   }
</style>
<body>
<div id="all">
<div id="bodyall">
	<form action="/mypage/admin/updateTeacher">
	<input type="hidden" value="${member.id}" name="id">
	<%-- 선택된 아이디 : ${member.id} --%>
	<table class="table caption-top">
	<tr><th>담임 선생님</th>
	<td>
	<select name="teacher">
	<c:forEach var="teacher" items="${teacher}">
		<option value="${teacher.id}">${teacher.memberName}</option>
	</c:forEach>
	</select>
	</td>
	<td><input style="bottom: 6px;" type="submit" value="배정하기" class="btn btn-warning"></td>
	</tr>
	</table>
	</form>
</div>
<div id="sidebar"><%@ include file="/WEB-INF/views/include/adminSidebar.jsp" %></div>
</div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>