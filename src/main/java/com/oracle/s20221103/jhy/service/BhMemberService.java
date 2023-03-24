package com.oracle.s20221103.jhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s20221103.dto.BHCorrectionDTO;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.MemberDTO;


public interface BhMemberService {
	
	//행동교정 신청 (사용자)
	List<MemberDTO> getMeList(BHCorrectionDTO bhDto);

	//행동교정 신청 (사용자)
	List<DogDTO> getDogList(BHCorrectionDTO bhDto);

	int insBhList(BHCorrectionDTO bhDto);

	//행동교정 신청 목록(사용자)
	List<BHCorrectionDTO> getBhList(BHCorrectionDTO bhDto);

	//행동교정 신청 내역(사용자)
	List<BHCorrectionDTO> selService(BHCorrectionDTO bhDto);

	//행동교정 신청 수정(사용자)
	List<BHCorrectionDTO> beforeUpdateList(BHCorrectionDTO bhDto);

	//행동교정 신청 수정이 완료되었습니다.(사용자)
	int upBhList(BHCorrectionDTO bhDto);

	// BhMemberDelete
	List<BHCorrectionDTO> bhDtoList(Long getAppliNo);

	Long bhDto(Long appli_no);
	
	// BhMemberDeleteConfirm
	int bhDel(Long appliNo);

	//Paging 작업
	int totalCount(Long memberId);


}