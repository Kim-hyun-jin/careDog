<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QA main</title>
<link href="/css/customerService/QA/QA.css" rel="styleSheet" type="text/css">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-2">
				<div class="flex-shrink-0 p-3 bg-white" style="width: 280px;">
					<a href="#" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
						<span class="fs-5 fw-semibold">Q n A</span>
					</a>
					<ul class="list-unstyled ps-0">
						<li class="mb-1">
							<a href="/customerService/QA/QA" class="link-dark d-inline-flex text-decoration-none rounded"><b>자주 묻는 질문</b></a>
						</li>
						<li class="mb-1">
							<a href="/customerService/QA/question" class="link-dark d-inline-flex text-decoration-none rounded">문의하기</a>
						</li>
						<li class="mb-1">
							<a href="/customerService/QA/myQuestion" class="link-dark d-inline-flex text-decoration-none rounded">나의 QnA</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="col-lg-10">
				<span class="fs-2 fw-semibold">자주 묻는 질문</span>
				<hr>
				<nav class="nav nav-pills flex-column flex-sm-row">
					<a class="flex-sm-fill text-sm-center nav-link active" aria-current="page" href="/customerService/QA/main">전체</a>
					<a class="flex-sm-fill text-sm-center nav-link active" href="/customerService/QA/school">유치원</a>
					<a class="flex-sm-fill text-sm-center nav-link active" href="/customerService/QA/hotel">호텔</a>
					<a class="flex-sm-fill text-sm-center nav-link " href="/customerService/QA/facility">시설안내</a>
				</nav>
				<div id="Accordion_wrap" style="margin-top: 20px;">
				
				
					<div class="que">
						<span>[이용안내]영업 시간은 어떻게 되나요?</span>
						<div class="arrow-wrap">
							<span class="arrow-top">🔼</span>
							<span class="arrow-bottom">🔽</span>
						</div>
					</div>
					<div class="anw">
						<span>상세 서비스에 따라 운영시간이 달라질 수 있습니다. <br>
						 유치원 : 오전 8시~오후 8시 [ 주말, 공휴일 휴무 ] <br>
						 호텔 : 사전 예약시 - 오전 8시~오후 8시 [연중무휴 ] </span>
					</div>
					
					<div class="que">
						<span>[이용안내]이용에 제한이 있나요?</span>
						<div class="arrow-wrap">
							<span class="arrow-top">🔼</span>
							<span class="arrow-bottom">🔽</span>
						</div>
					</div>
					<div class="anw">
						<span>아래 사항에 해당될 경우 이용에 제한될 수 있습니다.<br>

							1. 응급 상황이 초래될 수 있는 질병을 앓고 있는 경우 (심장병 등)<br>
							
							2. 전염성 질환을 앓고 있는 경우<br>
							
							3. 발정기 암컷 (부득이한 경우, 매너팰트 착용 및 개별 관리)<br></span>
					</div>	


					
					<div class="que">
						<span>[시설관련]방문시 주차가 가능한가요?</span>
						<div class="arrow-wrap">
							<span class="arrow-top">🔼</span>
							<span class="arrow-bottom">🔽</span>
						</div>
					</div>
					<div class="anw">
						<span>주차는 오전 8시부터 오후 8시까지 주차가 가능합니다. 
								지하 주차장 3층 T맵으로 들어오시면 됩니다.
								주차는 30분까지 무료이며 그 이후는 리셉션에 요청하시면 주차 시간을 넣어드리고 있습니다.  </span>
					</div>					
					<div class="que">
						<span>[시설관련]아이들이 이용가능한 놀이 시설이 있나요?</span>
						<div class="arrow-wrap">
							<span class="arrow-top">🔼</span>
							<span class="arrow-bottom">🔽</span>
						</div>
					</div>
					<div class="anw">
						<span>건물내 라운지, 루프탑에 잔디운동장 및 훈련용 놀이시설, 애견 전용 수영장이 있습니다.
						반려견의 견종, 체급, 나이 상관 없이 이용가능합니다. 다만, 아이들의 성향별로 이용시간을 나눠서 이용하게 됩니다. </span>
					</div>	
					
					
					
						
										
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript">
$(".que").click(function() {
	   $(this).next(".anw").stop().slideToggle(300);
	  $(this).toggleClass('on').siblings().removeClass('on');
	  $(this).next(".anw").siblings(".anw").slideUp(300); // 1개씩 펼치기
	});
</script>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>
</html>