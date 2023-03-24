package com.oracle.s20221103.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oracle.s20221103.asecurity.PrincipalDetails;

@Controller
public class HomeController {

	// main(index) 첫 페이지에 연결된 링크 모음
	// 메인페이지 연결된 링크들 모아둠

	// --------------------------------------메인--------------------------------------------------------------------------------------
	@GetMapping("/")
	public String root(@Nullable Authentication authentication) {
		return "main/index";
	}

	@GetMapping("/index")
	public String index(@Nullable Authentication authentication) {
		return "main/index";
	}

	// --------------------------------------로그인/회원가입/회원관리--------------------------------------------------------------------------------------

	@GetMapping("/main/login")
	public String loginForm(String error, String logout, Model model,
			HttpServletRequest request) {

		System.out.println("logout: " + logout);

		// 임재우 12/02 로그인시 이전페이지로 이동기능 구현 ========================
		String uri = request.getHeader("Referer");
		if (uri != null && !uri.contains("/login")
				&& !uri.contains("/signup")
				&& !uri.contains("/resetPassForm")
				&& !uri.contains("/findJoinId")) {
			request.getSession().setAttribute("prevPage", uri);
		}
		// 임재우 12/02 로그인시 이전페이지로 이동기능 구현 ========================

		if (error != null) {
			model.addAttribute("error", "로그인 실패: 다시 시도 해주세요");
		}
		if (logout != null) {
			System.out.println("user logout");
			model.addAttribute("logout", "로그아웃 완료");
		}

		return "main/loginForm";
	}

	@GetMapping("/main/signup")
	public String signupForm() {
		return "main/signupForm";
	}

	// 마이페이지
	@GetMapping("/mypage/admin/main")
	public String mypageAdminMain() {
		return "mypage/admin/main";
	}

	@GetMapping("/mypage/member/main")
	public String mypageMemberMain() {
		return "mypage/member/main";
	}

	// --------------------------------------호텔--------------------------------------------------------------------------------------
	// 호텔시설안내
	@GetMapping("/hotel/intro")
	public String hotelIntro() {
		return "hotel/intro";
	}

	// 호텔이용안내
	@GetMapping("/hotel/usage")
	public String hotelUsage() {
		return "hotel/usage";
	}

	// 호텔유의사항
	@GetMapping("/hotel/caution")
	public String hotelCaution() {
		return "hotel/caution";
	}

	// 호텔예약하기
	@GetMapping("/hotel/reservation/main2")
	public String hotelRreservationMain() {
		return "hotel/reservation/main";
	}

	// --------------------------------------유치원-------------------------------------------------------------------------------------
	// 유치원 시설안내
	@GetMapping("/school/intro")
	public String schoolIntro() {
		return "school/intro";
	}

	// 유치원 이용안내
	@GetMapping("/school/usage")
	public String schoolUsage() {
		return "school/usage";
	}

	// 유치원 알림장

	@GetMapping("/school/dogNotice/buttonTest")
	public String buttonTest() {
		return "/school/dogNotice/buttonTest";
	}

	// 행동교정신청

	// --------------------------------------고객서비스------------------------------------------------------------------------------------

	// 공지사항 메인페이지


	// QA 메인페이지


	// 자유게시판 메인페이지
	

}
