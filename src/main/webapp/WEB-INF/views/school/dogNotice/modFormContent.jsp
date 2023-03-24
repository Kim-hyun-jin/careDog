<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <div class="container">
		<form action="/school/dogNotice/mod" enctype = "multipart/form-data" method="post" >
			<div class="col-xl-10 my-5 mx-3">
				<span class="fs-2 fw-semibold mx-5 "></span>
			</div>
	  	 <h2>알림장 수정</h2> 
		  	 <input type="hidden" name="noticeNo" value="${dog.noticeNo}">
		  	  <input type="hidden" value="${dog.dogImgPath }" name="dogImgPath">
				<input type="hidden" value="${dog.dogImgName }" name="dogImgName">
			
				<label for="title" class="form-label"></label>
			<p>
			제목
				<input type="text" class="form-control" id="exampleInputPassword1" name="title" value="${dog.title}" readonly>
			<p>
			원생선택
				<select class="form-select" required aria-label="select example" name="dogNo">
					<option value="">원생을 선택하세요</option>
						<option value="${dog.dogNo}">${dog.dogName}</option>
				</select>

		<p>	
		<c:choose>
					<c:when test="${dog.dogImgName == null}">
		  				<img id="dogImgs" class="dogImgs" alt="image" src="<%=context %>/DJimages/기본이미지.png" style="width: 400px;">
					</c:when>
					<c:otherwise>
		  				<img id="dogImgs" class="dogImgs"  alt="image" src="<%=context %>/DJimages/${dog.dogImgName}" style="width: 500px;">
					</c:otherwise>
				</c:choose>
		
			<p> 
			 <div class="mb-3">
		  		<label for="exampleFormControlTextarea1" class="form-label">내용</label>
					<textarea class="form-control" id="exampleFormControlTextarea1" rows="5"
							  name="content" >${dog.content} 
					</textarea>
			</div>
			
			<div>기분</div>
			<p>
			<td>
			<lable>
				<input type="radio"  name="feel" value="좋음"
					<c:if test="${dog.feel eq '좋음'}">checked="checked"</c:if> />좋음 
				<input type="radio"  name="feel" value="보통"
					<c:if test="${dog.feel eq '보통'}">checked="checked"</c:if> />보통 
				<input type="radio"  name="feel" value="나쁨"
					<c:if test="${dog.feel eq '나쁨'}">checked="checked"</c:if> />나쁨 
			</lable>
			</td>
			
			<div>건강</div>
			<p>
			<td>
			<lable>
				<input type="radio"  name="health" value="좋음"
					<c:if test="${dog.health eq '좋음'}">checked="checked"</c:if> />좋음 
				<input type="radio"  name="health" value="보통"
					<c:if test="${dog.health eq '보통'}">checked="checked"</c:if> />보통 
				<input type="radio"  name="health" value="나쁨"
					<c:if test="${dog.health eq '나쁨'}">checked="checked"</c:if> />나쁨 
			</lable>
			</td>
			
			<div>식사량</div>
			<p>
			<td>
			<lable>
				<input type="radio"  name="meal" value="정량"
					<c:if test="${dog.meal eq '정량'}">checked="checked"</c:if> />정량
				<input type="radio"  name="meal" value="많음"
					<c:if test="${dog.meal eq '많음'}">checked="checked"</c:if> />많음
				<input type="radio"  name="meal" value="보통"
					<c:if test="${dog.meal eq '보통'}">checked="checked"</c:if> />보통 
				<input type="radio"  name="meal" value="적음"
					<c:if test="${dog.meal eq '적음'}">checked="checked"</c:if> />적음 
			</lable>
			</td>
			
			<div>배변</div>
			<p>
			<td>
			<lable>
				<input type="radio"  name="condition" value="좋음"
					<c:if test="${dog.condition eq '좋음'}">checked="checked"</c:if> />좋음 
				<input type="radio"  name="condition" value="묽음"
					<c:if test="${dog.condition eq '묽음'}">checked="checked"</c:if> />묽음 
				<input type="radio"  name="condition" value="딱딱함"
					<c:if test="${dog.condition eq '딱딱함'}">checked="checked"</c:if> />딱딱함 
			</lable>
			</td>
			<p>
				<div class="mb-3">
				  <label for="formFileMultiple" class="form-label">첨부파일</label>
				  <input class="form-control" type="file"  name="dogImgs" multiple>
				</div>
			
		<div>등록일</div>
				<p>
					<input type="date"   name="regdate" value="${dog.regdate }" readonly>
		<p>
		<tr><td colspan="2">
		   <input type="submit" value="수정완료하기">
		   </td>
		</tr>
	</table>
</form>
</div>
</body>
</html>