package com.oracle.s20221103.ssh.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.s20221103.asecurity.PrincipalDetails;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.HotelDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.ResPayDTO;
import com.oracle.s20221103.dto.ResVO;
import com.oracle.s20221103.dto.ReservationDTO;
import com.oracle.s20221103.lhj.service.LhjService;
import com.oracle.s20221103.ssh.service.KakaoPay;
import com.oracle.s20221103.ssh.service.Paging;
import com.oracle.s20221103.ssh.service.ShHotelService;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@Controller
@Setter
@Log
@RequiredArgsConstructor
public class ShHotelController {
	private final ShHotelService hs;
	private final JavaMailSender mailSender;
	private final LhjService lhjs;
	
	@Setter(onMethod_ = @Autowired)
	private KakaoPay kakaopay;
	    
	    
	@GetMapping("/kakaoPay")
	public void kakaoPayGet() {
	        
	}
	    
	@PostMapping("/kakaoPay")
	public String kakaoPay(HttpServletRequest request
						 , @RequestParam("roomNum") int roomNum
						 , @RequestParam("resStartdate") String resStartdate
						 , @RequestParam("resEnddate") String resEnddate
						 , @RequestParam("size") int size
						 , @RequestParam("gen") int gen
						 , @RequestParam("dogName") String dogName
						 , @RequestParam("roomPrice") int roomPrice
						 , @AuthenticationPrincipal PrincipalDetails principal) throws ParseException {
		log.info("kakaoPay post............................................");
		System.out.println("kakaoPay roomNum -> " + roomNum);
	    HotelDTO hotel = hs.roomchoice(roomNum);
	    
	    MemberDTO myInfo = null;
	    if (principal != null) {
			myInfo = principal.getUser();
		}
	    
	    ResVO resVO = new ResVO();
	    resVO.setRoomNum(roomNum);
	    resVO.setResStartdate(resStartdate);
	    resVO.setResEnddate(resEnddate);
	    resVO.setId(myInfo.getId());
	    resVO.setDogSize(size);
	    resVO.setDeSexing(gen);
	    resVO.setDogName(dogName);
	    
	    String userId = principal.getUsername();
	    
	    HttpSession session = request.getSession();
	    session.setAttribute("resVO", resVO);
	    
	    // 호텔 일 수 계산
	    Date format1 = new SimpleDateFormat("yyyy-MM-dd").parse(resStartdate);
	    Date format2 = new SimpleDateFormat("yyyy-MM-dd").parse(resEnddate);
	    long diffSec = (format2.getTime() - format1.getTime()) / 1000; //초 차이
	    Long diffDays = diffSec / (24*60*60); //일자수 차이
	    System.out.println("ShHotelController kakaoPay " + diffDays + "일 차이");
	    resVO.setResPrice(roomPrice*diffDays.intValue());
		
		return "redirect:" + kakaopay.kakaoPayReady(hotel, diffDays, userId);
	}
	
	// 새로고침 데이터 날아감!?
	@GetMapping("/kakaoPaySuccess")
	public String kakaoPaySuccess( @RequestParam("pg_token") String pg_token
								 , @AuthenticationPrincipal PrincipalDetails principal
								 , HttpServletRequest request, Model model) {
		log.info("kakaoPaySuccess get............................................");
		log.info("kakaoPaySuccess pg_token : " + pg_token);
		
		model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
		
		MemberDTO myInfo = null;
	    if (principal != null) {
			myInfo = principal.getUser();
		}
		
		HttpSession session = request.getSession();
		ResVO resVO = (ResVO) session.getAttribute("resVO");
		System.out.println("ks -> " + resVO);
		// 예약 내역 + 결제 내역 추가
		hs.resInsert(resVO);
		// 예약 번호 가져오기
		long resNum = hs.resNoSelect();
		
		// 결제완료 메일 전송
		System.out.println("mailSending...");
		String tomail = myInfo.getMemberEmail();              // 받는 사람 이메일
		System.out.println(tomail);
		String setfrom = "ssh01074139957@gmail.com";
		String title = String.format("[케어독] 예약 확정 예약번호 : %d", resNum);                 // 제목
		
		
		try {
			//  Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true , "UTF-8");
			messageHelper.setFrom(setfrom);    // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail);       // 받는사람 이메일
			messageHelper.setSubject(title);   // 메일제목은 생략이 가능하다
			messageHelper.setText(String.format("%s 고객님의 예약이 확정되었습니다.\n감사합니다.", myInfo.getMemberName())); // 메일 내용
			mailSender.send(message);
			model.addAttribute("check", 1);   // 정상 전달
		} catch (Exception e) {
			System.out.println("mailTransport e.getMessage()->"+e.getMessage());
			model.addAttribute("check", 2);  // 메일 전달 실패
		}
		
		
		return "hotel/reservation/paySuccess";
	}
	
