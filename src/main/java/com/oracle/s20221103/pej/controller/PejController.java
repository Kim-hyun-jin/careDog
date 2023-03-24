package com.oracle.s20221103.pej.controller;

import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oracle.s20221103.asecurity.PrincipalDetails;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.MemberDogDTO;
import com.oracle.s20221103.pej.service.PejService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class PejController {
	private final PejService ps;
	private final JavaMailSender mailSender;
	private final BCryptPasswordEncoder encoder;
	
	@GetMapping(value = "/mypage/member/myInfo1")
	public String selMyInfo(MemberDogDTO memberDog, Model model) {
		System.out.println("PejController selMyInfo start");
		MemberDogDTO selMyInfo = ps.selMyInfo(memberDog);
		model.addAttribute("selMyInfo", selMyInfo);
		System.out.println("PejController selMyInfo Finish");
		return "mypage/member/myInfo";
		
		
	}
	
	@RequestMapping(value = "/mypage/member/myInfo", method = {RequestMethod.GET, RequestMethod.POST})
	public String selMyInfo1(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("PejController selMyInfo1 Start...");

		MemberDogDTO selMyInfo1 = ps.selMyInfo1(principalDetails.getUser().getId());
		model.addAttribute("selMyInfo1", selMyInfo1);

		return "mypage/member/myInfo";
	}
	
	
	
	@RequestMapping(value = "/mypage/admin/memberList")
	public String selMemberDogList(Model model) {
		System.out.println("PejController selMemberDogList start");
		List<MemberDogDTO> selMemberDogList = ps.selMemberDogList();
		System.out.println("PejController selMemberDogList selMemberDogList.size()->"+selMemberDogList.size());
		model.addAttribute("selMemberDogList", selMemberDogList);
		
		return "mypage/admin/memberList";
	}
	
	
	@GetMapping(value = "/mypage/admin/detailMemberDog")
	public String detailMemberDog(Long id, Model model) {
		System.out.println("PejController detailMemberDog Start");
		List<MemberDogDTO> detailMemberDog = ps.detailMemberDog(id);
		MemberDogDTO detailMemberDog1 = ps.detailMemberDog1(id);
		List<MemberDogDTO> selRole = ps.selRole();
		model.addAttribute("selMemberDogList", detailMemberDog);
		model.addAttribute("detailMemberDog1", detailMemberDog1);
		model.addAttribute("selRole", selRole);
		System.out.println("PejController detailMemberDog Finish");
		return "mypage/admin/detailMemberDog";
	}
	
	@PostMapping(value = "/mypage/admin/updateMemberDog")
	public String updateMemberDog(MemberDogDTO memberDog, Model model) {
		System.out.println("PejController updateMemberDog Start");
		int updateCount = ps.updateMemberDog(memberDog);
		System.out.println("PejController updateMemberDog getRole->"+memberDog.getRole());
		System.out.println("PejController updateMemberDog getId->"+memberDog.getId());
		//int updateCount2 = ps.updateMemberDog2(memberDog);
		System.out.println("ps.updateMemberDog updateCount-->"+updateCount);
		model.addAttribute("upCnt", updateCount);
		//model.addAttribute("upCnt2", updateCount2);
		System.out.println("PejController updateMemberDog finish");
		//return "redirect:listEmp"; url만 이동한다
		return "forward:/mypage/admin/memberList"; //model이들을 다 저장해서 이동한다
	}
	
	@GetMapping(value = "/mypage/member/updateFormMyInfo")
	public String updateFormMyInfo(@RequestParam("selMyInfo1") Long id, Model model) {
		System.out.println("PejController Start updateFormMyInfo...");
		
		MemberDogDTO memberDog = ps.updateFormMyInfo(id);
		
		model.addAttribute("memberDog", memberDog);
		return "mypage/member/updateFormMyInfo";
	}
	
	@PostMapping(value = "/mypage/member/updateMyInfo")
	public String updateMyinfo(MemberDogDTO memberDog, Model model) {
		System.out.println("PejController updateMyinfo Start");
		int updateMyinfoCount = ps.updateMyinfo(memberDog);
		System.out.println("ps.updateMyinfo updateMyinfoCount-->"+updateMyinfoCount);
		model.addAttribute("upCnt", updateMyinfoCount);
		return "forward:/mypage/member/myInfo";
	}

//------------------------------------------------ 비밀번호 수정
	@RequestMapping(value = "/mypage/member/myPwEdit")
	public String myPwEdit(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("PejController myPwEdit Start...");
		if(principalDetails!=null) {
			MemberDTO myInfo = principalDetails.getUser();
			System.out.println("principal myinfo : "+myInfo.toString());
		}
		MemberDogDTO myPwEdit = ps.myPwEdit(principalDetails.getUser().getId());
		model.addAttribute("myPwEdit", myPwEdit);

		return "mypage/member/myPwEdit";
	}
	
	@ResponseBody
	@RequestMapping(value = "/mypage/member/myPwEditChk")
	public String myPwEditChk(String passwd, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("PejController myPwEditChk Start...");
		String result = ""+BCrypt.checkpw(passwd, principalDetails.getPassword());
		System.out.println("PejController myPwEditChk finish..."+result);
		return result;
	}
	
	@RequestMapping(value = "/mypage/member/myPwEditAfter")
	public String myPwEditAfter(Long id, Model model) {
		System.out.println("PejController myPwEditAfter Start...");

		MemberDogDTO memberDog = ps.myPwEditAfter(id); //aa
		model.addAttribute("memberDog", memberDog);
		System.out.println("PejController myPwEditAfter finish...");
		
		return "mypage/member/myPwEditAfter";
	}
	
	@RequestMapping(value = "/mypage/member/updateMyPw")
	public String myPwEdit1(MemberDogDTO memberDog, Model model) {
		System.out.println("PejController updateMyPwEdit Start...");

		memberDog.setPassword(encoder.encode(memberDog.getPassword()));
		int updateMyPwCount = ps.updateMyPw(memberDog);
		System.out.println("ps.updateMyinfo updateMyinfoCount-->"+updateMyPwCount);
		model.addAttribute("upCnt", updateMyPwCount);

		return "redirect:/mypage/member/myPwEdit";
	}
	
// ----------- 회원 탈퇴 --------------------------
	@RequestMapping(value = "/mypage/member/myInfoDrop")
	public String myInfoDrop(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("PejController myInfoDrop Start...");
		MemberDogDTO memberDog = ps.myInfoDrop(principalDetails.getUser().getId()); 
		System.out.println("PejController myInfoDrop id->"+memberDog.getId());
		model.addAttribute("memberDog", memberDog);

		return "mypage/member/myInfoDrop";
	}
	
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/mypage/member/myMDChk") public String
	 * myMDChk(String passwd, @AuthenticationPrincipal PrincipalDetails
	 * principalDetails) { System.out.println("PejController myMDChk Start...");
	 * String result = ""+BCrypt.checkpw(passwd, principalDetails.getPassword());
	 * System.out.println("PejController myMDChk finish..."+result); return result;
	 * }
	 */
	
	
	@RequestMapping(value = "/mypage/member/myInfoDropAfter")
	public String myInfoDropAfter(MemberDogDTO memberDog, Model model) {
		System.out.println("PejController myInfoDropAfter Start...");

		int myInfoDropAfterCount = ps.myInfoDropAfter(memberDog);
		System.out.println("PejController myInfoDropAfter myInfoDropAfterCount-->"+myInfoDropAfterCount);
		model.addAttribute("upCnt", myInfoDropAfterCount);

		return "mypage/member/myInfoDropEnd";
	}
	
	@RequestMapping(value="/mypage/mailTransport")
	public String mailTransport(HttpServletRequest request, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("mailSending...");
		MemberDogDTO memberDog = ps.selMyEmail(principalDetails.getUser().getId()); //aa
		String tomail = memberDog.getMemberEmail();             // 받는 사람 이메일
		System.out.println(tomail);
		String setfrom = "caredog.caredog@gmail.com";
		String title = "[CareDog] 탈퇴가 완료되었습니다.";                 // 제목
		HttpSession session = request.getSession();
		session.invalidate();

		try {
			//  Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true , "UTF-8");
			messageHelper.setFrom(setfrom);    // 보내는사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail);       // 받는사람 이메일
			messageHelper.setSubject(title);   // 메일제목은 생략이 가능하다
			messageHelper.setText("[CareDog] 탈퇴가 완료되었습니다. 이용해 주셔서 감사합니다."); // 메일 내용
			mailSender.send(message);
			model.addAttribute("check", 1);   // 정상 전달
		    // DB tempPassword Logic 구성 
		} catch (Exception e) {
			System.out.println("mailTransport e.getMessage()->"+e.getMessage());
			model.addAttribute("check", 2);  // 메일 전달 실패
		}
		
		return "redirect:/";
	}
	
	// ------ 강아지 정보
	
	@RequestMapping(value = "/mypage/admin/detailDogInfo")
	public String detailDogInfo(Long dogNo, Model model) {
		System.out.println("PejController detailDogInfo Start");
		MemberDogDTO detailDogInfo = ps.detailDogInfo(dogNo);
		model.addAttribute("detailDogInfo", detailDogInfo);
		System.out.println("PejController detailDogInfo Finish");
		return "mypage/admin/detailDogInfo";
	}
	
	@RequestMapping(value = "/mypage/admin/updateDogInfo")
	public String updateDogInfo(MemberDogDTO memberDog, Model model) {
		System.out.println("PejController updateDogInfo Start");
		int updateCount = ps.updateDogInfo(memberDog);
		System.out.println("PejController updateDogInfo dogNo->"+memberDog.getDogNo());
		int updateCount2 = ps.updateDogInfo2(memberDog);
		System.out.println("ps.updateDogInfo updateCount-->"+updateCount);
		model.addAttribute("upCnt", updateCount);
		model.addAttribute("upCnt2", updateCount2);
		System.out.println("PejController updateDogInfo finish");
		//return "redirect:listEmp"; url만 이동한다
		return "forward:/mypage/admin/memberList"; //model이들을 다 저장해서 이동한다
	}
}