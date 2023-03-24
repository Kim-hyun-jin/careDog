package com.oracle.s20221103.kdj.dao;

import java.util.List;

import com.oracle.s20221103.dto.CommonDTO;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.DogNoticeDTO;
import com.oracle.s20221103.dto.MemberDTO;

public interface DogNoticeDao {

	List<DogNoticeDTO> listDog(DogNoticeDTO dog);

	DogNoticeDTO selContent(Long id);

	int modContent(DogNoticeDTO dog);

	DogNoticeDTO modForm(Long noticeNo);

	List<DogDTO> insWrite(Long id);

	int insertNotice(DogNoticeDTO dog);

	Long delDogNotice(Long noticeNo);

	List<DogDTO> TinsWrite(Long id);

	MemberDTO MeminsWrite(Long id);

	List<DogNoticeDTO> teacherList(DogNoticeDTO tdog);

	int TinsertNotice(DogNoticeDTO dog);

	List<CommonDTO> getCategoryList();

	DogDTO getDogInfo(Long dogNo);

	int totalCount(Long id);

	int TtotalCount(Long id);

	MemberDTO TNameinsWrite(Long id);

}
