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
					<a class="flex-sm-fill text-sm-center nav-link " aria-current="page" href="/customerService/QA/main">전체</a>
					<a class="flex-sm-fill text-sm-center nav-link active" href="/customerService/QA/school">유치원</a>
					<a class="flex-sm-fill text-sm-center nav-link active" href="/customerService/QA/hotel">호텔</a>
					<a class="flex-sm-fill text-sm-center nav-link active" href="/customerService/QA/facility">시설안내</a>
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