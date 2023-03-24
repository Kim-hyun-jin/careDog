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
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
 integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<body>
<div id="allContent">
<div id="mydogList" style="width: 1300px;">
<form action="/mypage/member/UpdMyDogInfo" enctype = "multipart/form-data"   method="post">
   <span class="fs-2 fw-semibold my-5 mx-5">반려견 정보수정(${dog.dogName })</span>
   <input type="hidden" value="${dog.dogImgPath }" name="dogImgPath">
   <input type="hidden" value="${dog.dogImgName }" name="dogImgName">
   <input type="hidden" name="dogNo" value="${dog.dogNo}">
   <table class="table caption-top my-5" >
      <tr>
         <th>반려견명</th>
         <td><input type="text" name="dogName" value="${dog.dogName }" ></td>
         <th rowspan="4">
            <c:choose>
               <c:when test="${dog.dogImgName == null}">
                    <img id="dogImgs" class="dogImgs" alt="image" src="<%=context %>/images/mydogImg.png" style="width: 100px;">
               </c:when>
               <c:otherwise>
                    <img id="dogImgs" class="dogImgs"  alt="image" src="<%=context %>/images/${dog.dogImgName}" style="width: 100px;">
               </c:otherwise>
            </c:choose>
            <input type="file" name="dogImgs">
         </th>
      </tr>
      <tr>
         <th>견종명</th>
         <td><input type="hidden" name="dogKind"  value="${dog.dogKind }" >${dog.dogKind }</td>
      </tr>
      <tr>
         <th>성별</th>
         <td> ${dog.dogGender == '0' ? '수컷' : '암컷'}</td>
      </tr>
      <tr>
         <th>나이</th>
         <td><input type="hidden" name="dogAge" value="${dog.dogAge}" >${dog.dogAge}</td>
      </tr>
      <tr>
         <th>몸무게</th>
         <td><input type="number" name="dogWeight"  value="${dog.dogWeight}" ></td>
         <th>특이사항</th>
      </tr>
      <tr>
         <th>중성화여부</th>
         <td>
            <label><input type="radio"  name="deSexibng" value="0"
            <c:if test="${dog.deSexibng eq '0'}">checked="checked"</c:if> />Y 
            <input type="radio"   value="1" name="deSexibng" 
            <c:if test="${dog.deSexibng eq '1'}">checked="checked"</c:if> />N </label>
         </td>
         <td><input type="text" name="dogMemo" required="required" value="${dog.dogMemo}"></td>
      </tr>
      <tr>
         <th>유치원등록일</th>
         <td>${dog.scStartdate} ~ ${dog.scEnddate}</td>
      </tr>
   </table>
      <input id="submit" type="submit" value="저장" class="btn btn-warning">
      <button type="button" class="btn btn-warning" 
               onclick="location.href='/mypage/member/myDogList'">목록</button>
  </form>
  </div>
  <div id="side"><%@ include file="/WEB-INF/views/include/memberSidebar.jsp" %></div>
</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>