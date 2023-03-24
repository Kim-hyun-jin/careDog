package com.oracle.s20221103.jhy.dao;

import java.util.List;

import com.oracle.s20221103.dto.BHCorrectionDTO;
import com.oracle.s20221103.dto.MemberDTO;

public interface BhTeacherDao {
	
	//BhAllList
	List<BHCorrectionDTO> bhAllList(BHCorrectionDTO bhDto);
	List<BHCorrectionDTO> selBhTeacherList(BHCorrectionDTO bhDto);

	//BhAllSelect
	List<BHCorrectionDTO> selBh(BHCorrectionDTO bhDto);

	//BhTeacherAdd
	List<MemberDTO> bhTeacherAddMemberId(BHCorrectionDTO bhDto);
	List<BHCorrectionDTO> bhTeacherAddSelect(BHCorrectionDTO bhDto);
	//BhTeacherAddConfirm
	int bhTeacherAddConfirm(BHCorrectionDTO bhDto);

	//BhTeacherUpdateSelect
	List<BHCorrectionDTO> bhTeacherUpdateSelect(BHCorrectionDTO bhDto);

	//BhTeacherUpdate
	int bhTeacherUpdate(BHCorrectionDTO bhDto);

	//BhTeacherDelete
	int bhTeacherDelete(BHCorrectionDTO bhDto);

	//BhTeacherList
	List<BHCorrectionDTO> bhTeacherList(BHCorrectionDTO bhDto);

	//Paging 작업
	int totalCount(Long memberId);

	//bhTeacherList Paging 작업
	int bhTeacherListTotalCount(Long memberId);



}
