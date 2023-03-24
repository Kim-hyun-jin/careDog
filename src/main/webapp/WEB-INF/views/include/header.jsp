<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<% String context = request.getContextPath(); %>
<link href="<%=context %>/common/css/bootstrap.min.css" rel="stylesheet">
<script src="<%=context %>/common/js/jquery.js" type="text/javascript"></script>
<script src="<%=context %>/common/js/bootstrap.min.js" type="text/javascript"></script>
<script src="<%=context %>/common/js/bootstrap.bundle.js" type="text/javascript"></script>
<script>
	function isAnonymous(){
		window.confirm('로그인이 필요한 서비스입니다.');	
	}
</script>

   <!---------------------- header 부분 --------------------------------------------------------------------------------------------->
   <body class="d-flex flex-column min-vh-100">
	<!-- role 권한 체크 요소 -->
	<sec:authorize access="hasAnyRole('MEMBER','TEACHER','ADMIN')" var="roleMember"/>
	<sec:authorize access="hasAnyRole('TEACHER','ADMIN')" var="roleTeacher"/>		
	<sec:authorize access="hasAnyRole('MEMBER')" var="roleOnlyMember"/>		
	<sec:authorize access="hasAnyRole('TEACHER')" var="roleOnlyTeacher"/>		
	<sec:authorize access="hasRole('ADMIN')" var="roleAdmin"/>	
	<!-- role 권한 체크 요소 -->
    <header id="header">
        <nav class="navbar navbar-expand-lg navbar-light bg-light" >
          <div class="container-fluid">
              <img src="/img/icon/love.png"  alt="logo" width="40" height="30" class="mx-2">
            <a class="navbar-brand mx-2" href="/index">
              CareDog
            </a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
              <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                
<!--                 <li class="nav-item">
                  <a class="nav-link" href= >혹시몰라여분으로</a>
                </li> -->

                
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href="/hotel/intro" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    호텔 
                  </a>
                  <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="/hotel/intro">시설안내</a></li>
                    <li><a class="dropdown-item" href="/hotel/usage">이용안내</a></li>
                    <!-- <li><hr class="dropdown-divider"></li> -->
                    
                    <sec:authorize access="isAnonymous()">
                    	<li><a class="dropdown-item" href="/main/login" onclick="isAnonymous()">예약하기</a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                    	<li><a class="dropdown-item" href="/hotel/reservation/main" >예약하기</a></li>
                    </sec:authorize>
                  </ul>
                </li>

                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href=  id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    유치원 
                  </a>
                  <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="/school/intro">시설안내</a></li>
                    <li><a class="dropdown-item" href="/school/usage">이용안내</a></li>
                    <!-- <li><hr class="dropdown-divider"></li> -->

                    <sec:authorize access="isAuthenticated()">
	                    <li><a class="dropdown-item" href="/school/dogNotice/main">알림장</a></li>                   
	                    <c:choose>
	                    	<c:when test="${roleOnlyMember }">
	                    		<li><a class="dropdown-item" href="/school/BHCorrection/Member/BhMemberList">행동교정 신청</a></li>
	                    	</c:when>
	                    	<c:when test="${roleOnlyTeacher }">
	                    		<li><a class="dropdown-item" href="/school/BHCorrection/Teacher/BhAllList">행동교정 신청</a></li>
	                    	</c:when>
	                    </c:choose>
                    </sec:authorize>

                  </ul>
                </li>
   
   
                <li class="nav-item dropdown">
                  <a class="nav-link dropdown-toggle" href=  id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                    고객서비스 
                  </a>
                  <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <li><a class="dropdown-item" href="/customerService/cusNotice/main?page=1&size=10&sorting=desc">공지사항</a></li>
                    <li><a class="dropdown-item" href="/customerService/QA/main">Q&A</a></li>
                    <!-- <li><hr class="dropdown-divider"></li> -->
                    <li><a class="dropdown-item" href="/customerService/freeboard/main">자유게시판</a></li>
                  </ul>
                </li>
  
  </ul>

              
           <!-- <button type="button" class="btn btn-outline-primary" >마이페이지</button> -->
              <sec:authorize access="isAnonymous()">
	              <a  href="/main/login"><button type="button" class="btn btn-warning m-1">로그인</button></a>
	              <a  href="/main/signup"><button type="button" class="btn btn-warning m-1">회원가입</button></a>
              </sec:authorize>
              

            <sec:authorize access="isAuthenticated()">
			<c:if test="${roleMember }">
				<c:set var="memberGrade" value="일반회원" />
				<c:if test="${roleTeacher }">
					<c:set var="memberGrade" value="선생님" />
						<c:if test="${roleAdmin }">
							<c:set var="memberGrade" value="관리자" />
						</c:if>
				</c:if>
			</c:if>
				username: <sec:authentication property="principal.username"/><br>
				userNo(id): <sec:authentication property="principal.id"/><br>
				회원등급 : ${memberGrade } 
				<sec:authentication property="principal.user" var="principal"/>
			</sec:authorize>
			
			
              <sec:authorize access="isAuthenticated()">
              	<a  href="/logout"><button type="button" class="btn btn-outline-secondary mx-2">로그아웃</button></a>
              </sec:authorize>
              
             <!--  로그인 기능 구현 전이므로 관리자 페이지를 디폴트로 연결 -->
             <sec:authorize access="hasRole('ADMIN')">
                <a href="/mypage/admin/memberList"><button type="button" class="btn btn-outline-secondary">마이페이지(admin)</button></a>
             </sec:authorize>
              
            <sec:authorize access="isAnonymous()">
                    	<a href="/main/login"><button type="button" class="btn btn-outline-secondary" onclick="isAnonymous()">마이페이지(member)</button></a>
            </sec:authorize>
            <sec:authorize access="hasRole('MEMBER')">
                <a href="/mypage/member/myInfo"><button type="button" class="btn btn-outline-secondary">마이페이지(member)</button></a>
             </sec:authorize>
             
             <!-- hasRole('TEACHER') ? -->
              
            </div>
             <!-- <a href="/mypage/member/myInfo"><img src="/img/icon/mypage.png"  alt="logo" width="40" height="30"></a> -->
          </div>
        </nav>
  </header>