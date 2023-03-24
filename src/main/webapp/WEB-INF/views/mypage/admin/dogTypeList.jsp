<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		padding-left: 30px;
	}
	
	#side{
		float: left;
	}
	
	a {
	text-decoration-line:none;
	}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- cdn 방식 제이쿼리 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
 integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</head>
<body>
<div id="allContent">
	<div id="container" style="width: 1000px;">
	<span class="fs-2 fw-semibold my-5 mx-5">품종관리(관리자)</span>
	<form action="/mypage/admin/delDogType">
		<input type="submit" value="삭제" style="float: right;" class="btn btn-warning mx-2"> 
		<input type="button" value="품종등록"  style="float: right;"
			   onclick="location.href='/mypage/admin/creDogTypeForm'" class="btn btn-warning">
		<c:set var="num" value="${page.total-page.start+1 }"></c:set>
		<table class="table caption-top">
		  	<thead>
	           <tr>
	              <th scope="col">#</th>
	              <th scope="col">견종명</th>
	              <th scope="col">반려견사이즈</th>
	              <th scope="col">반려견정보</th>
	            </tr>
	        </thead>
	        <tbody>
			<c:forEach var="dogType" items="${dogTypeList}">
				<tr>
				<td><input type="radio" name="dogKind" value="${dogType.dogKind}" >
				<td>${dogType.dogKind}</td>
				<td>${dogType.dogSize}</td>
				<td>${dogType.dogInfo}</td></tr>
			</c:forEach>
			</tbody>
		</table>
		
		<nav aria-label="Page navigation example">
	        <ul class="pagination justify-content-center">
				<c:forEach var="i" begin="${page.startPage}" end="${page.endPage}" >
					<li class="page-item">
						<a  class="page-link" href="/mypage/admin/dogTypeList?currentPage=${i}" >${i}</a>
				 	</li>
				</c:forEach>
			</ul>
		</nav>
	</form>	
	</div>
	<div id="side"><%@ include file="/WEB-INF/views/include/adminSidebar.jsp" %></div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>