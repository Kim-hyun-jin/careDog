package com.oracle.s20221103.jhy.service;

import java.util.List;

import com.oracle.s20221103.dto.BHCorrectionDTO;
import com.oracle.s20221103.dto.MemberDTO;

public interface BhTeacherService {

	//BhAllList
	List<BHCorrectionDTO> selBhList(BHCorrectionDTO bhDto);
	List<BHCorrectionDTO> selBhTeacherList(BHCorrectionDTO bhDto);

	//BhAllSelect
	List<BHCorrectionDTO> bhAllSelect(BHCorrectionDTO bhDto);


	//BhTeacherAdd
	List<MemberDTO> bhTeacherAddMemberId(BHCorrectionDTO bhDto);
	List<BHCorrectionDTO> bhTeacherAddSelect(BHCorrectionDTO bhDto);

	//BhTeacherAddConfirm
	int bhTeacherAddConfirm(BHCorrectionDTO bhDto); //(221212 학원)

	//BhTeacherUpdateSelect
	List<BHCorrectionDTO> bhTeacherUpdateSelect(BHCorrectionDTO bhDto); //(221212 학원)

	//BhTeacherUpdate
	int bhTeacherUpdate(BHCorrectionDTO bhDto); //(221212 학원)

	//BhTeacherDelete
	int bhTeacherDelete(BHCorrectionDTO bhDto);

	//BhTeacherList
	List<BHCorrectionDTO> bhTeacherList(BHCorrectionDTO bhDto);

	//Paging 작업
	int totalCount(Long memberId);

	//bhAllSelect Paging 작업
	int bhTeacherListTotalCount(Long memberId);



}