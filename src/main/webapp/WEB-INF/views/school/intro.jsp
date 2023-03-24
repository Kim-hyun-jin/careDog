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
<link href="/css/kindergarten/kin.css" rel="styleSheet" type="text/css">

</head>
<body class="d-flex flex-column min-vh-100">
	<div class="container">
		<div><img style="width: 1300px; height: 300px;" src="/img/school/pug.PNG">
		</div>
		
		<div class="hotel_title">
			강아지 유치원
		</div>
		
		
		<div class="hotel_use_ment">
			<div class="title">"친구들과 수업하며 사회성 향상되는 교육"</div>
			<div class="ment">산책예절, 놀이방법 습득으로 매너있는 강아지 되기 !</div>
		</div>
		
		<div class="card-group">
			<div class="card">
				<img src="/img/school/black.PNG" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">쾌활한 놀이</h5>
				</div>
			</div>
			<div class="card">
				<img src="/img/school/ball.PNG" class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title">근력운동</h5>
					</div>
			</div>
			<div class="card">
				<img src="/img/school/together.PNG" class="card-img-top" alt="...">
				<div class="card-body">
					<h5 class="card-title">옥상산책</h5>
				</div>
			</div>
		</div>
		<p>
		<!----------------------- 필요하면 사용 ------------------------------------------------>
		<div style="width: 40%; margin-left: auto; margin-right: auto;">
		<div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active" data-bs-interval="3000">
					<img src="/img/hotel/outside.jpg" class="d-block w-100" alt="...">
					<div class="carousel-caption d-none d-md-block">
						<h5>넓은 야외공간</h5>
					</div>
				</div>
				<div class="carousel-item" data-bs-interval="3000">
					<img src="/img/hotel/public.jpg" class="d-block w-100" alt="...">
					<div class="carousel-caption d-none d-md-block">
						<h5>다양한 공공시설</h5>
					</div>
				</div>
				<div class="carousel-item" data-bs-interval="3000">
					<img src="/img/hotel/care.jpg" class="d-block w-100" alt="...">
					<div class="carousel-caption d-none d-md-block">
						<h5>데일리 케어</h5>
					</div>
				</div>
			</div>
			<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
		</div>
		</div>
			<div class="hotel_title">
			입학문의<p>
			02.6523.3652
			
		</div>
		<!-----------------------  ------------------------------------------------>
		 <!-- container ---------------------------------------------------------------------------->
		 	
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>