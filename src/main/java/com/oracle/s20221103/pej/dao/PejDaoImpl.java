package com.oracle.s20221103.pej.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.MemberDogDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PejDaoImpl implements PejDao {
	private final SqlSession session;


	@Override
	public List<MemberDogDTO> selMemberDogList() {
		List<MemberDogDTO> selMemberDogList = null;
		try {
			selMemberDogList = session.selectList("selMemberList");
		} catch (Exception e) {
			System.out.println("PejDaoImpl selMemberDogList Exception->"+e.getMessage());
		}
		return selMemberDogList;
	}


	@Override
	public List<MemberDogDTO> detailMemberDog(Long id) {
		List<MemberDogDTO> detailMemberDog = null;
		System.out.println("PejDaoImpl detailMemberDog detailMemberDog start");
		try {
			detailMemberDog = session.selectList("detailMemberDog", id);
			System.out.println("PejDaoImpl detailMemberDog getMember_name->"+detailMemberDog.size());
		} catch (Exception e) {
			//System.out.println("PejDaoImpl detailMemberDog memberDog.getid->"+detailMemberDog.get(2).getMemberName());
			System.out.println("PejDaoImpl detailMemberDog Exception ->"+e.getMessage());
		}
		return detailMemberDog;
	}


	@Override
	public MemberDogDTO detailMemberDog1(Long id) {
		MemberDogDTO detailMemberDog1 = null;
		System.out.println("PejDaoImpl detailMemberDog1 detailMemberDog1 start");
		
		try {
			detailMemberDog1 = session.selectOne("detailMemberDog1", id);
			System.out.println("PejDaoImpl detailMemberDog1 selectOne");
		} catch (Exception e) {
			System.out.println("PejDaoImpl detailMemberDog1 Exception ->"+e.getMessage());
		}
		return detailMemberDog1;
	}


	@Override
	public List<MemberDogDTO> selRole() {
		List<MemberDogDTO> selRole = null;
		System.out.println("PejDaoImpl selRole selRole start");
		try {
			selRole = session.selectList("selRole");
			System.out.println("PejDaoImpl selRole selRole.size()->"+selRole.size());
		} catch (Exception e) {
			//System.out.println("PejDaoImpl detailMemberDog memberDog.getid->"+detailMemberDog.get(2).getMemberName());
			System.out.println("PejDaoImpl detailMemberDog Exception ->"+e.getMessage());
		}
		return selRole;
	}


	@Override
	public MemberDogDTO updateRole(Long id) {
		MemberDogDTO updateRole = null;
		System.out.println("PejDaoImpl updateRole updateRole start");
		
		try {
			updateRole = session.selectOne("updateRole");
			System.out.println("PejDaoImpl updateRole selectOne");
		} catch (Exception e) {
			System.out.println("PejDaoImpl updateRole Exception ->"+e.getMessage());
		}
		return updateRole;
	}


	@Override
	public int updateMemberDog(MemberDogDTO memberDog) {
		System.out.println("PejDaoImpl updateMemberDog start...");
		int updateCount = 0;
		try {
			updateCount = session.update("updateMemberDog", memberDog);
		} catch (Exception e) {
			System.out.println("PejDaoImpl updateMemberDog Exception->"+e.getMessage());
		}
		return updateCount;
	}


	@Override
	public MemberDogDTO selMyInfo(MemberDogDTO memberDog) {
		System.out.println("PejDaoImpl selMyInfo start...");	
		MemberDogDTO selMyInfo = null;
		try {
			selMyInfo = session.selectOne("selMyInfo", memberDog);
			System.out.println("PejDaoImpl selMyInfo selectOne");
		} catch (Exception e) {
			System.out.println("PejDaoImpl selMyInfo Exception ->"+e.getMessage());
		}
		return selMyInfo;
	}


	@Override
	public int updateMyinfo(MemberDogDTO memberDog) {
		System.out.println("PejDaoImpl updateMyinfo start...");
		int updateMyinfo = 0;
		try {
			updateMyinfo = session.update("updateMyinfo", memberDog);
		} catch (Exception e) {
			System.out.println("PejDaoImpl updateMyinfo Exception->"+e.getMessage());
		}
		return updateMyinfo;
	}


	@Override
	public MemberDogDTO updateFormMyInfo(Long id) {
		System.out.println("PejDaoImpl updateFormMyInfo start..");
		MemberDogDTO memberDog = new MemberDogDTO();
		try {
			//						mapper ID,		Parameter
			memberDog = session.selectOne("updateFormMyInfo", id);
			System.out.println("PejDaoImpl updateFormMyInfo memberDog.getId()->"+memberDog.getId());
		} catch (Exception e) {
			System.out.println("PejDaoImpl updateFormMyInfo Exception->"+e.getMessage());
		}
		return memberDog;
	}


	@Override
	public MemberDogDTO selMyInfo1(Long id) {
		System.out.println("PejDaoImpl selMyInfo1 start..");
		MemberDogDTO selMyInfo1 = new MemberDogDTO();
		try {
			//						mapper ID,		Parameter
			selMyInfo1 = session.selectOne("selMyInfo1", id);
			System.out.println("PejDaoImpl selMyInfo1 getEname->"+selMyInfo1.getId());
		} catch (Exception e) {
			System.out.println("PejDaoImpl selMyInfo1 Exception->"+e.getMessage());
		}
		return selMyInfo1;
	}

// ------------------ 비밀번호 변경 
	@Override
	public MemberDogDTO myPwEdit(Long id) {
		System.out.println("PejDaoImpl myPwEdit start..");
		MemberDogDTO myPwEdit = new MemberDogDTO();
		try {
			//						mapper ID,		Parameter
			myPwEdit = session.selectOne("myPwEdit", id);
			System.out.println("PejDaoImpl selMyInfo1 getEname->"+myPwEdit.getId());
		} catch (Exception e) {
			System.out.println("PejDaoImpl selMyInfo1 Exception->"+e.getMessage());
		}
		return myPwEdit;
	}


	@Override
	public int updateMyPw(MemberDogDTO memberDog) {
		System.out.println("PejDaoImpl updateMyPw start...");
		int updateMyPw = 0;
		try {
			updateMyPw = session.update("updateMyPw", memberDog);
		} catch (Exception e) {
			System.out.println("PejDaoImpl updateMyPw Exception->"+e.getMessage());
		}
		return updateMyPw;
	}


	@Override
	public MemberDogDTO myPwEditAfter(Long id) {
		System.out.println("PejDaoImpl myPwEditAfter start..");
		MemberDogDTO memberDog = new MemberDogDTO();
		try {
			//						mapper ID,		Parameter
			memberDog = session.selectOne("myPwEditAfter", id);
			System.out.println("PejDaoImpl myPwEditAfter memberDog.getId()->"+memberDog.getId());
		} catch (Exception e) {
			System.out.println("PejDaoImpl myPwEditAfter Exception->"+e.getMessage());
		}
		return memberDog;
	}

	// ------------------ 회원탈퇴 
	@Override
	public MemberDogDTO myInfoDrop(Long id) {
		System.out.println("PejDaoImpl myInfoDrop start..");
		MemberDogDTO myInfoDrop = new MemberDogDTO();
		try {
			//						mapper ID,		Parameter
			myInfoDrop = session.selectOne("myInfoDrop", id);
			System.out.println("PejDaoImpl selMyInfo1 getEname->"+myInfoDrop.getId());
		} catch (Exception e) {
			System.out.println("PejDaoImpl selMyInfo1 Exception->"+e.getMessage());
		}
		return myInfoDrop;
		
		
		
	}


	@Override
	public int myInfoDropAfter(MemberDogDTO memberDog) {
		System.out.println("PejDaoImpl myInfoDropAfter start...");
		int myInfoDropAfter = 0;
		try {
			myInfoDropAfter = session.update("myInfoDropAfter", memberDog);
		} catch (Exception e) {
			System.out.println("PejDaoImpl updateMyPw Exception->"+e.getMessage());
		}
		return myInfoDropAfter;
	}


	@Override
	public MemberDogDTO selMyEmail(Long id) {
		System.out.println("PejDaoImpl mailTransport start..");
		MemberDogDTO selMyEmail = new MemberDogDTO();
		try {
			//						mapper ID,		Parameter
			selMyEmail = session.selectOne("selMyEmail", id);
			System.out.println("PejDaoImpl mailTransport getEname->"+selMyEmail.getId());
		} catch (Exception e) {
			System.out.println("PejDaoImpl mailTransport Exception->"+e.getMessage());
		}
		return selMyEmail;
	}

// 관리자페이지 회원정보 수정
	@Override
	public int updateMemberDog2(MemberDogDTO memberDog) {
		System.out.println("PejDaoImpl updateMemberDog2 start...");
		int updateCount2 = 0;
		System.out.println("PejDaoImpl updateMemberDog2 getScEnddate"+memberDog.getScEnddate());
		System.out.println("PejDaoImpl updateMemberDog2 getDogNo"+memberDog.getDogNo());
		try {
			updateCount2 = session.update("updateMemberDog2", memberDog);
		} catch (Exception e) {
			System.out.println("PejDaoImpl updateMemberDog2 Exception->"+e.getMessage());
		}
		return updateCount2;
	}

// 강아지 정보

	@Override
	public MemberDogDTO detailDogInfo(Long dogNo) {
		MemberDogDTO detailDogInfo = null;
		System.out.println("PejDaoImpl detailDogInfo detailDogInfo start");
		
		try {
			detailDogInfo = session.selectOne("detailDogInfo", dogNo);
			System.out.println("PejDaoImpl detailDogInfo selectOne");
		} catch (Exception e) {
			System.out.println("PejDaoImpl detailDogInfo Exception ->"+e.getMessage());
		}
		return detailDogInfo;
	}


	@Override
	public int updateDogInfo(MemberDogDTO memberDog) {
		System.out.println("PejDaoImpl updateDogInfo start...");
		int updateCount1 = 0;
		System.out.println("PejDaoImpl updateDogInfo getScStartdate"+memberDog.getScStartdate());
		System.out.println("PejDaoImpl updateDogInfo getDogNo"+memberDog.getDogNo());
		try {
			updateCount1 = session.update("updateDogInfo", memberDog);
		} catch (Exception e) {
			System.out.println("PejDaoImpl updateDogInfo Exception->"+e.getMessage());
		}
		return updateCount1;
	}


	@Override
	public int updateDogInfo2(MemberDogDTO memberDog) {
		System.out.println("PejDaoImpl updateDogInfo2 start...");
		int updateCount2 = 0;
		System.out.println("PejDaoImpl updateDogInfo2 getScEnddate"+memberDog.getScEnddate());
		System.out.println("PejDaoImpl updateDogInfo2 getDogNo"+memberDog.getDogNo());
		try {
			updateCount2 = session.update("updateDogInfo2", memberDog);
		} catch (Exception e) {
			System.out.println("PejDaoImpl updateDogInfo2 Exception->"+e.getMessage());
		}
		return updateCount2;
	}
	

}
