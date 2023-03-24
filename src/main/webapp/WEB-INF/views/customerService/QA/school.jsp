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
					<a class="flex-sm-fill text-sm-center nav-link " href="/customerService/QA/school">유치원</a>
					<a class="flex-sm-fill text-sm-center nav-link active" href="/customerService/QA/hotel">호텔</a>
					<a class="flex-sm-fill text-sm-center nav-link active" href="/customerService/QA/facility">시설안내</a>
				</nav>
				<div id="Accordion_wrap" style="margin-top: 20px;">
				
				

					
					<div class="que">
						<span>[유치원]수강신청은 어떻게 하나요?</span>
						<div class="arrow-wrap">
							<span class="arrow-top">🔼</span>
							<span class="arrow-bottom">🔽</span>
						</div>
					</div>
					<div class="anw">
						<span>로그인 후 홈페이지 유치원 이용안내를 숙지하신 후 
								대표번호 02.6523.3652로 전화주시면  유치원 가입안내 및 상담시 기본 내용 평가 후 결제 도와드리겠습니다.</span>
					</div>
					
					<div class="que">
						<span>[유치원]사전 상담시에 평가는 어떤 내용인가요?</span>
						<div class="arrow-wrap">
							<span class="arrow-top">🔼</span>
							<span class="arrow-bottom">🔽</span>
						</div>
					</div>
					<div class="anw">
						<span>평가는 모든 반려견과 케어도그 직원들의 건강과 안전을 보장하기 위한 것입니다.
								강아지와 상호 작용하여 강아지가 받은 훈련 수준, 얼마나 사교적인지 등을 평가할 것입니다.
								또한 평가는 반려견이 케어서비스에 적합한지 알아보기 위해 시행됩니다.</span>
					</div>
					
					<div class="que">
						<span>[유치원]이용시 필요한 접종기록이 있나요?</span>
						<div class="arrow-wrap">
							<span class="arrow-top">🔼</span>
							<span class="arrow-bottom">🔽</span>
						</div>
					</div>
					<div class="anw">
						<span>모든 서비스는 필수 예방접종에 대하여 1년 이내의 접종내역이 확인이 되셔야 이용 가능합니다.
							(종합백신/코로나장염/전염성기관지염(켄넬코프)/광견병/신종인플루엔자)
							또한, 1년 미만의 자견의 경우 항체가 검사 기록이 필수로 필요합니다.
							방문 전 병원 어플, 접종 수첩, 병원에서 발급한 정식 기록 등 꼭 지참 요청드립니다.
							(자가접종 및 동물병원 외 펫샵 등의 접종 기록은 인정되지 않으며, 7종 or 8종 항체가검사로 기록 대체 인정 가능합니다.)</span>
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