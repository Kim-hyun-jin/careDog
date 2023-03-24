package com.oracle.s20221103.jhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s20221103.dto.BHCorrectionDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.jhy.dao.BhTeacherDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BhTeacherServiceImpl implements BhTeacherService {
	private final BhTeacherDao bhDao;

	//BhAllList
	@Override
	public List<BHCorrectionDTO> selBhList(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> bhAllList = bhDao.bhAllList(bhDto);
		return bhAllList;
	}
	@Override
	public List<BHCorrectionDTO> selBhTeacherList(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> selBhTeacherList = bhDao.selBhTeacherList(bhDto);
		return selBhTeacherList;
	}

	//BhAllSelect
	@Override
	public List<BHCorrectionDTO> bhAllSelect(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> bhAllSelect = bhDao.selBh(bhDto);
		return bhAllSelect;
	}
	@Override
	public List<MemberDTO> bhTeacherAddMemberId(BHCorrectionDTO bhDto) {
		List<MemberDTO> bhTeacherAddMemberId = bhDao.bhTeacherAddMemberId(bhDto);
		return bhTeacherAddMemberId;
	}
	@Override
	public List<BHCorrectionDTO> bhTeacherAddSelect(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> bhTeacherAddSelect = bhDao.bhTeacherAddSelect(bhDto);
		return bhTeacherAddSelect;
	}
	//BhTeacherAddConfirm
	@Override
	public int bhTeacherAddConfirm(BHCorrectionDTO bhDto) {
		int bhTeacherAddConfirm = bhDao.bhTeacherAddConfirm(bhDto);
		return bhTeacherAddConfirm;
	}

	//BhTeacherUpdateSelect
	@Override
	public List<BHCorrectionDTO> bhTeacherUpdateSelect(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> bhTeacherUpdateSelect = bhDao.bhTeacherUpdateSelect(bhDto);
		return bhTeacherUpdateSelect;
	}
	
	//BhTeacherUpdate
	@Override
	public int bhTeacherUpdate(BHCorrectionDTO bhDto) {
		int bhTeacherUpdate = bhDao.bhTeacherUpdate(bhDto);
		return bhTeacherUpdate;
	}

	//BhTeacherDelete
	@Override
	public int bhTeacherDelete(BHCorrectionDTO bhDto) {
		int bhTeacherDelete = bhDao.bhTeacherDelete(bhDto);
		return bhTeacherDelete;
	}

	//BhTeacherList
	@Override
	public List<BHCorrectionDTO> bhTeacherList(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> bhTeacherList = bhDao.bhTeacherList(bhDto);
		return bhTeacherList;
	}

	//Paging 작업
	@Override
	public int totalCount(Long memberId) {
		int totalCount = bhDao.totalCount(memberId);
		return totalCount;
	}

	//bhTeacherList Paging 작업
	@Override
	public int bhTeacherListTotalCount(Long memberId) {
		int bhTeacherListTotalCount = bhDao.bhTeacherListTotalCount(memberId);
		return bhTeacherListTotalCount;
	}

}
