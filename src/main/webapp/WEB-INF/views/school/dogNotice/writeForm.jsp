<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림장 글쓰기</title>


</head>

<body>
	<div class="container">
		<form action="/school/dogNotice/write" method="post" enctype = "multipart/form-data">
		
			<input type="hidden" name="id" value="${writerId}" >
			<input type="hidden" class="form-control" id="teacherId" name="teacherId" value="${member.teacher}" readonly>
			
			
			<h1>알림장 글쓰기</h1>

			<label for="title" class="form-label"></label>
			<p>
			 <label for="exampleInputPassword1" class="form-label">제목</label>
				<c:if test="${roleOnlyTeacher }">		
				<input type="text" class="form-control" name="title" value="유치원에서 가정으로" readonly></c:if>	
				<c:if test="${roleOnlyMember }">		
				<input type="text" class="form-control" name="title" value="가정에서  유치원으로" readonly></c:if>	
		
				<p>
		<label for="selectDog" class="form-label">원생선택</label>
            <select class="form-select my-2" required aria-label="select example" name="dogNo" id="selectDog">
               <option value="">원생을 선택하세요</option>
               <c:forEach var="dog" items="${dogList}">
                  <option value="${dog.dogNo}">${dog.dogName}</option> <%-- value="${dog.id}" --%>
               </c:forEach>
            </select>
				<p>

				   <label for="teacherName" class="form-label">선생님</label>
         <%--    <input type="text" class="form-control my-3" value="선생님이름 : ${member.memberName}" id="teacherName" name="memberName" readonly> --%>
         	<c:if test="${roleOnlyTeacher }">		
				<input type="text" class="form-control" name="memberName" value="선생님 이름: ${member.memberName }" id="teacherName" readonly></c:if>	
				<c:if test="${roleOnlyMember }">	
				<input type="text" class="form-control" name="tname" value="선생님 이름: ${member.tname }" id="teacherName" readonly></c:if>	
         <p>
			
			<c:choose>
								<c:when test="${dog.dogImgName == null}">
		  				<img id="dogImgs" class="dogImgs" alt="image" src="<%=context %>/img/school/기본이미지.png" style="width: 400px;">
					</c:when>
					<c:otherwise>
		  				<img id="dogImgs" class="dogImgs"  alt="image" src="<%=context %>/DJimages/${dog.dogImgName}" style="width: 500px;">
					</c:otherwise>
				</c:choose>
				
		
			<div class="mb-3">
		  		<label for="exampleFormControlTextarea1" class="form-label">내용</label>
					<textarea class="form-control" id="exampleFormControlTextarea1" rows="3"
							  name="content">
					</textarea>
			</div>
			
			<div>기분</div>
			<p>
			<td>
			<lable>
				<input type="radio"  name="feel"  value="좋음">좋음
					<input type="radio"  name="feel"  value="보통" >보통
						<input type="radio"  name="feel"  value="나쁨">나쁨 
			
			</lable>
			</td>
			<div>건강</div>
			<p>
			<td>
			<lable>
				<input type="radio"  name="health"  value="좋음">좋음
				<input type="radio"  name="health"  value="보통">보통 
				<input type="radio"  name="health"  value="나쁨">나쁨
	
			</lable>
			</td>
			<div>식사량</div>
			<p>
			<td>
			<lable>
				<input type="radio"  name="meal" value="정량">정량 
				<input type="radio" name="meal"  value="많음">많음
				<input type="radio"  name="meal" value="보통">보통
				<input type="radio"  name="meal" value="적음">적음
	
			</lable>
			</td>
			
			<div>배변</div>
			<p>
			<td>
			<lable>
				<input type="radio" name="condition" value="좋음">좋음
				<input type="radio" name="condition"  value="묽음">묽음
				<input type="radio"  name="condition" value="딱딱함">딱딱함
		
			</lable>
			</td>	
				<!-- 사진첨부 -->
			
				<div class="mb-3">
				  <label for="formFileMultiple" class="form-label">첨부파일</label>
				  <input class="form-control" type="file"  name="dogImgs" multiple>
				</div>
				
				<p>
			<div>등록일</div>
				<p>
					<input type="date"   name="regdate" value="${dog.regdate }" >

			
			<p>
				<button type="submit" class="btn btn-outline-secondary">등록</button>
		</form>
				
			<p>
			<a href="/school/dogNotice/main">
				<button type="button" class="btn btn-outline-secondary">목록으로</button>
			</a>
		
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>