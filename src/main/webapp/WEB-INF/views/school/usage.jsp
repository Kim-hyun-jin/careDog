<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>usage</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">
<link href="/css/kindergarten/price.css" rel="styleSheet" type="text/css">
<link href="/css/kindergarten/kin.css" rel="styleSheet" type="text/css">

</head>
<body class="d-flex flex-column min-vh-100">
	<ul class="" style="position: fixed; top: 70px;">
		<a class="" href="#scrollspyHeading1"><li class="usage_link">입학안내</li></a>
		<a class="" href="#scrollspyHeading2"><li class="usage_link">유의사항</li></a>
		<a class="" href="#scrollspyHeading3"><li class="usage_link">결제 안내</li></a>
		<a class="" href="#scrollspyHeading4"><li class="usage_link">오시는 길</li></a>
	</ul>
	<div class="container">
		<div >
			<img style="width: 1300px; height: 300px;" src="/img/school/kinbanner.PNG">
		</div>
		<div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-root-margin="0px 0px -40%" data-bs-smooth-scroll="true"	class="scrollspy-example bg-light p-3 rounded-2" tabindex="0">
			<h4 id="scrollspyHeading1"><div class="hotel_title">유치원 이용 안내</div></h4>
			<div class="hotel_use_ment">
				<div class="title">강아지의 사회성을 길러주세요❗❗❗</div>
				<div class="ment">소심한 강아지도 🆗</div>
				
				<!-- <div class="title">예약 방법</div>
				<div class="ment">로그인 ➱ 호텔>예약하기 ➱ 방, 날짜 선택 ➱ 예약 정보 입력 ➱ 결제</div>
				<div class="ment">[ 호텔 <span style="color: red;">예약은 회원만 가능</span>합니다! ]</div> -->
				
				<div class="title">입학 가격 안내</div>
				<table class="table_price">
					<thead>
						<tr class="th_tr">
							<th><span class="th_price">사이즈</span></th>
							<th><span class="th_price">기본보육</span></th>
							<th><span class="th_price">활동교육</span></th>
							<th><span class="th_price">종일반</span></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th class="th">소형(~8kg)</th>
							<td>20,000</td>
							<td>30,000</td>
							<td>50,000</td>
						</tr>
						<tr class="mid_tr">
							<th class="th">중형(~16kg)</th>
							<td>20,000</td>
							<td>30,000</td>
							<td>50,000</td>
						</tr>
						<tr>
							<th class="th">대형(~30kg)</th>
							<td>30,000</td>
							<td>40,000</td>
							<td>50,000</td>
						</tr>
					</tbody>
				</table>
				<div class="ment" style="font-size: 13px;">등원 (1일 기준)</div>
				
				<div class="title">기본 옵션</div>
				<div class="ment">
					산책 1회 / 데일리 케어 1회<br>
					택1 이용가능
				</div>
				<div class="ment" style="font-size: 13px;">등원 (1일 기준)</div>
			</div>
			<hr>
			<h4 id="scrollspyHeading2"><div class="hotel_title">입학 유의 사항</div></h4>
			<div class="hotel_use_ment">
				<div class="title">반려견 크기에 따른 주의사항</div>
				<div class="ment">안전을 위해 상황에 따라 분리 보호 조치를 취할 수 있습니다❗❗</div>
				
				<div class="title">이용제한</div>
				<div class="ment">
					✔ 1년 이내 5가지 접종(종합, 코로나, 켄넬코프, 신종 인플루엔자, 광견병) 받지 않은 반려견은 입실이 제한됩니다.<br>
					※ 접종기록은 접종병원명이 꼭 필요하므로 증빙 자료는 미리 준비해주세요.
				</div>
				<div class="ment">
					✔ 1살 미만의 반려견인 경우 항체 검사가 필수 입니다. (단, 1살 이상 시 재접종 기록 확인)
				</div>
				<div class="ment">
					✔  건강문제 또는 공격성이 강해 타 고객의 이용에 피해가 되는 반려견 일 경우 입실이 제한됩니다.
				</div>
			</div>
			<hr>
			<h4 id="scrollspyHeading3"><div class="hotel_title">결제 안내</div></h4>
			<div class="hotel_use_ment">
				<div class="title">반려견 크기에 따른 주의사항</div>
				<div class="ment">안전을 위해 상황에 따라 분리 보호 조치를 취할 수 있습니다❗</div>
				
				<div class="title">이용제한</div>
				<div class="ment">물림사고 이력이 있는 경우, 행동교정 클래스를 이수 한 후 입학가능합니다❗</div>
				
				<div class="title">종일반</div>
				<div class="ment">보육 종료 시간은 22시입니다. 이후는 호텔 개별 결재 부탁드립니다❗</div>
				
			</div>
		</div>
	</div>
	<h4 id="scrollspyHeading4"><div class="hotel_title">오시는 길</div></h4>
		<div class="map" id="map" style="width:750px;height:350px;"></div>

		<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fb93fd26857d51f3e5dba9db3ff8167c"></script>
		<script>
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = {
			        center: new kakao.maps.LatLng(37.55650, 126.94519), // 지도의 중심좌표
			        level: 3, // 지도의 확대 레벨
			        mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
			    }; 
	
			// 지도를 생성한다 
			var map = new kakao.maps.Map(mapContainer, mapOption); 
	
			// 지도에 마커를 생성하고 표시한다
			var marker = new kakao.maps.Marker({
			    position: new kakao.maps.LatLng(37.55650, 126.94519), // 마커의 좌표
			    map: map // 마커를 표시할 지도 객체
			});

		</script>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>