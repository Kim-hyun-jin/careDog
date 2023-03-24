package com.oracle.s20221103.ssh.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.s20221103.asecurity.PrincipalDetails;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.QADTO;
import com.oracle.s20221103.ssh.service.Paging;
import com.oracle.s20221103.ssh.service.ShQAService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.asm.Advice.AssignReturned.ToAllArguments;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ShQAController {
	private final ShQAService qs;
	
	// 자주 묻는 질문으로 단순 연결
	//2022.12.12 김현진 수정 /customerService/QA/QA/ => /customerService/QA/main
	@GetMapping("/customerService/QA/main")
	public String qaPreq(Model model) {
		
		return "/customerService/QA/QA";
	}
	
	// QA 모든 목록 열기
	@GetMapping("/customerService/QA/question")
	public String qaQues(QADTO qadto, Model model, String currentPage) {
		int total1 = qs.totalQAAdmin();
		Paging page = new Paging(total1, currentPage);
		System.out.println("ShQAController qaQues total1 -> " + total1);
		
		qadto.setStart(page.getStart());
		qadto.setEnd(page.getEnd());
		
		List<QADTO> qaQuestion = qs.qaQues(qadto);
		
		int total2 = qs.totalQA();
		page = new Paging(total2, currentPage);
		System.out.println("ShQAController qaQues total2 -> " + total2);
		
		qadto.setStart(page.getStart());
		qadto.setEnd(page.getEnd());
		
		List<QADTO> qaAdmin = qs.qaAdmin(qadto);
		
		page = new Paging(total2, currentPage);
		
		model.addAttribute("question", qaQuestion);
		model.addAttribute("admin", qaAdmin);
		model.addAttribute("page", page);
		
		return "/customerService/QA/question";
	}
	
	// 질문 작성 페이지
	@GetMapping("/customerService/QA/questionInsert")
	public String qaQuesWrite(String currentPage, Model model) {
		model.addAttribute("currentPage", currentPage);
		
		return "/customerService/QA/questionInsert";
	}
	
	// DB에 qa insert
	@PostMapping("/customerService/QA/qaInsert")
	public String qaQueInsert(QADTO qadto, Model model) {
		log.info("ShQAController qaQueInsert Start...");
		qs.qaQuesInsert(qadto);
		
		return "redirect:question";
	}
	
	// 질문 내용과 답변 보기
	@GetMapping("/customerService/QA/questionContent")
	public String qaQuesContent(String currentPage, Integer questionNo, Model model) {
		QADTO qadto = qs.qaContent(questionNo);
		
		model.addAttribute("qadto", qadto);
		model.addAttribute("currentPage", currentPage);
		
		return "/customerService/QA/questionContent";
	}
	
	// 본인 작성 질문만 보기
	@GetMapping("/customerService/QA/myQuestion")
	public String qaMyQues(@AuthenticationPrincipal PrincipalDetails principal
							, QADTO qadto
							, String currentPage
							, Model model) {
		MemberDTO myInfo = null;
	    if (principal != null) {
			myInfo = principal.getUser();
		}
	    int total = qs.totalMyQA(myInfo.getId());
	    System.out.println("ShQAController qaMyQues myInfo.getId() -> " + myInfo.getId());
	    System.out.println("ShQAController qaMyQues total -> " + total);
		
		Paging page = new Paging(total, currentPage);
		qadto.setStart(page.getStart());
		qadto.setEnd(page.getEnd());
	    qadto.setId(principal.getId());
	    
	    List<QADTO> myQueList = qs.qaQuesMyList(qadto);
	    
	    model.addAttribute("QueList", myQueList);
	    model.addAttribute("page", page);
		
		return "/customerService/QA/myQuestion";
	}
	
	// my QnA 질문 작성 페이지
	@GetMapping("/customerService/QA/questionMyInsert")
	public String qaMyQuesWrite(String currentPage, Model model) {
		model.addAttribute("currentPage", currentPage);
		
		return "/customerService/QA/questionMyInsert";
	}
	
	// my QnA DB에 qa insert
	@PostMapping("/customerService/QA/qaMyInsert")
	public String qaQueMyInsert(QADTO qadto, Model model) {
		qs.qaQuesInsert(qadto);
		
		return "redirect:myQuestion";
	}
	
	// 본인 질문 내용과 답변 보기
	@GetMapping("/customerService/QA/questionMyContent")
	public String qaQuesMyContent(String currentPage, Integer questionNo, Model model) {
		QADTO qadto = qs.qaContent(questionNo);
		
		model.addAttribute("qadto", qadto);
		model.addAttribute("currentPage", currentPage);
		
		return "/customerService/QA/questionMyContent";
	}
	
	// 질문 수정 페이지
	@GetMapping("/customerService/QA/questionUpdateForm")
	public String qaQuesUpdate(QADTO qadto, String chk, String currentPage, Model model) {
		qadto = qs.qaContent(qadto.getQuestionNo());
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("chk", chk);
		model.addAttribute("qadto", qadto);
		
		return "/customerService/QA/questionUpdateForm";
	}
	
	// QnA DB에 qa update
	@PostMapping("/customerService/QA/qaUpdate")
	public String qaUpdate(QADTO qadto, String chk, String currentPage, Model model) {
		qs.qaContentUpdate(qadto);
		
		if (chk.equals("1")) {
			return "redirect:question";
		} else {
			return "redirect:myQuestion";
		}
	}
	
	// DB에 QA Delete 
	@GetMapping("/customerService/QA/questionDelete")
	public String qaDelete(QADTO qadto, String chk, String currentPage, Model model) {
		log.info("ShQAController qaDelete Start...");
		log.info("ShQAController qaDelete qadto.getQuestionNo() -> " + qadto.getQuestionNo());
		qs.qaContentDelete(qadto);
		
		if (chk.equals("1")) {
			return "redirect:question";
		} else {
			return "redirect:myQuestion";
		}
	}
	
	// 관리자 답변 페이지 (관리자 답변 )
	@GetMapping("/customerService/QA/questionAdminUpdateForm")
	public String quesAdminUpdate(QADTO qadto, String chk, String currentPage, Model model) {
		qadto = qs.qaContent(qadto.getQuestionNo());
		
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("chk", chk);
		model.addAttribute("qadto", qadto);
		
		return "/customerService/QA/questionAdminUpdateForm";
	}
	
	// QnA DB에 qa update (관리자 답변)
	@PostMapping("/customerService/QA/qaAdminUpdate")
	public String qaAdminUpdate(QADTO qadto, String currentPage, Model model) {
		qs.qaContentAdminUpdate(qadto);
		
		model.addAttribute("currentPage", currentPage);
		
		return "redirect:question";
	}
}