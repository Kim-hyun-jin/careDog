<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예약 정보 입력</title>
<script type="text/javascript" src="/js/hotel/hotelRes.js"></script>
<link href="/css/hotel/reservation.css" rel="styleSheet" type="text/css">
<script type="text/javascript">
// select onchange 함수
function chageLangSelect(){  
		var langSelect = document.getElementById("id-lang");  
	 		  
 		// select element에서 선택된 option의 value가 저장된다.  
 		var selectValue = langSelect.options[langSelect.selectedIndex].value;  
 		// select element에서 선택된 option의 text가 저장된다.  
 		var selectText = langSelect.options[langSelect.selectedIndex].text;  
 		
 		if (selectValue != "" && selectValue != null) {
	 		$.ajax(
		    		 {
		   				url:"/hotel/reservation/getDogInfo",  
		   				data:{dogNo : selectValue},
		   				dataType:'json',
		   				success:function(data){
		   					$('#dogName').val(data.dogName);     /*  input Tag */
		   					if (data.dogWeight > 20) {
			   					$('#option3').prop('checked', true);
		   					} else if (data.dogWeight > 10) {
			   					$('#option2').prop('checked', true);
		   					} else {
			   					$('#option1').prop('checked', true);
		   					}
		   					
		   					if (data.deSexibng == 1) {
		   						$('#success-outlined').prop('checked', true);
		   					} else {
		   						$('#danger-outlined').prop('checked', true);
		   					}
		   				}
		   			}
		 	 );
 		}
 		
 		if (selectValue == "" || selectValue == null) {
 			$('#dogName').val('');
 			$("input:radio[name='size']").prop('checked', false);
 			$("input:radio[name='gen']").prop('checked', false);
 		}
 		
}
</script>

</head>
<body class="d-flex flex-column min-vh-100" style="background-image: url('/img/hotel/ss.webp');">
	<div class="container" style="padding-top: 30px; max-width: 900px; background-color: white;">
		<span class="fs-2 fw-semibold">예약 정보 입력</span>
		<hr style="max-width: 900px; margin: 30px 0;">
		<div class="card mb-4" style="max-width: 900px;">
			<div class="row g-0">
				<div class="col-md-4">
					<c:choose>
						<c:when test="${hotel.roomNum > 400 }">
							<img src="/img/hotel/sweetyroom.jpg" class="img-fluid rounded-start" alt="...">
						</c:when>
						<c:when test="${hotel.roomNum > 300 }">
							<img src="/img/hotel/premiumroom.jpg" class="img-fluid rounded-start" alt="...">
						</c:when>
						<c:otherwise>
							<img src="/img/hotel/standardroom.jpg" class="img-fluid rounded-start" alt="...">
						</c:otherwise>
					</c:choose>
				</div>
				<div class="col-md-6">
					<div class="card-body">
						<h5 class="card-title"><b>${hotel.roomType} 룸</b></h5>
							<p class="card-text">${hotel.roomMent}</p>
							<p class="card-text">${hotel.roomFunction}</p>
							<p class="card-text">1박 <script type="text/javascript">
														document.write(addComma(${hotel.roomPrice}));
													</script> 원 
													 <span style="float: right;"><b>총 
													<script type="text/javascript">
														document.write(addComma(${hotel.roomPrice*difD}));
													</script> 원</b></span></p>
							<p class="card-text"><small class="text-muted">${resStartdate} ~ ${resEnddate} | ${difD }박</small></p>
					</div>
				</div>
			</div>
		</div>
		<form action="/kakaoPay" method="post" onsubmit="return check()">
			<input type="hidden" name="roomPrice" value="${hotel.roomPrice}">
			<input type="hidden" name="roomNum" value="${hotel.roomNum}">
			<input type="hidden" name="resStartdate" value="${resStartdate}">
			<input type="hidden" name="resEnddate" value="${resEnddate}">
			<!-- 아이디 -->
			
			<table class="table" style="background-color: white;">
				<tr>
					<th>보호자명</th>
					<td><input class="form-control" type="text" value="${member.memberName}" placeholder="Default input" aria-label="default input example" readonly="readonly"></td>
				</tr>
				<tr>
					<th>애견 명</th>
					<td>
						<input class="form-control" name="dogName" id="dogName" type="text" placeholder="Default input" aria-label="default input example">
					</td>
					<td>
						<select id="id-lang" onchange="chageLangSelect()" class="dogSelect">
							<option value="">직접입력</option>
							<c:forEach items="${dogList}" var="dog">
								<option value="${dog.dogNo }">${dog.dogName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<th>애견 사이즈</th>
					<td>        <!-- primary 파랑 / secondary 회색 / success 초록 / danger 빨강 -->
						<input type="radio" class="btn-check" name="size" id="option3" value="3" autocomplete="off">
						<label class="btn btn-outline-secondary" for="option3" style="padding: 6px 15px;">대형</label>
						<input type="radio" class="btn-check" name="size" id="option2" value="2" autocomplete="off">
						<label class="btn btn-outline-secondary" for="option2" style="padding: 6px 15px;">중형</label>
						<input type="radio" class="btn-check" name="size" id="option1" value="1" autocomplete="off">
						<label class="btn btn-outline-secondary" for="option1" style="padding: 6px 15px;">소형</label>
					</td>
				</tr>
				<tr>
					<th>중성화 여부</th>
					<td>
						<input type="radio" class="btn-check" name="gen" value="1" id="success-outlined" autocomplete="off">
						<label class="btn btn-outline-secondary" for="success-outlined" style="padding: 6px 42.5px;">O</label>
						
						<input type="radio" class="btn-check" name="gen" value="0" id="danger-outlined" autocomplete="off">
						<label class="btn btn-outline-secondary" for="danger-outlined" style="padding: 6px 42.5px;">X</label>
					</td>
				</tr>
	
			</table>
			<hr style="max-width: 900px; margin: 30px 0;">
			<input type="image" src="/img/hotel/payment_icon_yellow_small.png">
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>