package com.oracle.s20221103.jhy.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.s20221103.asecurity.PrincipalDetails;
import com.oracle.s20221103.dto.BHCorrectionDTO;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.jhy.dao.BhMemberDao;
import com.oracle.s20221103.jhy.service.BhMemberService;
import com.oracle.s20221103.jhy.service.Paging;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BhMemberController {
	private final BhMemberService bhService;

	// 행동교정 신청 목록(사용자)
	@PreAuthorize("principal.getPremiumChk()")
	@RequestMapping(value = "/school/BHCorrection/Member/BhMemberList")
	public String BHMain(BHCorrectionDTO bhDto, Model model, String currentPage, @AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		System.out.println("BHMain bhDto->" + bhDto);
		
		//Paging 작업
		Long memberId = bhDto.getMemberId();
		int totalCount = bhService.totalCount(memberId);
		Paging page = new Paging(totalCount, currentPage);
		bhDto.setStart(page.getStart());
		bhDto.setEnd(page.getEnd());
//		System.out.println("BHMain page" + page);
//		System.out.println("BHMain totalCount" + totalCount);
//		System.out.println("BHMain currentPage" + currentPage);
		
		List<BHCorrectionDTO> bhDtoList = bhService.getBhList(bhDto);
		System.out.println("BHMain bhDtoList" + bhDtoList.size());
		
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("page", page);
		model.addAttribute("bhList", bhDtoList);
		return "school/BHCorrection/Member/BhMemberList";
	}

	// 행동교정 신청 (사용자)
	@PreAuthorize("principal.getPremiumChk()")
	@RequestMapping(value = "/school/BHCorrection/Member/BhMemberInsert")
	public String BhMemberForm(BHCorrectionDTO bhDto, Model model,
			@AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());

		List<MemberDTO> meDtoList = bhService.getMeList(bhDto);
		System.out.println("BhMemberForm bhDto -> " + bhDto);
		List<DogDTO> dDtoList = bhService.getDogList(bhDto);
		
		model.addAttribute("bhDto", bhDto);
		model.addAttribute("meList", meDtoList);
		model.addAttribute("dogList", dDtoList);

		return "school/BHCorrection/Member/BhMemberInsert";
	}

	// 행동교정 신청 완료 (사용자)
	@PreAuthorize("principal.getPremiumChk()")
	@RequestMapping(value = "/school/BHCorrection/Member/BhMemberInsertConfirm")
	public String BhConfirm(BHCorrectionDTO bhDto, Model model, Long appliNo, @AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		bhDto.setAppliNo(appliNo);

		System.out.println("BhConfirm bhDto->" + bhDto);

		int bhDtoList = bhService.insBhList(bhDto);
		model.addAttribute("bhList", bhDtoList);
		
		List<BHCorrectionDTO> BhConfirm = bhService.selService(bhDto);
		model.addAttribute("BhConfirm", BhConfirm);
		
		return "school/BHCorrection/Member/BhMemberInsertConfirm";
	}

	// 행동교정 신청 내역(사용자)
	@RequestMapping(value = "/school/BHCorrection/Member/BhMemberSelect")
	public String BhSelect(BHCorrectionDTO bhDto, Model model, Long appliNo, @AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		bhDto.setAppliNo(appliNo);
		
		List<BHCorrectionDTO> bhDtoList = bhService.selService(bhDto);
		model.addAttribute("bhList", bhDtoList);
		System.out.println("bhDto->" + bhDto);
		return "school/BHCorrection/Member/BhMemberSelect";
	}

	// 행동교정 신청 수정(사용자)
	@PreAuthorize("principal.getPremiumChk()")
	@RequestMapping(value = "/school/BHCorrection/Member/BhMemberUpdate")
	public String BhMeUpd(BHCorrectionDTO bhDto, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		System.out.println("BhController BhMeUpd bhDto->" + bhDto);
		MemberDTO myInfo = principal.getUser();
		System.out.println("BhController BhMeUpd myInfo.getId()->" + myInfo.getId());
		bhDto.setMemberId(myInfo.getId());

		// MemberDTO 불러옴.
		List<MemberDTO> meDtoList = bhService.getMeList(bhDto);
		model.addAttribute("meList", meDtoList);
		System.out.println("meList 수정화면" + meDtoList);

		// 행동교정 신청 수정(사용자) - select
		List<BHCorrectionDTO> beforeUpdateList = bhService.beforeUpdateList(bhDto);
		model.addAttribute("bhList", beforeUpdateList);
		System.out.println("bhList 수정화면" + beforeUpdateList);

		List<DogDTO> dDtoList = bhService.getDogList(bhDto);
		System.out.println("dogList 수정 화면" + dDtoList);
		model.addAttribute("dogList", dDtoList);

		return "school/BHCorrection/Member/BhMemberUpdate";
	}

	// 행동교정 신청 수정이 완료되었습니다.(사용자)
	@PreAuthorize("principal.getPremiumChk()")
	@RequestMapping(value = "/school/BHCorrection/Member/BhMemberUpdateConfirm")
	public String BhMemberUpdateConfirm(BHCorrectionDTO bhDto, Model model,
			@AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		System.out.println("수정완료" + bhDto);
		bhDto.setMemberId(myInfo.getId());

		int BhUpList = bhService.upBhList(bhDto);

		model.addAttribute("BhUpList", BhUpList);
		return "school/BHCorrection/Member/BhMemberUpdateConfirm";
	}

	// 행동교정 신청을 취소하시겠습니까?
	// BhMemberDelete
	@PreAuthorize("principal.getPremiumChk()")
	@RequestMapping(value="/school/BHCorrection/Member/BhMemberDelete")
	 public String BhMemberDelete(BHCorrectionDTO bhDto, Long appliNo, Model model, @AuthenticationPrincipal PrincipalDetails principal) {
		MemberDTO myInfo = principal.getUser();
		bhDto.setMemberId(myInfo.getId());
		bhDto.setAppliNo(appliNo);
		model.addAttribute("appliNo", appliNo);
		System.out.println("BhMemberDelete appliNo ->" + appliNo);
		
		int bhDel = bhService.bhDel(appliNo);
		model.addAttribute("bhDelAppli", bhDel);
		return "school/BHCorrection/Member/BhMemberDeleteConfirm";
	 }

	// BhMemberDeleteConfirm
	@PreAuthorize("principal.getPremiumChk()")
	@RequestMapping(value = "/school/BHCorrection/Member/BhMemberDeleteConfirm")
	public String BhMemberDeleteConfirm(Long appliNo, Model model) {
//		int bhDel = bhService.bhDel(appliNo);
//		model.addAttribute("bhDelAppli", bhDel);

		return "school/BHCorrection/Member/BhMemberDeleteConfirm";
	}
}
