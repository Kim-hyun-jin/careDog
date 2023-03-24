<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- 드롭다운 기능을 가능하게 해주는 popper.js -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" 
integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
</head>
<%-- <% String context = request.getContextPath(); %> --%>
<body>



<!---------------------- main 시작 --------------------------------------------------------------------------------------------->
<div id="main">

 <!--  <svg width="1921" height="526" viewBox="0 0 1921 526"> -->
      <g id="List_Group_Item_heading_Center" data-name="List Group/Item heading/Center" transform="translate(0.5 0.5)">
		 <rect  id="State" width="1920" height="500" fill="#ced4da" stroke="#ced4da" stroke-width="1"/>
		 <img src="img/dog/메인배너.jpg" class="card-img-top" alt="...">
          <g id="content" transform="translate(16 167.778)">
<!--               <text id="Donec_id_elit_non_mi_porta." data-name="Donec id elit non mi porta." transform="translate(0 68.222)" fill="#ced4da" font-size="13" font-family="ArialMT, Arial"><tspan x="0" y="0">Donec id elit non mi porta.</tspan></text>
 -->              <g id="header">
                  <!-- <text id="List_group_item_heading" data-name="List group item heading" transform="translate(0 40.222)" fill="#ced4da" font-size="20" font-family="SegoeUI, Segoe UI"><tspan x="0" y="0">List group item heading</tspan></text>
                  <text id="_3_days_ago" data-name="3 days ago" transform="translate(1880 37.222)" fill="#ced4da" font-size="13" font-family="ArialMT, Arial"><tspan x="-63.604" y="0">3 days ago</tspan></text> -->
              </g>
          </g>
         <!--  <text id="대표이미지" transform="translate(191 258)" fill="#6c757d" font-size="30" font-family="MalgunGothic, Malgun Gothic"><tspan x="-75" y="0">대표이미지</tspan></text> -->
      </g>
  <!-- </svg> -->
  
  
        <!-- Wrap the rest of the page in another container to center all the content. -->

      <div class="container marketing my-5">

        <!-- Three columns of text below the carousel -->
        <div class="row">
          <div class="col-lg-4">
            <img class="rounded-circle my-2" src="/img/dog/따뜻한비숑.jpg" alt="Generic placeholder image" width="140" height="140">
            <h4>강아지 유치원</h4>
            <p>반려견이 매일 정해진 교육 프로그램에 따라 운영되는 프리미엄 유치원 입니다.</p>
            <p><a class="btn btn-secondary" href="/school/usage" role="button">이용안내 바로가기 &raquo;</a></p>
          </div><!-- /.col-lg-4 -->
          <div class="col-lg-4">
            <img class="rounded-circle my-2" src="/img/dog/순무.jpg" alt="Generic placeholder image" width="140" height="140">
            <h4>행동교정신청</h4>
            <p>부족한 사회화 교육 등 다양한 놀이와 함께 전문 훈련사가  교육을 진행합니다.</p>
            <p><a class="btn btn-secondary" href="/school/usage" role="button">이용안내 바로가기 &raquo;</a></p>
          </div><!-- /.col-lg-4 -->
          <div class="col-lg-4">
            <img class="rounded-circle my-2" src="/img/dog/졸린비숑.jpg" alt="Generic placeholder image" width="140" height="140">
            <h4>편안한 호텔</h4>
            <p>자다가 업어가도 모를정도로 편안한 휴식환경입니다.</p>
            <p><a class="btn btn-secondary" href="/hotel/intro" role="button">시설안내 바로가기 &raquo;</a></p>
          </div><!-- /.col-lg-4 -->
        </div>
        <!-- /.row -->
  
 <!--  sliding img -->
		<div style="width: 60%; margin-left: auto; margin-right: auto; margin-top: 30px;">
			<div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
				<div class="carousel-indicators">
					<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
					<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
					<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
				</div>
				<div class="carousel-inner">
					<div class="carousel-item active" data-bs-interval="3000">
						<img src="/img/dog/종찬안녕.jpg" class="d-block w-100" alt="...">
						<div class="carousel-caption d-none d-md-block">
							<h5 style="color:white;">반가운 친구들</h5>
						</div>
					</div>
					<div class="carousel-item" data-bs-interval="3000">
						<img src="/img/dog/강아지유치원.jpg" class="d-block w-100" alt="...">
						<div class="carousel-caption d-none d-md-block">
							<h5 style="color: white;">즐거운 유치원</h5>
						</div>
					</div>
					<div class="carousel-item" data-bs-interval="3000">
						<img src="/img/dog/달리는리트리버.jpg" class="d-block w-100" alt="...">
						<div class="carousel-caption d-none d-md-block">
							<h5 style="color: white;">데일리 케어</h5>
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
 <!--  sliding img -->

		   	
	</div>


</div>              

<!---------------------- main 종료 --------------------------------------------------------------------------------------------->


<!---------------------- footer 시작 --------------------------------------------------------------------------------------------->
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
<!---------------------- footer 종료 --------------------------------------------------------------------------------------------->
</body>
</html>