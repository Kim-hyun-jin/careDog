package com.oracle.s20221103.jhy.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.BHCorrectionDTO;
import com.oracle.s20221103.dto.MemberDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BhTeacherDaoImpl implements BhTeacherDao {
	private final SqlSession session;
	
	//BhAllList
	@Override
	public List<BHCorrectionDTO> bhAllList(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> bhAllList = session.selectList("bhAllList", bhDto);
		return bhAllList;
	}
	@Override
	public List<BHCorrectionDTO> selBhTeacherList(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> selBhTeacherList = session.selectList("selBhTeacherList", bhDto);
		return selBhTeacherList;
	}

	//BhAllSelect
	@Override
	public List<BHCorrectionDTO> selBh(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> selBh = session.selectList("selBh", bhDto);
		return selBh;
	}

	//BhTeacherAdd
//(221212 학원)
	@Override
	public List<MemberDTO> bhTeacherAddMemberId(BHCorrectionDTO bhDto) {
		List<MemberDTO> bhTeacherAddMemberId = session.selectList("bhTeacherAddMemberId", bhDto);
		return bhTeacherAddMemberId;
	}
	//(221212 학원)
	@Override
	public List<BHCorrectionDTO> bhTeacherAddSelect(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> bhTeacherAddSelect = session.selectList("bhTeacherAddSelect", bhDto);
		return bhTeacherAddSelect;
	}
	//BhTeacherAddConfirm (221212 학원)
	@Override
	public int bhTeacherAddConfirm(BHCorrectionDTO bhDto) {
		int bhTeacherAddConfirm = session.update("bhTeacherAddConfirm", bhDto);
		return bhTeacherAddConfirm;
	}

	//BhTeacherUpdateSelect //(221212 학원)
	@Override
	public List<BHCorrectionDTO> bhTeacherUpdateSelect(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> bhTeacherUpdateSelect = session.selectList("bhTeacherUpdateSelect", bhDto);
		return bhTeacherUpdateSelect;
	}

	//BhTeacherUpdate //(221212 학원)
	@Override
	public int bhTeacherUpdate(BHCorrectionDTO bhDto) {
		int bhTeacherUpdate = session.update("bhTeacherUpdate", bhDto);
		return bhTeacherUpdate;
	}

	//BhTeacherDelete

//	@Override
//	public List<MemberDTO> selTeacherId(BHCorrectionDTO teacherId) {
//		List<MemberDTO> selTeacherId = session.selectList("teacherId", teacherId);
//		return selTeacherId;
//	}

	@Override
	public int bhTeacherDelete(BHCorrectionDTO bhDto) {
		int bhTeacherDelete = session.update("bhTeacherDelete", bhDto);
		return bhTeacherDelete;
	}

	//BhTeacherList
	@Override
	public List<BHCorrectionDTO> bhTeacherList(BHCorrectionDTO bhDto) {
		List<BHCorrectionDTO> bhTeacherList = session.selectList("bhTeacherList", bhDto);
		return bhTeacherList;
	}

	//Paging 작업
	@Override
	public int totalCount(Long memberId) {
		int totalCount = 0;
		try {
			totalCount = session.selectOne("hyBhAllListTotalCount", memberId);
		} catch (Exception e) {
			System.out.println("totalCount exception->" + e.getMessage());
		}
		return totalCount;
	}

	//bhTeacherList Paging 작업
	@Override
	public int bhTeacherListTotalCount(Long memberId) {
		int bhTeacherListTotalCount = 0;
		try {
			bhTeacherListTotalCount = session.selectOne("bhTeacherListTotalCount", memberId);
		} catch (Exception e) {
			System.out.println("totalCount exception->" + e.getMessage());
		}
		return bhTeacherListTotalCount;
	}

}