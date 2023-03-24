package com.oracle.s20221103.jhy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.BHCorrectionDTO;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BhMemberDaoImpl implements BhMemberDao {
	private final SqlSession session;

	@Override
	//행동교정 신청 (사용자)
	public List<MemberDTO> getMeList(BHCorrectionDTO bhDto) {
		List<MemberDTO> getMeList = session.selectList("meList", bhDto);
		return getMeList;
	}

	@Override
	//행동교정 신청 (사용자)
	public List<DogDTO> getDogList(BHCorrectionDTO bhDto) {
		List<DogDTO> getDogList = session.selectList("dogList", bhDto);
		return getDogList;
	}

	@Override
	public int insBhList(String memberId) {
		int insBhList = session.insert("insBhList", memberId);
		return insBhList;
	}

	@Override
	public int insBhList(BHCorrectionDTO bhDto) {
		int insBhList = session.insert("insBhList", bhDto);
		return insBhList;
	}

	@Override
	//행동교정 신청 목록(사용자)
	public List<BHCorrectionDTO> getBhList(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> getBhList = session.selectList("bhList", bhDto);
		return getBhList;
	}

	//행동교정 신청 내역(사용자)
	@Override
	public List<BHCorrectionDTO> selServiceList(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> selBhList = session.selectList("selBhList", bhDto);
		return selBhList;
	}

	//행동교정 신청 수정(사용자) - select
	@Override
	public List<BHCorrectionDTO> befUpdList(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> befUpList = session.selectList("befUpList", bhDto);
		return befUpList;
	}

	@Override
	public int updServiceList(BHCorrectionDTO bhDto) {
		int updBhList = session.update("updBhList", bhDto);
		return updBhList;
	}	 

	//행동교정 신청 수정이 완료되었습니다.(사용자)
	@Override
	public int upBhList(BHCorrectionDTO bhDto) {
		int upBhList = session.update("upBhList", bhDto);
		return upBhList;
	}

	//BhMemberDelete
	@Override
	public int delBh(BHCorrectionDTO bhDto) {
		int delBh = session.delete("delBh", bhDto);
		return delBh;
	}

	@Override
	public List<BHCorrectionDTO> delSelBh(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> bhDelSel = session.selectList("delSelBh", bhDto);
		return null;
	}

	@Override
	public Long delBhApp(Long appli_no) {
		Long delBhApp = (long) session.delete("delBhApp", appli_no);
		return delBhApp;
	}

	// BhMemberDelete
	@Override
	public List<BHCorrectionDTO> getAppliNo(Long getAppliNo) {
		List<BHCorrectionDTO> getAppliList = session.selectList("delSelBh", getAppliNo);
		return getAppliList;
	}

	// BhMemberDeleteConfirm
	@Override
	public int bhDel(Long appliNo) {
		int bhDel = 0;
		try {
			bhDel = session.delete("bhDel", appliNo);
		} catch (Exception e) {
			System.out.println("bhDel message->" + e.getMessage());
		}
		return bhDel;
	}

	//Paging 작업
	@Override
	public int totalCount(Long memberId) {
		int totalCount = 0;
		try {
			totalCount = session.selectOne("hyBHMainTotalCount", memberId);
			System.out.println("totalCount->" + totalCount);
		} catch (Exception e) {
			System.out.println("totalCount->" + totalCount);
			System.out.println("totalCount exception->" + e.getMessage());
		}
		return totalCount;
	}
}
