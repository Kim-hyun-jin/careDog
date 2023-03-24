package com.oracle.s20221103.jhy.dao;

import java.util.List;

import com.oracle.s20221103.dto.BHCorrectionDTO;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.MemberDTO;

public interface BhMemberDao {

	//행동교정 신청 목록(사용자)
	List<BHCorrectionDTO> getBhList(BHCorrectionDTO bhDto);

	//행동교정 신청 (사용자)
	List<MemberDTO> getMeList(BHCorrectionDTO bhDto);
	List<DogDTO> getDogList(BHCorrectionDTO bhDto);

	int insBhList(BHCorrectionDTO bhDto);

	int insBhList(String memberId);

	//행동교정 신청 내역(사용자)
	List<BHCorrectionDTO> selServiceList(BHCorrectionDTO bhDto);

	//행동교정 신청 수정(사용자)
	int updServiceList(BHCorrectionDTO bhDto);
	List<BHCorrectionDTO> befUpdList(BHCorrectionDTO bhDto);

	//행동교정 신청 수정이 완료되었습니다.(사용자)
	int upBhList(BHCorrectionDTO bhDto);

	//BhMemberDelete
	int delBh(BHCorrectionDTO bhDto);

	List<BHCorrectionDTO> delSelBh(BHCorrectionDTO bhDto);

	Long delBhApp(Long appli_no);
	
	// BhMemberDelete
	List<BHCorrectionDTO> getAppliNo(Long getAppliNo);

	// BhMemberDeleteConfirm
	int bhDel(Long appliNo);

	//Paging 작업
	int totalCount(Long memberId);

}
