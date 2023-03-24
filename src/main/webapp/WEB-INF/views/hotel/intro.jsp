<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Intro</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
<link href="/css/hotel/hotel.css" rel="styleSheet" type="text/css">
</head>
<body class="d-flex flex-column min-vh-100">
	<div class="container">
		<div class="hotel_mainImg">
		</div>
		
		<div class="hotel_title">
			반려동물 호텔
		</div>
		
		
		<div class="hotel_use_ment">
			<div class="title">"5성급 프리미엄 호텔 케어독!"</div>
			<div class="ment">아이들이 좋아할 수 밖에 없는 환경!</div>
			<div class="ment"><b>케어독 호텔링은 포근함과 아늑함으로 아이들의 활력과 심리적인 안정감을 향상시킵니다.</b></div>
		</div>
		
		<div class="card-group">
			<div class="card">
				<img src="/img/hotel/outside.jpg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">넓은 야외공간</h5>
				</div>
			</div>
			<div class="card">
				<img src="/img/hotel/public.jpg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">다양한 공공시설</h5>
					</div>
			</div>
			<div class="card">
				<img src="/img/hotel/care.jpg" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">데일리 케어</h5>
				</div>
			</div>
		</div>
		<!----------------------- 필요하면 사용 ------------------------------------------------>
		<!-----------------------  ------------------------------------------------>
		
		<hr>
		
		<div class="hotel_use_ment">
			<div class="title">"편안~한 프라이빗 공간!"</div>
			<div class="ment">하루종일 놀다 들어온 아이들의 편안한 휴식처</div>
			<div>신선한 순환기시스템과 쾌적한 공기청정 제습은 기본!<br>
				전객실 항온 바닥난방&냉방순환 시스템<br>
				철저한 위생소독 관리와 1:1 맞춤 데이케어
				<p><b>스위티 룸은 별도로 시간을 맞춰 맛있는 간식을 제공합니다!</b></div>
		</div>
		
		<div class="row row-cols-1 row-cols-md-3 g-4">
			<div class="col">
				<div class="card h-100">
				<img src="/img/hotel/standardroom.jpg" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">스탠다드 룸</h5>
					</div>
					<div class="card-footer">
						<small class="text-muted">소 / 중형견 이용가능</small>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card h-100">
				<img src="/img/hotel/premiumroom.jpg" class="card-img-top" alt="...">
					<div class="card-body">
					<h5 class="card-title">프리미엄 룸</h5>
					</div>
					<div class="card-footer">
					<small class="text-muted">소 / 중 / 대형견 이용가능</small>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="card h-100">
				<img src="/img/hotel/sweetyroom.jpg" class="card-img-top" alt="...">
					<div class="card-body">
					<h5 class="card-title">스위티 룸</h5>
					</div>
					<div class="card-footer">
					<small class="text-muted">소 / 중 / 대형견 이용가능</small>
					</div>
				</div>
			</div>
		</div>
	</div> <!-- container ---------------------------------------------------------------------------->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>