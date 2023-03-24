<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

function delChk(){
	if(confirm("알림장을 삭제하세요?\n삭제한 알림장은 복구가 불가능 해요 ㅠ ")){
		location.href="/school/dogNotice/delete?noticeNo="+${dog.noticeNo}
	}                  
}

</script>

<meta charset="UTF-8">
<title>알림장보기</title>
</head>

<body>
	<div class="container">
	<!-- 	<form action="/school/dogNotice/write" method="post" name="frm"> -->
			<form action="/school/dogNotice/detailContent2" method="post" name="frm">

			<h1>알림장 보기</h1>
				<input type="hidden" value="${dog.dogImgPath }" name="dogImgPath">
				<input type="hidden" value="${dog.dogImgName }" name="dogImgName">
				
			<label for="title" class="form-label"></label>
			<p>
				<input type="text" class="form-control" id="exampleInputPassword1"  readonly="readonly" name="title" value="${dog.title}" >
				<p>
				<select class="form-select" required aria-label="select example">
				
					<!-- <option value="">원생을 선택하세요</option> -->
						<option value="${dog.dogNo}">${dog.dogName}</option>
				</select>
				<p>
		 		<select class="form-select" required aria-label="select example">
						<option value="${dog.id}" readonly>${dog.tname}선생님</option>
				</select>  
	
				<p>
				
		<th rowspan="4">
			<c:choose>
				<c:when test="${dog.dogImgName == null}">
  					<img id="dogImgs" class="dogImgs" alt="image" src="<%=context %>/DJimages/기본이미지.PNG" style="width: 400px;">
				</c:when>
				<c:otherwise> 
  					<img id="dogImgs" class="dogImgs"  alt="image" src="<%=context %>/DJimages/${dog.dogImgName}" style="width: 400px;">
				</c:otherwise>
			</c:choose>
		</th>
			<p>
			<div>내용</div>
			<p>
				<div><textarea cols="100" rows="5"
					placeholder="Required example textarea" required="required" name="content" readonly>${dog.content}</textarea></div>
			
		         <div>기분</div>
		         <p>
		  			 <button type="button" class="btn btn-warning" name="feel">${dog.feel}</button>
		         
		         <div>건강</div>
		         <p>
		            <button type="button" class="btn btn-warning" name="health">${dog.health}</button>
		            
		         <div>식사량</div>
		         <p>
		             <button type="button" class="btn btn-warning" name="meal">${dog.meal}</button>
		            
		         <div>배변</div>
		         <p>
		        	<button type="button" class="btn btn-warning" name="condition">${dog.condition}</button>
		            
				<div>등록일</div>
				<p>
					<input type="date"   name="regdate" value="${dog.regdate }" readonly>
				
				
			<p>
				<tr>
				<td colspan="2">
				    <input type="button" value="목록" 
						onclick="location.href='/school/dogNotice/main'">
					<input type="button" value="수정" 
						onclick="location.href='/school/dogNotice/modFormContent?noticeNo=${dog.noticeNo}'">
					<!-- 	<button type="button" class="btn btn-outline-secondary deleteBtn" >삭제</button> -->

						<input type="button" value="삭제" onclick="delChk()">
							<%-- <input type="button" value="삭제" 
						onclick="location.href='/school/dogNotice/delete?noticeNo=${dog.noticeNo}'">  --%>
				</td>
		</tr>
		
		<%-- <div class="modButtons detailBtnsRight">
					<c:choose>
						<c:when test="${roleMember || principal.id == dog.id }">
							<input type="button" value="삭제" onclick="delChk()">
							<c:if test="${principal.id == dog.id }">
								<input type="button" value="수정" onclick="location.href='/school/dogNotice/modFormContent?noticeNo=${dog.noticeNo}'">
							</c:if>
						</c:when>
					</c:choose>
				</div> --%>
		
		</form>
	</div>
	
<!-- 	<script type="text/javascript">
		const formObj = document.querySelector("form");
		
		document.querySelector(".deleteBtn").addEventListner("click" , function(e){
			e.preventDefault();
			e.stopPropagation();
			
			if(window.confirm("알림장을 삭제하시겠습니까? ")){
				formObj.action = "/school/dogNotice/delete?noticeNo="+ ${dog.noticeNo};
				formObj.method = "post";
				formObj.submit();
			}
		}, false);
	
	</script>  -->
	
	
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>