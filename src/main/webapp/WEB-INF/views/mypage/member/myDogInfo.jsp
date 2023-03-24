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
  <div id="mydogList"  style="width: 1300px;">
	<span class="fs-2 fw-semibold my-5 mx-5">반려견 정보 (${dog.dogName })</span>
	<input type="hidden" value="${dog.dogImgPath }" name="dogImgPath">
	<input type="hidden" value="${dog.dogImgName }" name="dogImgName">
	<table class="table caption-top my-5">
		<tr><th>반려견명</th><td>${dog.dogName }</td>
		<th rowspan="4"><c:choose>
				<c:when test="${dog.dogImgName == null}">
  					<img id="dogImgs" class="dogImgs" alt="image" src="<%=context %>/images/mydogImg.png" style="width: 100px;">
				</c:when>
				<c:otherwise>
  					<img id="dogImgs" class="dogImgs"  alt="image" src="<%=context %>/images/${dog.dogImgName}" style="width: 100px;">
				</c:otherwise>
			</c:choose></th></tr>
		<tr><th>견종명</th><td>${dog.dogKind }</td>
		<tr><th>성별</th><td>${dog.dogGender == '0' ? '수컷' : '암컷'}</td></tr>
		<tr><th>나이</th><td>${dog.dogAge }</td></tr>
		<tr>
			<th>몸무게</th><td>${dog.dogWeight }</td>
			<th>특이사항</th>
		</tr>
		<tr><th>중성화여부</th><td>${dog.deSexibng == '0' ? 'Y' :'N' }</td>
			<td rowspan="2">${dog.dogMemo}</td></tr>
		<tr><th>유치원등록일</th><td>${dog.scStartdate }~${dog.scEnddate }</td>
		</tr>
		<%-- <tr><th>${dog.dogImg}</th></tr> --%>
	</table>
   <input type="button" value="목록" onclick="location.href='/mypage/member/myDogList'" class="btn btn-warning"">
   <input type="button" value="수정" onclick="location.href='/mypage/member/UpdMyDogInfoForm?dogNo=${dog.dogNo}'" class="btn btn-warning">
   <input type="button" value="삭제" onclick="location.href='/mypage/member/delMyDogInfo?dogNo=${dog.dogNo}'" class="btn btn-warning">
	</div>
	<div id="side"><%@ include file="/WEB-INF/views/include/memberSidebar.jsp" %></div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>