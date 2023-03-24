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
	<div id="mydogList" style="width: 1300px;">
	<form action="/mypage/member/creMyDog" enctype = "multipart/form-data" method="post" >
		<input type="hidden" name="dogNo" value="${dog.dogNo}">
		<input type="hidden" name="id" value="${dog.id}">
		<input type="hidden" name="joinCheck" value="0">
	  	<span class="fs-2 fw-semibold my-5 mx-5">나의 반려견 등록</span>
		<table class="table caption-top my-5">
			<tr><th>반려견명</th>
			<td><input type="text" name="dogName"></td>
			<th rowspan="3">
			<c:choose>
					<c:when test="${dog.dogImgName == null}">
	  					<img id="dogImgs" class="dogImgs" alt="image" src="<%=context %>/images/mydogImg.png" style="width: 100px;">
					</c:when>
					<c:otherwise>
	  					<img id="dogImgs" class="dogImgs"  alt="image" src="<%=context %>/images/${dog.dogImgName}" style="width: 100px;">
					</c:otherwise>
			</c:choose><p>
			<input type="file" name="dogImgs">
			</th></tr>
			<tr><th>견종명</th>
			<td><select name="dogKind">
			<c:forEach var="dogType" items="${dogType}">
					<option value="${dogType.dogKind}">${dogType.dogKind}</option>
			</c:forEach>
			</select></td>
			</tr>
			<tr>
				<th>성별</th>
				<td> <input type="radio" name="dogGender" value="0">수컷
					 <input type="radio" name="dogGender" value="1">암컷
				</td>
			</tr>
			<tr><th>나이</th>
			<td><input type="number" name="dogAge" ></td>
			<th>특이사항</th></tr>
			<tr><th>몸무게</th>
			<td><input type="number" name="dogWeight"  ></td>
			<td rowspan="3">
			<textarea rows="5" cols="20" name="dogMemo"></textarea>
			</td>
			</tr>
			<tr><th>중성화여부</th>
			<td>
			<label>	<input type="radio"  name="deSexibng" value="0">Y 
				<input type="radio"   value="1" name="deSexibng">N </label>
			</td>
			</tr>
		</table>
		   <input id="submit" type="submit" value="등록" class="btn btn-warning">
		   <input type="button" value="목록" 
					onclick="location.href='/mypage/member/myDogList'" class="btn btn-warning">
		   <!-- <input type="button" value="삭제" 
					onclick="location.href='/mypage/member/myDogList'" class="btn btn-warning"> -->
	</form>
	</div>
	<div id="side"><%@ include file="/WEB-INF/views/include/memberSidebar.jsp" %></div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>