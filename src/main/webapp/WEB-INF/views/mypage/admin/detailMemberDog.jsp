<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<% String context2 = request.getContextPath(); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/datepicker-ko.js"></script>

<script type="text/javascript">
	function changRole(obj) {
    alert(obj.value); // 선택된 option의 value가 출력된다!
    $("#role").val(obj.value);
	}
	
</script>

<style type="text/css">

   #all {
      position: absolute;
      top: 120px;
      min-height: 100%;
   }
   
   #bodyall {
      float: right;
   }
   
   #sidebar{
      float: left;
      
   }
   
   #footer{
   	position: absolute;
   	bottom: 0px;
   	
   	
	
   }
   
</style>

</head>
<body>
<div id="all">
<div id="bodyall">
<!-- <h1>회원 정보</h1> -->
	<span class="fs-2 fw-semibold my-5 mx-5">회원관리 (${detailMemberDog1.memberName })</span>
	<form action="/mypage/admin/updateMemberDog" method="post" class="form-inline justify-content-end" style="overflow:overlay; width:1400px; height:400px;">

	
		<table class="table caption-top" style="margin-left: 100; text-align: center; text-align:left;">
			<input type="hidden" value="${detailMemberDog1.id }" id="id" name="id">
			<input type="hidden" value="${detailMemberDog1.dogNo }" name="dogNo" >
			<input type="hidden" value="${detailMemberDog1.role }" id="role" name="role" >
	
			<tr><th>회원번호</th><td>${detailMemberDog1.id }</td></tr>
			<tr><th>이름</th><td>${detailMemberDog1.memberName }</td></tr>
			<tr><th>연락처</th><td>${detailMemberDog1.memberCall }</td></tr>
			<tr><th>주소</th><td>${detailMemberDog1.memberAddress }</td></tr>
			<tr><th>회원등급</th><td>
				<select onchange="javascript:changRole(this);">
								<c:forEach var="comm" items="${selRole }">
									<c:if test="${detailMemberDog1.role==comm.commDetail}">
										<option value="${comm.commDetail}" selected="selected">${comm.commDetail }</option>
									</c:if>
									<c:if test="${detailMemberDog1.role != comm.commDetail}">
										<option value="${comm.commDetail}">${comm.commDetail }</option>
									</c:if>
								</c:forEach>
				</select>
			</td></tr>
			<tr><th>담당선생님</th><td>${detailMemberDog1.teacherName }
	        <input type="button" value="변경 " class="btn btn-warning" onclick="location.href='/mypage/admin/classTeacheForm?id=${detailMemberDog1.id }'">
	        </td></tr>	
			<c:forEach var="selMemberDogList" items="${selMemberDogList}">
			<input type="hidden" value="${selMemberDogList.dogNo }" name="dogNo" >
				<tr><th>반려견번호</th><td>${selMemberDogList.dogNo }</td></tr>
				<%-- <tr><th>반려견명</th><td>${selMemberDogList.dogName }</td></tr> --%>
				<tr><th>반려견명</th><td><a href="detailDogInfo?dogNo=${selMemberDogList.dogNo}">${selMemberDogList.dogName }</a></td></tr>
				<tr><th>성별</th><td>
				<c:if test="${selMemberDogList.dogGender==0 }">암컷</c:if>
				<c:if test="${selMemberDogList.dogGender==1 }">수컷</c:if></td></tr> 
	
				<tr><th>나이</th><td>${selMemberDogList.dogAge }</td></tr>
				<tr><th>견종명</th><td>${selMemberDogList.dogKind }</td></tr>
				<tr><th>서비스만료일</th><td>${selMemberDogList.scStartdate.substring(0, 11) }</td></tr>
				<tr><th>서비스만료일</th><td>
				${selMemberDogList.scEnddate.substring(0, 11) } 
				<%-- <input id="datepicker" name="scEnddate" value="${selMemberDogList.scEnddate.substring(0, 11)}" type="text" required="required"> --%>
				<script>
					$(function () {
						 $("#datepicker").datepicker();
					});
				</script>
				</td></tr>
				<tr><th>메모(특이사항)</th><td>${selMemberDogList.dogMemo }</td></tr>
			</c:forEach>
			<tr><th><input type="submit" value="저장" class="btn btn-warning"></th></tr>
		</table>
	</form>
</div>
<div id="footer" style="width: 100%; z-index: 1; bottom:0; position: fixed;"><%@ include file="/WEB-INF/views/include/footer.jsp" %></div>
<div id="sidebar"><%@ include file="/WEB-INF/views/include/adminSidebar.jsp" %></div>
</div>
</body>
</html>