	@GetMapping(value = "/hotel/reservation/info")
	public String resInfo(@AuthenticationPrincipal PrincipalDetails principal
						, @RequestParam("roomNum") int roomNum
						, @RequestParam("resStartdate") String resStartdate
						, @RequestParam("resEnddate") String resEnddate
						, DogDTO dog
						, Model model) throws ParseException {
		System.out.println("ShHotelController resInfo");
		
		HotelDTO hotel = hs.roomchoice(roomNum);
		
		MemberDTO member = null;
		
		if (principal != null) {
			member = principal.getUser();
			dog.setId(member.getId());
		}
		
		// 호텔 일 수 계산
	    Date format1 = new SimpleDateFormat("yyyy-MM-dd").parse(resStartdate);
	    Date format2 = new SimpleDateFormat("yyyy-MM-dd").parse(resEnddate);
	    long diffSec = (format2.getTime() - format1.getTime()) / 1000; //초 차이
	    Long diffDays = diffSec / (24*60*60); //일자수 차이
	    System.out.println("ShHotelController kakaoPay " + diffDays + "일 차이");
		
		List<DogDTO> dogList = lhjs.myDogList(dog);
		System.out.println(dogList.size());
		
		model.addAttribute("hotel", hotel);
		model.addAttribute("dogList", dogList);
		model.addAttribute("member", member);
		model.addAttribute("difD", diffDays);
		model.addAttribute("resStartdate", resStartdate);
		model.addAttribute("resEnddate", resEnddate);
		
		return "hotel/reservation/infoInput";
	}
	
	@GetMapping("/mypage/admin/resList")
	public String resList(ReservationDTO reservationDTO, String currentPage
						, String name, Model model) {
		System.out.println("ShHotelController resList...");
		System.out.println(name);
		int total = 0;
		
		if (name == null) {
			total = hs.totalRes();
		} else {
			total = hs.totalRes(name);
		}
		
		System.out.println("ShHotelController resList total -> " + total);
		Paging page = new Paging(total, currentPage);
		
		reservationDTO.setStart(page.getStart());
		reservationDTO.setEnd(page.getEnd());
		
		List<ReservationDTO> listRes = hs.resList(reservationDTO);
		System.out.println("ShHotelController resList listRes.get(0).getId() -> " + listRes.get(0).getId());
		System.out.println("ShHotelController resList listRes.get(0).getRes_no() -> " + listRes.get(0).getResNo());
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
		String formatTo = today.format(formatter);
		
		model.addAttribute("res", listRes);
		model.addAttribute("page", page);
		model.addAttribute("today", formatTo);
		model.addAttribute("name", name);
		
		return "mypage/admin/resList";
	}
	
	@GetMapping("/mypage/admin/resListSearch")
	public String resListSearch(ReservationDTO reservationDTO, String currentPage, String name, Model model) {
		System.out.println("ShHotelController resList...");
		
		int total = hs.totalRes(name);
		System.out.println("ShHotelController resList total -> " + total);
		Paging page = new Paging(total, currentPage);
		
		reservationDTO.setStart(page.getStart());
		reservationDTO.setEnd(page.getEnd());
		
		List<ReservationDTO> listRes = hs.resList(reservationDTO, name);
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
		String formatTo = today.format(formatter);
		
		System.out.println("노 -> " + listRes.size());
		
		model.addAttribute("page", page);
		model.addAttribute("today", formatTo);
		model.addAttribute("name", name);
		model.addAttribute("res", listRes);
		model.addAttribute("size", listRes.size());
		
		return "mypage/admin/resList";
	}
	
