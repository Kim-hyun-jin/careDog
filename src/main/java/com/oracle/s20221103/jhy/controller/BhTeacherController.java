package com.oracle.s20221103.jhy.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.s20221103.asecurity.PrincipalDetails;
import com.oracle.s20221103.dto.BHCorrectionDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.jhy.service.BhTeacherService;
import com.oracle.s20221103.jhy.service.Paging;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BhTeacherController {
	private final BhTeacherService bhService;

	// 회원님들의 행동교정 신청 목록 (선생님) BhAllList
	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER')")
	@RequestMapping(value = "/school/BHCorrection/Teacher/BhAllList")
	public String BhAllList(BHCorrectionDTO bhDto, String currentPage, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		
		//Paging 작업
		Long memberId = bhDto.getMemberId();
		int totalCount = bhService.totalCount(memberId);
		Paging page = new Paging(totalCount, currentPage);
		bhDto.setStart(page.getStart());
		bhDto.setEnd(page.getEnd());
		System.out.println("BHMain page" + page);
		
//		List<BHCorrectionDTO> SelBhList = bhService.selBhList(bhDto);
		List<BHCorrectionDTO> SelBhTeacherList = bhService.selBhTeacherList(bhDto);
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("page", page);
		model.addAttribute("bhAllList", SelBhTeacherList);
		return "school/BHCorrection/Teacher/BhAllList";
	}

	// 행동교정 신청 내역 (선생님) BhAllSelect
	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER')")
	@RequestMapping(value = "/school/BHCorrection/Teacher/BhAllSelect")
	public String BhAllSelect(BHCorrectionDTO bhDto, Long appliNo,  Model model,
			@AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		
		List<BHCorrectionDTO> BhAllSelect = bhService.bhAllSelect(bhDto);
		model.addAttribute("bhAllSelect", BhAllSelect);
		System.out.println("BhAllSelect" + BhAllSelect);
		return "school/BHCorrection/Teacher/BhAllSelect";
	}

	// 행동교정 훈련 담당 전 select(선생님) BhTeacherAdd
	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER')")
	@RequestMapping(value = "/school/BHCorrection/Teacher/BhTeacherAdd")
	public String BhTeacherAdd(BHCorrectionDTO bhDto, Long appliNo, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		bhDto.setAppliNo(appliNo);
		System.out.println("bhDto" + bhDto);
		
		List<MemberDTO> teacherId = bhService.bhTeacherAddMemberId(bhDto);
		model.addAttribute("teacherId", teacherId);
		System.out.println("teacherId" + teacherId);
		
		List<BHCorrectionDTO> BhTeacherAddSelect = bhService.bhTeacherAddSelect(bhDto);
		model.addAttribute("BhTeacherAddSelect", BhTeacherAddSelect);
		System.out.println("BhTeacherAddSelect" + BhTeacherAddSelect);

		return "school/BHCorrection/Teacher/BhTeacherAdd";
	}

	// 행동교정 훈련 담당 (선생님) BhTeacherAddConfirm
	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER')")
	@RequestMapping(value="/school/BHCorrection/Teacher/BhTeacherAddConfirm")
	public String BhTeacherAddConfirm(BHCorrectionDTO bhDto, Model model, String currentPage, @AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		
		//Paging 작업
		Long memberId = bhDto.getMemberId();
		int totalCount = bhService.totalCount(memberId);
		Paging page = new Paging(totalCount, currentPage);
		bhDto.setStart(page.getStart());
		bhDto.setEnd(page.getEnd());
		System.out.println("BHMain page" + page);
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("page", page);
		
		int BhTeacherAddConfirm = bhService.bhTeacherAddConfirm(bhDto);
		System.out.println("bhTeacherAddConfirm bhDto ->" + bhDto);
		model.addAttribute("BhTeacherAddConfirm", BhTeacherAddConfirm);
		System.out.println("BhTeacherAddConfirm" + BhTeacherAddConfirm);
		
//		List<BHCorrectionDTO> SelBhList = bhService.selBhList(bhDto);
		List<BHCorrectionDTO> SelBhTeacherList = bhService.selBhTeacherList(bhDto);
		model.addAttribute("bhAllList", SelBhTeacherList);
		return "school/BHCorrection/Teacher/BhAllList";
	}
	
	// 행동교정 훈련 수정 전 select (선생님) BhTeacherUpdate
	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER')")
	@RequestMapping(value="/school/BHCorrection/Teacher/BhTeacherUpdate")
	public String BhTeacherUpdateSelect(BHCorrectionDTO bhDto, Long appliNo, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		bhDto.setAppliNo(appliNo);
		System.out.println("bhDto" + bhDto);

		List<BHCorrectionDTO> BhTeacherUpdateSelect = bhService.bhTeacherUpdateSelect(bhDto);
		model.addAttribute("BhTeacherUpdateSelect", BhTeacherUpdateSelect);
		System.out.println("BhTeacherUpdateSelect" + BhTeacherUpdateSelect);
		
		return "school/BHCorrection/Teacher/BhTeacherUpdate";
	}
	
	// 행동교정 훈련 수정 (선생님) BhTeacherUpdateConfirm
	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER')")
	@RequestMapping(value = "/school/BHCorrection/Teacher/BhTeacherUpdateConfirm")
	public String BhTeacherUpdate(BHCorrectionDTO bhDto, Long appliNo, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		bhDto.setAppliNo(appliNo);
		System.out.println("bhDto" + bhDto);
		
		int BhTeacherUpdate = bhService.bhTeacherUpdate(bhDto);
		model.addAttribute("BhTeacherUpdate", BhTeacherUpdate);
		System.out.println("BhTeacherUpdate" + BhTeacherUpdate);
		
		List<BHCorrectionDTO> BhAllSelect = bhService.bhAllSelect(bhDto);
		model.addAttribute("bhAllSelect", BhAllSelect);
		
		return "school/BHCorrection/Teacher/BhAllSelect";
	}
	
	// 행동교정 훈련 취소 (선생님) BhTeacherDelete
	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER')")
	@RequestMapping(value = "/school/BHCorrection/Teacher/BhTeacherDelete")
	public String BhTeacherDelete(BHCorrectionDTO bhDto, Long appliNo, Model model, String currentPage, @AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		bhDto.setAppliNo(appliNo);
		System.out.println("BhTeacherDeletebhDto -> " + bhDto);
		
		//Paging 작업
		Long memberId = bhDto.getMemberId();
		int totalCount = bhService.totalCount(memberId);
		Paging page = new Paging(totalCount, currentPage);
		bhDto.setStart(page.getStart());
		bhDto.setEnd(page.getEnd());
		System.out.println("BHMain page" + page);
		
		int BhTeacherDelete = bhService.bhTeacherDelete(bhDto);
		model.addAttribute("BhTeacherDelete", BhTeacherDelete);
		System.out.println("BhTeacherDelete" + BhTeacherDelete);
		
		List<BHCorrectionDTO> SelBhTeacherList = bhService.selBhTeacherList(bhDto);
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("page", page);
		model.addAttribute("bhAllList", SelBhTeacherList);
		
		return "school/BHCorrection/Teacher/BhAllList";
	}
	
	//내가 담당한 행동교정 훈련 목록 (선생님) BhTeacherList
	@PreAuthorize("principal.getPremiumChk() || hasAnyRole('ROLE_TEACHER')")
	@RequestMapping(value="/school/BHCorrection/Teacher/BhTeacherList")
	public String BhTeacherList(BHCorrectionDTO bhDto, Model model, String currentPage,  @AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		System.out.println("BhTeacherList bhDto-> " + bhDto);
		
		//Paging 작업
		Long memberId = bhDto.getMemberId();
		int totalCount = bhService.bhTeacherListTotalCount(memberId);
		Paging page = new Paging(totalCount, currentPage);
		bhDto.setStart(page.getStart());
		bhDto.setEnd(page.getEnd());
		System.out.println("BHMain page" + page);
		
		List<BHCorrectionDTO> BhTeacherList =bhService.bhTeacherList(bhDto);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("page", page);
		model.addAttribute("bhTeacherList", BhTeacherList);
		
		return "school/BHCorrection/Teacher/BhTeacherList";
	}
}
