<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hotel reservation main</title>
<link href="/css/hotel/reservation.css" rel="styleSheet" type="text/css">
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(function() {
		$("#datepicker").datepicker({
			dateFormat : 'yy-mm-dd' //달력 날짜 형태
			,showOtherMonths : true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
			,showMonthAfterYear : true // 월- 년 순서가아닌 년도 - 월 순서
			,changeYear : true //option값 년 선택 가능
			,changeMonth : true //option값  월 선택 가능                
			,showOn : "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
			,buttonImage : "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
			,buttonImageOnly : true //버튼 이미지만 깔끔하게 보이게함
			,buttonText : "선택" //버튼 호버 텍스트              
			,yearSuffix : "년" //달력의 년도 부분 뒤 텍스트
			,monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 텍스트
			,monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 Tooltip
			,dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ] //달력의 요일 텍스트
			,dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일',	'금요일', '토요일' ] //달력의 요일 Tooltip
			,minDate : "0" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
			,maxDate : "+6m" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
		});
	});
	$(function() {
		$("#datepicker2").datepicker({
			dateFormat : 'yy-mm-dd' //달력 날짜 형태
			,showOtherMonths : true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
			,showMonthAfterYear : true // 월- 년 순서가아닌 년도 - 월 순서
			,changeYear : true //option값 년 선택 가능
			,changeMonth : true //option값  월 선택 가능                
			,showOn : "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
			,buttonImage : "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //버튼 이미지 경로
			,buttonImageOnly : true //버튼 이미지만 깔끔하게 보이게함
			,buttonText : "선택" //버튼 호버 텍스트              
			,yearSuffix : "년" //달력의 년도 부분 뒤 텍스트
			,monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 텍스트
			,monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ] //달력의 월 부분 Tooltip
			,dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ] //달력의 요일 텍스트
			,dayNames : [ '일요일', '월요일', '화요일', '수요일', '목요일',	'금요일', '토요일' ] //달력의 요일 Tooltip
			,minDate : "0" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전)
			,maxDate : "+6m" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
		});
		$('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
	    $('#datepicker').datepicker("option", "onClose", function ( selectedDate ) {
	        $("#datepicker2").datepicker( "option", "minDate", selectedDate);
	    });

	    $('#datepicker2').datepicker("option", "onClose", function ( selectedDate ) {
	        $("#datepicker").datepicker( "option", "maxDate", selectedDate);
	    });
	});
	function ajaxRoomList(vResStartdate,vResEnddate){
		if(vResStartdate==vResEnddate){
			alert("동일한 날짜를 선택 할 수 없습니다.");
			$('#resRoomList *').remove();
			$('#datepicker').val("");
			$('#datepicker2').val("");
		} else if(vResStartdate!=""&&vResEnddate!=""){
			$.ajax({
				url : "/hotel/reservation/main/ajaxRoomList",
				data:{resStartdate	:vResStartdate,
					  resEnddate	:vResEnddate},
				dataType:'json',
				success:function(result){
					var str = 	"<table id='hotels' class='table caption-top'><thead><tr>"+
								"<th>방번호</th>"+
								"<th>방유형</th>"+
								"<th>방설명</th>"+
								"<th>일박가격</th>"+
								"<th>방상태</th>"+
								"<th>선택</th></tr></thead><tbody>";
					$(result).each(
						function(){
							str +=	"<tr><td>"+this.roomNum+"</td>"+
									"<td>"+this.roomType+"</td>"+
									"<td>"+this.roomMent+"</td>"+
									"<td>"+this.roomPrice+"</td>"+
									"<td>"+this.roomCondition+"</td>";
							if(this.roomCondition=="사용가능"){
								str+="<td><input class='form-check-input' type='radio' id='flexRadioDefault1' name='roomNum' value='"+this.roomNum+"'></td></tr>";
							} else {
								str+="<td>선택불가</td></tr>";
							}
						});
					str+="</tbody></table><input type='submit' class='btn btn-outline-secondary' value='다음으로'>";
					$('#resRoomList *').remove();
					$('#resRoomList').append(str);
				}
			});
		}
	}
	
	function radioChk(value){
		if(value==""||value==null){
			alert("방을 선택해주세요.");
			return false;
		} else{
			return true;
		}
	}
</script>
</head>
<body class="d-flex flex-column min-vh-100">
	<div class="mainContents">
		<div class="container">
			<a class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
				<span class="fs-5 fw-semibold"><h1><b>호텔 예약하기</b></h1></span>
			</a>
			<form action="/hotel/reservation/info" onsubmit="return radioChk(roomNum.value)" class="reservationMainForm">
				<div id="content" class="content">
	 				<a class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
						<span class="fs-5 fw-semibold"><h2><b>날짜 선택</b></h2>
			 				시작일 : <input type="text" class="datepicker" id="datepicker" name="resStartdate" onchange="ajaxRoomList(resStartdate.value,resEnddate.value)" required="required"><br/>
							종료일 : <input type="text" class="datepicker" id="datepicker2" name="resEnddate" onchange="ajaxRoomList(resStartdate.value,resEnddate.value)" required="required"><br/>
						</span>
					</a>
				</div><br>
				<div id="resRoomList">
				</div><br>
			</form>
		</div>
	</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>