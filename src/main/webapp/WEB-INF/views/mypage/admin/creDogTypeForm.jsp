<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!DOCTYPE html>
<html>
<head>
<style type="text/css">

	#allContent {
		position: absolute;
		top: 120px;
	}
	
	#container {
		float: right;
	}
	
	#side{
		float: left;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="allContent">
	<div id="container" style="width: 1000px;">
	<span class="fs-2 fw-semibold my-5 mx-5">애견 품종등록</span>
	<form action="/mypage/admin/creDogType">
	 <table class="table caption-top my-5">
	  	<tr><th>견종명</th><td><input type="text" name="dogKind"></td></tr>
	  	<tr>
	  		<th>반려견 사이즈</th>
		  	<td>
		  		<input type="radio" name="dogSize" value="소">소형견
		  		<input type="radio" name="dogSize" value="중">중형견
		  		<input type="radio" name="dogSize" value="대">대형견
		  	</td>
		</tr>
		<tr>
	  		<th>반려견 정보</th>
	  		<td><textarea rows="5" cols="30" name="dogInfo"></textarea></td>
	  	<tr>
	  		<th rowspan="2">
	  			<input type="submit" value="등록" class="btn btn-warning">
				<input type="button" value="목록"  onclick="location.href='/mypage/admin/dogTypeList'" class="btn btn-warning">
			</th>
		</tr>
	  </table>
	 </form>
	</div>
	<div id="side"><%@ include file="/WEB-INF/views/include/adminSidebar.jsp" %></div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>