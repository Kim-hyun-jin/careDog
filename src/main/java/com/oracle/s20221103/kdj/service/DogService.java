package com.oracle.s20221103.kdj.service;

import java.util.List;

import com.oracle.s20221103.dto.CommonDTO;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.DogNoticeDTO;
import com.oracle.s20221103.dto.MemberDTO;

public interface DogService {
//   멤버리스트
	List<DogNoticeDTO> listDog(DogNoticeDTO dog );
//   Content 보기	
	DogNoticeDTO selContent(Long noticeNo);
//   Content 수정
	int modContent(DogNoticeDTO dog);
//    수정페이지 이동
	DogNoticeDTO modForm(Long noticeNo);
//    글쓰기페이지 강아지 이름 받아오기
	List<DogDTO> insWrite(Long id);
//    글쓰기
	int insertNotice(DogNoticeDTO dog);
//     삭제	
	Long delDogNotice(Long noticeNo);
//     선생님 글쓰기
	List<DogDTO> TinsWrite(Long id);
//       
	MemberDTO MeminsWrite(Long id);

	List<DogNoticeDTO> teacherList(DogNoticeDTO dog);

	int TinsertNotice(DogNoticeDTO dog);

	List<CommonDTO> getCategoryList();

	DogDTO getDogInfo(Long dogNo);

	int totalCount(Long id);

	int TtotalCount(Long id);
	//선생님 본인이름 가져오기
	MemberDTO TNameinsWrite(Long id);

	

	



	



}
