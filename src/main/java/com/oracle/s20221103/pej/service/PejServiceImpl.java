package com.oracle.s20221103.pej.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.MemberDogDTO;
import com.oracle.s20221103.pej.dao.PejDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PejServiceImpl implements PejService {
	private final PejDao pd;

	@Override
	public List<MemberDogDTO> selMemberDogList() {
		List<MemberDogDTO> selMemberDogList = null;
		System.out.println("PejServiceImpl selMemberList selMemberList Start..");
		selMemberDogList = pd.selMemberDogList();
		System.out.println("PejServiceImpl selMemberList selMemberList.size()->"+selMemberDogList.size());
		return selMemberDogList;
	}

	@Override
	public List<MemberDogDTO> detailMemberDog(Long id) {
		System.out.println("PejServiceImpl detailMemberDog Start");
		List<MemberDogDTO> detailMemberDog = null;
		
		detailMemberDog = pd.detailMemberDog(id);
		
		return detailMemberDog;
	
	}

	@Override
	public MemberDogDTO detailMemberDog1(Long id) {
		MemberDogDTO detailMemberDog1 = null;
		detailMemberDog1 = pd.detailMemberDog1(id);
		return detailMemberDog1;
	}

	@Override
	public List<MemberDogDTO> selRole() {
		System.out.println("PejServiceImpl selRole Start");
		List<MemberDogDTO> selRole = null;
		
		selRole = pd.selRole();
		
		return selRole;
	}

	@Override
	public int updateMemberDog(MemberDogDTO memberDog) {
		System.out.println("PejServiceImpl updateMemberDog start..");
		int updateCount = 0;
		updateCount = pd.updateMemberDog(memberDog);
		return updateCount;
	}

	@Override
	public MemberDogDTO selMyInfo(MemberDogDTO memberDog) {
		System.out.println("PejServiceImpl selMyInfo start..");
		MemberDogDTO selMyInfo = null;
		selMyInfo = pd.selMyInfo(memberDog);
		return selMyInfo;
	}

	@Override
	public int updateMyinfo(MemberDogDTO memberDog) {
		System.out.println("PejServiceImpl updateMyinfo start..");
		int updateMyinfoCount = 0;
		updateMyinfoCount = pd.updateMyinfo(memberDog);
		return updateMyinfoCount;
	}

	@Override
	public MemberDogDTO updateFormMyInfo(Long id) {
		System.out.println("PejServiceImpl updateFormMyInfo...");
		MemberDogDTO memberDog = null;
		memberDog = pd.updateFormMyInfo(id);
		return memberDog;
	}

	@Override
	public MemberDogDTO selMyInfo1(Long id) {
		System.out.println("PejServiceImpl selMyInfo1...");
		MemberDogDTO selMyInfo1 = null;
		selMyInfo1 = pd.selMyInfo1(id);
		return selMyInfo1;
	}

	
	// ------------------ 비밀번호 변경 
	@Override
	public MemberDogDTO myPwEdit(Long id) {
		System.out.println("PejServiceImpl myPwEdit...");
		MemberDogDTO myPwEdit = null;
		myPwEdit = pd.myPwEdit(id);
		return myPwEdit;
	}

	@Override
	public int updateMyPw(MemberDogDTO memberDog) {
		System.out.println("PejServiceImpl updateMyPw start..");
		int updateMyPwCount = 0;
		updateMyPwCount = pd.updateMyPw(memberDog);
		return updateMyPwCount;
	}

	@Override
	public MemberDogDTO myPwEditAfter(Long id) {
		System.out.println("PejServiceImpl myPwEditAfter...");
		MemberDogDTO memberDog = null;
		memberDog = pd.myPwEditAfter(id);
		return memberDog;
	}

	// ------------------ 회원 탈퇴
	@Override
	public MemberDogDTO myInfoDrop(Long id) {
		System.out.println("PejServiceImpl myInfoDrop...");
		MemberDogDTO myInfoDrop = null;
		myInfoDrop = pd.myInfoDrop(id);
		return myInfoDrop;
	}

	@Override
	public int myInfoDropAfter(MemberDogDTO memberDog) {
		System.out.println("PejServiceImpl myInfoDropAfter start..");
		int myInfoDropAfterCount = 0;
		myInfoDropAfterCount = pd.myInfoDropAfter(memberDog);
		return myInfoDropAfterCount;
	}

	@Override
	public int memberDogEmail(MemberDogDTO memberDog) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberDogDTO selMyEmail(Long id) {
		System.out.println("PejServiceImpl selMyEmail...");
		MemberDogDTO memberDog = null;
		memberDog = pd.selMyEmail(id);
		return memberDog;
	}

	// ------ 관리자 회원정보 수정
	@Override
	public int updateMemberDog2(MemberDogDTO memberDog) {
		System.out.println("PejServiceImpl updateMemberDog2 start..");
		int updateCount2 = 0;
		updateCount2 = pd.updateMemberDog2(memberDog);
		return updateCount2;
	}

	// 강아지 정보	

	@Override
	public MemberDogDTO detailDogInfo(Long dogNo) {
		MemberDogDTO detailDogInfo = null;
		detailDogInfo = pd.detailDogInfo(dogNo);
		return detailDogInfo;
	}

	@Override
	public int updateDogInfo(MemberDogDTO memberDog) {
		System.out.println("PejServiceImpl updateDogInfo start..");
		int updateCount1 = 0;
		updateCount1 = pd.updateDogInfo(memberDog);
		return updateCount1;
	}

	@Override
	public int updateDogInfo2(MemberDogDTO memberDog) {
		System.out.println("PejServiceImpl updateDogInfo2 start..");
		int updateCount2 = 0;
		updateCount2 = pd.updateDogInfo2(memberDog);
		return updateCount2;
	}










		

	

}