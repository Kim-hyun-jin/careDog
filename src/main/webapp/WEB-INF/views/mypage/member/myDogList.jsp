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
	
	#mydogList {
		float: right;
	}
	
	#side{
		float: left;
	}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
 integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</head>
<body>
  <div id="allContent">
  <div id="mydogList" style="width: 1300px;" >
  <input type="button" value="반려견등록" onclick="location.href='/mypage/member/creMyDogForm'" 
  	     class="btn btn-warning mt-3" style="float: right;">
  	<span class="fs-2 fw-semibold my-5 mx-5">반려견 정보</span>
  <table class="table caption-top my-5" >
  	<caption>나의 강아지_리스트</caption>
  	<thead>
  	 		<tr>
                <th scope="col">번호</th>
                <th scope="col">반려견명</th>
              </tr>
    </thead>
  	<c:forEach var="dog" items="${myDogList}">
  	<tr><td>${dog.dogNo}</td>
  	<td><a href="/mypage/member/myDogInfo?dogNo=${dog.dogNo}">${dog.dogName}</a></td></tr>
  	</c:forEach>
  </table>
  </div>
  <div id="side"><%@ include file="/WEB-INF/views/include/memberSidebar.jsp" %></div>
 </div>
 
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>