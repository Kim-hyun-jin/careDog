package com.oracle.s20221103.lhj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.DogTypeDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.ResPayDTO;
import com.oracle.s20221103.dto.ReservationDTO;
import com.oracle.s20221103.lhj.dao.LhjDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LhjServiceImpl implements LhjService {
	
	private final LhjDao lhjDao;
	//사용자_나의 반려견 리스트 
	@Override
	public List<DogDTO> myDogList(DogDTO dog) {
		List<DogDTO> myDogList =null;
		System.out.println("LhjServiceImpl myDogList Start..." );
		myDogList = lhjDao.myDogList(dog);
		System.out.println("LhjServiceImpl myDogList myDogList.size()->" +myDogList.size());
		return myDogList;
	}
	//사용자_나의 반려견 별 상세정보
	@Override
	public DogDTO mydogInfo(Long dogNo) {
		System.out.println("LhjServiceImpl mydogInfo Start...");
		DogDTO dog = null;
		dog = lhjDao.mydogInfo(dogNo);
		return dog;
	}
	//사용자_나의 반려견 정보 수정 
	@Override
	public int UpdMyDogInfo(DogDTO dog) {
		System.out.println("LhjServiceImpl UpdMyDogInfo Start...");
		int updMydogCount = 0;
		updMydogCount = lhjDao.UpdMyDogInfo(dog);
		return updMydogCount;
	}
	//사용자_나의 반려견 등록시, 품종명 select로 불러오기
	@Override
	public List<DogTypeDTO> dogTypeSelect() {
		List<DogTypeDTO> dogType =null;
		System.out.println("LhjServiceImpl dogTypeSelect Start..." );
		dogType = lhjDao.dogTypeSelect();
		System.out.println("LhjServiceImpl dogTypeSelect deptList.size()->" +dogType.size());
		return dogType;
	}
	//사용자_나의 반려견 등록
	@Override
	public int insertDog(DogDTO dog) {
		int result = 0;
		System.out.println("LhjServiceImpl insertDog Start..." );
		result = lhjDao.insertDog(dog);
		return result;
	}
	//사용자_나의 반려견 정보 삭제 
	@Override
	public DogDTO delMyDogInfo(Long dogNo) {
		DogDTO dog = null;
		System.out.println("LhjServiceImpl delMyDogInfo Start..." );
		dog=lhjDao.delMyDogInfo(dogNo);
		return dog;
	}
	//관리자_전체 호텔예약 리스트 조회
	@Override
	public List<ReservationDTO> memberResList(ReservationDTO reservation) {
		List<ReservationDTO> memberResList = null;
		System.out.println("LhjServiceImpl memberResList Start..." );
		memberResList=lhjDao.memberResList(reservation);
		System.out.println("LhjServiceImpl memberResList memberResList.size()->" +memberResList.size());
		return memberResList;
	}
	//관리자_예약번호 별 정보 조회
	@Override
	public ReservationDTO memberResDetail(ReservationDTO resNo) {
		System.out.println("LhjServiceImpl memberResDetail Start...");
		ReservationDTO reservation = null;
		reservation = lhjDao.memberResDetail(resNo);
		return reservation;
	}
	//관리자_호텔결제 내역 조회
	@Override
	public List<ResPayDTO> hotelPayList(ResPayDTO resPay) {
		List<ResPayDTO> resPayList =null;
		System.out.println("LhjServiceImpl hotelPayList Start..." );
		resPayList = lhjDao.hotelPayList(resPay);
		System.out.println("LhjServiceImpl myDogList resPayList.size()->" +resPayList.size());
		return resPayList;
	}
	//관리자_반려견 품종관리 리스트
	@Override
	public List<DogTypeDTO> dogTypeList(DogTypeDTO dogType) {
		List<DogTypeDTO> dogTypeList = null;
		System.out.println("LhjServiceImpl dogTypeList Start..." );
		dogTypeList = lhjDao.dogTypeList(dogType);
		System.out.println("LhjServiceImpl myDogList dogTypeList.size()->" +dogTypeList.size());
		return dogTypeList;
	}
	//관리자_반려견 품종 등록
	@Override
	public int insertDogType(DogTypeDTO dogType) {
		int result = 0;
		System.out.println("LhjServiceImpl insertDogType Start..." );
		result = lhjDao.insertDogType(dogType);
		return result;
	}
	//관리자_반려견 품종 삭제 
	@Override
	public DogTypeDTO delDogType(String dogKind) {
		DogTypeDTO dogType = null;
		System.out.println("LhjServiceImpl delDogType Start..." );
		dogType=lhjDao.delDogType(dogKind);
		return dogType;
	}

	@Override
	public int totalDogType() {
		System.out.println("LhjServiceImpl Start totalDogType..." );
		int totalDogType = lhjDao.totalDogType();
		System.out.println("LhjServiceImpl totalDogType totalDogType->" + totalDogType);
		return totalDogType;
	}
	//선생님 리스트 불러오기
	@Override
	public List<MemberDTO> teacherSelect() {
		List<MemberDTO> teacher =null;
		System.out.println("LhjServiceImpl Start teacherSelect..." );
		teacher = lhjDao.teacherSelect();
		System.out.println("LhjServiceImpl teacherSelect teacher.size()->" +teacher.size());
		return teacher;
	}
	//선생님 등록, 수정
	@Override
	public int updateTeacher(MemberDTO member) {
		int result = 0;
		System.out.println("LhjServiceImpl updateTeacher Start..." );
		result = lhjDao.updateTeacher(member);
		return result;
	}
	//멤버 아이디 값 불러오기 
	@Override
	public MemberDTO memberTeacherup(Long id) {
		System.out.println("LhjServiceImpl memberTeacherup Start..." );
		MemberDTO member= null;
		member= lhjDao.memberTeacherup(id);
		return member;
	}
	

}
