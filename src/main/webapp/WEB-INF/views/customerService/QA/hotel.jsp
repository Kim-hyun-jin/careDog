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
					<a class="flex-sm-fill text-sm-center nav-link " href="/customerService/QA/hotel">호텔</a>
					<a class="flex-sm-fill text-sm-center nav-link active" href="/customerService/QA/facility">시설안내</a>
				</nav>
				<div id="Accordion_wrap" style="margin-top: 20px;">
				

					
					
					<div class="que">
						<span>[호텔]체크인과 체크아웃 시간은 어떻게 되나요?</span>
						<div class="arrow-wrap">
							<span class="arrow-top">🔼</span>
							<span class="arrow-bottom">🔽</span>
						</div>
					</div>
					<div class="anw">
						<span>사전 예약시 - 오전 8시~오후 8시 입니다.</span>
					</div>
					
					
					<div class="que">
						<span>[호텔]식사시간과 산책시간이 따로 있나요?</span>
						<div class="arrow-wrap">
							<span class="arrow-top">🔼</span>
							<span class="arrow-bottom">🔽</span>
						</div>
					</div>
					<div class="anw">
						<span>반려견 개별로 체크하여 식사가 지급되며, 1일 1회 20분가량 산책이 진행됩니다.</span>
					</div>
					
					<div class="que">
						<span>[호텔]단체예약은 어떻게 하나요?</span>
						<div class="arrow-wrap">
							<span class="arrow-top">🔼</span>
							<span class="arrow-bottom">🔽</span>
						</div>
					</div>
					<div class="anw">
						<span>단체 예약은 상담을 통해 예약가능합니다. 대표번호로 전화 부탁드립니다.</span>
					</div>
					
					<div class="que">
						<span>[호텔]예약 취소는 언제까지 가능한가요?</span>
						<div class="arrow-wrap">
							<span class="arrow-top">🔼</span>
							<span class="arrow-bottom">🔽</span>
						</div>
					</div>
					<div class="anw">
						<span>예약일 2일전까지 취소 가능하며, 당일은 예약 취소 불가능합니다.</span>
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