	@GetMapping("/mypage/admin/resPayList")
	public String resPayList(ResPayDTO resPayDTO, String currentPage, Model model) {
		System.out.println("ShHotelController resPayList...");
		
		int total = hs.totalResPay();
		
		Paging page = new Paging(total, currentPage);
		
		resPayDTO.setStart(page.getStart());
		resPayDTO.setEnd(page.getEnd());
		
		List<ResPayDTO> listResPay = hs.resPayList(resPayDTO);
		model.addAttribute("resPay", listResPay);
		model.addAttribute("page", page);
		
		return "mypage/admin/resPayList";
	}
	
	@GetMapping("/mypage/member/memberResList")
	public String MemberResList(  @AuthenticationPrincipal PrincipalDetails principal
								, ReservationDTO reservationDTO
								, String currentPage
								, Model model) {
		System.out.println("ShHotelController MemberResList...");
		
		MemberDTO member = null;
		if (principal != null) {
			member = principal.getUser();
		}
		
		int total = hs.totalRes(member.getId());
		System.out.println("ShHotelController MemberResList total -> "+ total);
		
		Paging page = new Paging(total, currentPage);
		
		reservationDTO.setStart(page.getStart());
		reservationDTO.setEnd(page.getEnd());
		
		System.out.println("ShHotelController MemberResList member.getId() -> " + member.getId());
		List<ReservationDTO> MemberListRes = hs.resList(member.getId(), reservationDTO);
//		System.out.println("ShHotelController MemberResList listRes.get(0).getId() -> " + MemberListRes.get(0).getId());
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
		String formatTo = today.format(formatter);
		
		System.out.println(today);
		
		model.addAttribute("resList", MemberListRes);
		model.addAttribute("today", formatTo);
		model.addAttribute("page", page);
		
		return "mypage/member/memberResList";
	}
	
	// 예약 내역 기간 조회
	@GetMapping("/mypage/member/memberResListSearch")
	public String MemberResListSearch(  @AuthenticationPrincipal PrincipalDetails principal
			, @RequestParam("period") String period
			, ReservationDTO reservationDTO
			, String currentPage
			, Model model) {
		System.out.println("ShHotelController MemberResList...");
		
		MemberDTO member = null;
		if (principal != null) {
			member = principal.getUser();
		}
		
		int total = hs.totalRes(member.getId());
		System.out.println("ShHotelController MemberResList total -> "+ total);
		
		Paging page = new Paging(total, currentPage);
		
		reservationDTO.setStart(page.getStart());
		reservationDTO.setEnd(page.getEnd());
		
		System.out.println("ShHotelController MemberResList member.getId() -> " + member.getId());
		List<ReservationDTO> MemberListRes = hs.resList(member.getId(), period, reservationDTO);
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd");
		String formatTo = today.format(formatter);
		
		System.out.println(today);
		
		model.addAttribute("resList", MemberListRes);
		model.addAttribute("today", formatTo);
		model.addAttribute("page", page);
		model.addAttribute("period", period);
		model.addAttribute("size", MemberListRes.size());
		
		return "mypage/member/memberResList";
	}
	
	@PostMapping("/mypage/member/memberResUpdate")
	public String memberResUpdate(Long resNo, String currentPage, Model model) {
		System.out.println("ShHotelController memberResUpdate Start...");
		System.out.println(resNo);
		System.out.println(currentPage);
		// 예약취소 -> 예약 수정 / 결제 수정
		hs.resUpdate(resNo);
		
		model.addAttribute("currentPage", currentPage);
		
		return "redirect:memberResList";
	}
	
	@PostMapping("/mypage/admin/memberResAdminUpdate")
	public String memberResAdminUpdate(@RequestParam("resNo") Long resNo, String currentPage, Model model) {
		System.out.println("ShHotelController memberResUpdate Start...");
		System.out.println(resNo);
		System.out.println(currentPage);
		// 예약취소 -> 예약 수정 / 결제 수정
		hs.resUpdate(resNo);
		
		model.addAttribute("currentPage", currentPage);
		
		return "redirect:resList";
	}
	
	// myDog 선택시 ajax로 데이터 불러오기
	@ResponseBody
	@RequestMapping(value = "/hotel/reservation/getDogInfo")
	public DogDTO getDogInfo(Long  dogNo, Model model) {
		System.out.println("ShHotelController getDogInfo");
		DogDTO dog = lhjs.mydogInfo(dogNo);
		
		return  dog;
	}
}
