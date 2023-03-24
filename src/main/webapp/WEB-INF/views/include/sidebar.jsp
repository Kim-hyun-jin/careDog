<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<% String context1 = request.getContextPath(); %>
</head>

<body>
<div class="flex-shrink-0 p-3 bg-white" style="width: 280px;">
  <a href="/" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
    <svg class="bi pe-none me-2" width="30" height="24"><use xlink:href="#bootstrap"></use></svg>
    <span class="fs-5 fw-semibold">Mypage</span>
  </a>
  <ul class="list-unstyled ps-0">
    <li class="mb-1">
      <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="false">
        회원
      </button>
      <div class="collapse" id="home-collapse" style="">
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
          <li><a href="/mypage/member/myInfo" class="link-dark d-inline-flex text-decoration-none rounded">내 정보</a></li>
          <li><a href="/mypage/member/myPwEdit" class="link-dark d-inline-flex text-decoration-none rounded">비밀번호 수정</a></li>
          <li><a href="/mypage/member/memberResList" class="link-dark d-inline-flex text-decoration-none rounded">예약 내역</a></li>
          <li><a href="/mypage/member/myInfoDrop" class="link-dark d-inline-flex text-decoration-none rounded">회원 탈퇴</a></li>
        </ul>
      </div>
    </li>
    <li class="mb-1">
      <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
        나의 강아지
      </button>
      <div class="collapse" id="dashboard-collapse" style="">
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
          <li><a href="/mypage/member/myDogList" class="link-dark d-inline-flex text-decoration-none rounded">반려견 정보</a></li>
          <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">반려견 일지</a></li>
        </ul>
      </div>
    </li>
     <li class="mb-1">
      <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
        관리자 페이지
      </button>
      <div class="collapse" id="orders-collapse" style="">
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
          <li><a href="/mypage/admin/memberList" class="link-dark d-inline-flex text-decoration-none rounded">회원 관리</a></li>
          <li><a href="/mypage/admin/resList" class="link-dark d-inline-flex text-decoration-none rounded">호텔 예약 관리</a></li>
          <li><a href="/mypage/admin/resPayList" class="link-dark d-inline-flex text-decoration-none rounded">결제 관리</a></li>
          <li><a href="/mypage/admin/dogTypeList" class="link-dark d-inline-flex text-decoration-none rounded">반려견 품종관리</a></li>
        </ul>
      </div>	
    </li>
<!--     <li class="border-top my-3"></li>
    <li class="mb-1">
      <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
        Account
      </button>
      <div class="collapse" id="account-collapse" style="">
        <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
          <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">New...</a></li>
          <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Profile</a></li>
          <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Settings</a></li>
          <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Sign out</a></li>
        </ul>
      </div>
    </li> -->
  </ul>
</div>
</body>
</html>
