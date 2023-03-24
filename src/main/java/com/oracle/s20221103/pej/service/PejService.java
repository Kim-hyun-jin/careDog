package com.oracle.s20221103.pej.service;

import java.util.List;

import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.dto.MemberDogDTO;

public interface PejService {


	List<MemberDogDTO>  	selMemberDogList();

	List<MemberDogDTO> 		detailMemberDog(Long id);

	MemberDogDTO 			detailMemberDog1(Long id);

	List<MemberDogDTO>		selRole();

	int 					updateMemberDog(MemberDogDTO memberDog);

	MemberDogDTO 			selMyInfo(MemberDogDTO memberDog);

	int 					updateMyinfo(MemberDogDTO memberDog);

	MemberDogDTO 			updateFormMyInfo(Long id);

	MemberDogDTO 			selMyInfo1(Long id);

	MemberDogDTO 			myPwEdit(Long id);

	int  					updateMyPw(MemberDogDTO memberDog);

	MemberDogDTO  			myPwEditAfter(Long id);

	MemberDogDTO 			myInfoDrop(Long id);

	int				 		myInfoDropAfter(MemberDogDTO memberDog);

	int 					memberDogEmail(MemberDogDTO memberDog);

	MemberDogDTO 			selMyEmail(Long id);

	int 					updateMemberDog2(MemberDogDTO memberDog);

	MemberDogDTO 			detailDogInfo(Long dogNo);

	int 					updateDogInfo(MemberDogDTO memberDog);

	int 					updateDogInfo2(MemberDogDTO memberDog);   






	



}
