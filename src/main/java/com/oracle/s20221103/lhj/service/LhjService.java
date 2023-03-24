package com.oracle.s20221103.lhj.service;

import java.util.List;

import com.oracle.s20221103.domain.Member;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.DogTypeDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.ResPayDTO;
import com.oracle.s20221103.dto.ReservationDTO;

public interface LhjService {
	List<DogDTO> 	           myDogList(DogDTO dog);
	DogDTO                     mydogInfo(Long dogNo);
	int                        UpdMyDogInfo(DogDTO dog);
	List<DogTypeDTO>           dogTypeSelect();
	int                        insertDog(DogDTO dog);
	DogDTO                     delMyDogInfo(Long dogNo);
	List<ReservationDTO>       memberResList(ReservationDTO reservation);
	ReservationDTO             memberResDetail(ReservationDTO resNo);
	List<ResPayDTO>            hotelPayList(ResPayDTO resPay);
	List<DogTypeDTO>           dogTypeList(DogTypeDTO dogType);
	int                        insertDogType(DogTypeDTO dogType);
	DogTypeDTO                 delDogType(String dogKind);
	int                        totalDogType();
	List<MemberDTO>            teacherSelect();
	int                        updateTeacher(MemberDTO member);
	MemberDTO                  memberTeacherup(Long id);

}
