package com.oracle.s20221103.lhj.dao;

import java.util.List;

import com.oracle.s20221103.domain.Member;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.DogTypeDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.ResPayDTO;
import com.oracle.s20221103.dto.ReservationDTO;

public interface LhjDao {

	List<DogDTO>          myDogList(DogDTO dog);   	    //나의 반려견리스트
	DogDTO                mydogInfo(Long dogNo);		//나의 반려견 정보
	int                   UpdMyDogInfo(DogDTO dog);	    //나의 반려견 정보 수정
	List<DogTypeDTO>      dogTypeSelect();				//견종명 리스트
	int                   insertDog(DogDTO dog);		//나의 반려견 등록
	DogDTO                delMyDogInfo(Long dogNo);	    //나의 반려견 삭제 
	
	List<ReservationDTO>  memberResList(ReservationDTO reservation); //전체 호텔예약 리스트
	ReservationDTO        memberResDetail(ReservationDTO resNo);	 //예약번호별 예약내역 조회
	List<ResPayDTO>       hotelPayList(ResPayDTO resPay);            //호텔 결제리스트 조회
	List<DogTypeDTO>      dogTypeList(DogTypeDTO dogType);			 //반려견 품종  리스트 조회
	int                   insertDogType(DogTypeDTO dogType);         //반려견 품종 등록   
	DogTypeDTO            delDogType(String dogKind);
	int                   totalDogType();
	List<MemberDTO>       teacherSelect();
	int                   updateTeacher(MemberDTO member);
	MemberDTO             memberTeacherup(Long id);

}
