package com.oracle.s20221103.lhj.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.domain.Member;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.DogTypeDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.ResPayDTO;
import com.oracle.s20221103.dto.ReservationDTO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class LhjDaoImpl implements LhjDao {
	
	private final SqlSession session;
	//나의 반려견 리스트
	@Override
	public List<DogDTO> myDogList(DogDTO dog) {
		List<DogDTO> myDogList = null;
		System.out.println("LhjDaoImpl myDogList Start ..." );
		try {
			myDogList = session.selectList("hjMyDogList" , dog);
			System.out.println("LhjDaoImpl hjMyDogList myDogList.size()->"+myDogList.size());
			
		} catch (Exception e) {
			System.out.println("LhjDaoImpl hjMyDogList e.getMessage()->"+e.getMessage());
		}
		return myDogList;
	}
	//나의 반려견 정보 상세조회
	@Override
	public DogDTO mydogInfo(Long dogNo) {
		System.out.println("LhjDaoImpl mydogInfo start..");
		DogDTO dog = new DogDTO();
		try {
			dog = session.selectOne("hjMydogInfo", dogNo);
		} catch (Exception e) {
			System.out.println("LhjDaoImpl hjMydogInfo Exception->"+e.getMessage());
		}
		return dog;
	}
	//나의 반려견 정보 수정
	@Override
	public int UpdMyDogInfo(DogDTO dog) {
		System.out.println("LhjDaoImpl UpdMyDogInfo start..");		
		int updMydogCount= 0;
		try {
			updMydogCount = session.update("hjUpdMyDogInfo", dog);
		} catch (Exception e) {
			System.out.println("LhjDaoImpl hjUpdMyDogInfo Exception->"+e.getMessage());
		}
		return updMydogCount;
	}
	//나의 반려견 등록 시, 견종명 select로 조회
	@Override
	public List<DogTypeDTO> dogTypeSelect() {
		List<DogTypeDTO> dogTypeSelect = null;
		System.out.println("LhjDaoImpl dogTypeSelect Start..." );
		try {
			dogTypeSelect = session.selectList("hjdogTypeSelect");
			System.out.println("LhjDaoImpl dogTypeSelect dogTypeSelect.size()->" +dogTypeSelect.size());
		} catch (Exception e) {
			System.out.println("LhjDaoImpl dogTypeSelect Exception->"+e.getMessage());
		}
		
		return dogTypeSelect;
	}
	//나의 반려견 등록 
	@Override
	public int insertDog(DogDTO dog) {
		int result = 0;
		System.out.println("LhjDaoImpl insertDog Start ..." );
		try {
			System.out.println("LhjDaoImpl insertDog dog.getDogImgName()->"+dog.getDogImgName() );
			result = session.insert("hjInsertDog",dog);
			
			
		} catch (Exception e) {
			System.out.println("LhjDaoImpl insertDog Exception->"+e.getMessage());
		}
		return result;
	}
	//나의 반려견 정보 삭제
	@Override
	public DogDTO delMyDogInfo(Long dogNo) {
		int result = 0;
		System.out.println("LhjDaoImpl delMyDogInfo Start ..." );
		try {
			session.delete("hjdelMyDogInfoBh",dogNo);
			session.delete("hjdelMyDogInfoNotice",dogNo);
			result = session.delete("hjdelMyDogInfo",dogNo);
		} catch (Exception e) {
			System.out.println("LhjDaoImpl delMyDogInfo Exception->"+e.getMessage());
		}
		return null;
	}
	//관리자_전체 호텔예약 리스트 보기
	@Override
	public List<ReservationDTO> memberResList(ReservationDTO reservation) {
		List<ReservationDTO> memberResList = null;
		System.out.println("LhjDaoImpl memberResList Start ..." );
		try {
			memberResList = session.selectList("hjMemberResList" , reservation);
			System.out.println("LhjDaoImpl memberResList memberResList.size()->"+memberResList.size());
			
		} catch (Exception e) {
			System.out.println("LhjDaoImpl hjMemberResList e.getMessage()->"+e.getMessage());
		}
		return memberResList;
	}
	//관리자_예약번호 별 예약정보 조회
	@Override
	public ReservationDTO memberResDetail(ReservationDTO resNo) {
		System.out.println("LhjDaoImpl memberResDetail start..");
		ReservationDTO reservation = new ReservationDTO();
		try {
			reservation = session.selectOne("hjMemberResDetail", resNo);
		} catch (Exception e) {
			System.out.println("LhjDaoImpl hjMemberResDetail Exception->"+e.getMessage());
		}
		return reservation;
	}
	//관리자_호텔결제 조회
	@Override
	public List<ResPayDTO> hotelPayList(ResPayDTO resPay) {
		List<ResPayDTO> hotelPayList = null;
		System.out.println("LhjDaoImpl hotelPayList Start ..." );
		try {
			hotelPayList = session.selectList("hjhotelPayList" , resPay);
			System.out.println("LhjDaoImpl hjhotelPayList hotelPayList.size()->"+hotelPayList.size());
			
		} catch (Exception e) {
			System.out.println("LhjDaoImpl hjhotelPayList e.getMessage()->"+e.getMessage());
		}
		return hotelPayList;
	}
	//관리자_반려견 품종 관리
	@Override
	public List<DogTypeDTO> dogTypeList(DogTypeDTO dogType) {
		List<DogTypeDTO> dogTypeList = null;
		System.out.println("LhjDaoImpl dogTypeList Start ..." );
		try {
			dogTypeList = session.selectList("hjdogTypeList", dogType);
			System.out.println("LhjDaoImpl hjdogTypeList dogTypeList.size()->"+dogTypeList.size());
		} catch (Exception e) {
			System.out.println("LhjDaoImpl hjdogTypeList e.getMessage()->"+e.getMessage());
		}
		return dogTypeList;
	}
	//관리자_반려견 품종 등록
	@Override
	public int insertDogType(DogTypeDTO dogType) {
		int result = 0;
		System.out.println("LhjDaoImpl insertDogType Start ..." );
		
		try {
			result = session.insert("hjInsertDogType",dogType);
		} catch (Exception e) {
			System.out.println("LhjDaoImpl hjInsertDogType Exception->"+e.getMessage());
		}
		
		return result;
	}
	//관리자_반려견 품종 삭제 
	@Override
	public DogTypeDTO delDogType(String dogKind) {
		int result = 0;
		System.out.println("LhjDaoImpl delDogType Start ..." );
		try {
			result = session.delete("hjdelDogType",dogKind);
		} catch (Exception e) {
			System.out.println("LhjDaoImpl delDogType Exception->"+e.getMessage());
		}
		return null;
	}
	@Override
	public int totalDogType() {
		int totalDogType = 0;
		System.out.println("LhjDaoImpl Start total..." );
		
		try {
			totalDogType = session.selectOne("hjTotalDogType");
			System.out.println("LhjDaoImpl totalDogType->" +totalDogType);
		
		} catch (Exception e) {
			System.out.println("LhjDaoImpl totalDogType Exception->"+e.getMessage());
		}
		return totalDogType;
	}
	@Override
	public List<MemberDTO> teacherSelect() {
		List<MemberDTO> teacherSelect = null;
		System.out.println("LhjDaoImpl Start teacherSelect..." );
		try {
			teacherSelect = session.selectList("teacherSelect");
		} catch (Exception e) {
			System.out.println("LhjDaoImpl teacherSelect Exception->"+e.getMessage());
		}
		return teacherSelect;
	}
	
	@Override
	public int updateTeacher(MemberDTO member) {
		int result = 0;
		System.out.println("LhjDaoImpl Start updateTeacher..." );
		
		try {
			result = session.update("updateTeacher",member);
		} catch (Exception e) {
			System.out.println("LhjDaoImpl updateTeacher Exception->"+e.getMessage());
		}
		return result;
	}
	@Override
	public MemberDTO memberTeacherup(Long id) {
		MemberDTO member = new MemberDTO();
		try {
			member = session.selectOne("memberTeacherup",id);
		} catch (Exception e) {
			System.out.println("LhjDaoImpl updateTeacher Exception->"+e.getMessage());
		}
		return member;
	}
}
