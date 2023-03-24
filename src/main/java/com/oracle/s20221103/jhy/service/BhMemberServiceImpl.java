package com.oracle.s20221103.jhy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s20221103.dto.BHCorrectionDTO;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.jhy.dao.BhMemberDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BhMemberServiceImpl implements BhMemberService {
	private final BhMemberDao bhDao;
	
	@Override
	//행동교정 신청 목록(사용자)
	public List<BHCorrectionDTO> getBhList(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> BhDto = bhDao.getBhList(bhDto);
		return BhDto;
	}
	
	@Override
	//행동교정 신청 (사용자)
	public List<MemberDTO> getMeList(BHCorrectionDTO bhDto) {
		List<MemberDTO> meDto = bhDao.getMeList(bhDto);
		return meDto;
	}

	@Override
	//행동교정 신청 (사용자)
	public List<DogDTO> getDogList(BHCorrectionDTO bhDto) {
		List<DogDTO> dogDto = bhDao.getDogList(bhDto);
		return dogDto;
	}

	@Override
	public int insBhList(BHCorrectionDTO bhDto) {
		int bhIns = bhDao.insBhList(bhDto);
		return bhIns;
	}

	//행동교정 신청 내역(사용자)
	@Override
	public List<BHCorrectionDTO> selService(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> selDto = bhDao.selServiceList(bhDto);
		return selDto;
	}

	//행동교정 신청 수정(사용자) - select
	@Override
	public List<BHCorrectionDTO> beforeUpdateList(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> beUpDto = bhDao.befUpdList(bhDto);
		return beUpDto;
	}

	//행동교정 신청 수정이 완료되었습니다.(사용자)
	@Override
	public int upBhList(BHCorrectionDTO bhDto) {
		int upBhDto = bhDao.upBhList(bhDto);
		return upBhDto;
	}

	@Override
	public Long bhDto(Long appli_no) {
		Long bhDelApp = (long) bhDao.delBhApp(appli_no);
		return bhDelApp;
	}

	// BhMemberDelete
	@Override
	public List<BHCorrectionDTO> bhDtoList(Long getAppliNo) {
		List<BHCorrectionDTO> bhDtoList = bhDao.getAppliNo(getAppliNo);
		return bhDtoList;
	}

	// BhMemberDeleteConfirm
	@Override
	public int bhDel(Long appliNo) {
		int bhDel = bhDao.bhDel(appliNo);
		return bhDel;
	}

	//Paging 작업
	@Override
	public int totalCount(Long memberId) {
		int totalCount = bhDao.totalCount(memberId);
		return totalCount;
	}
}
