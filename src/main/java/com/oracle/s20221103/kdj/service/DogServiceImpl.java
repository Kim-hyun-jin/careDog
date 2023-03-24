package com.oracle.s20221103.kdj.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.s20221103.dto.CommonDTO;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.DogNoticeDTO;
import com.oracle.s20221103.dto.MemberDTO;
import com.oracle.s20221103.kdj.dao.DogNoticeDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DogServiceImpl implements DogService {

	private final DogNoticeDao dogDao;

	@Override
	public List<DogNoticeDTO> listDog(DogNoticeDTO dog) {
		System.out.println("dog" +dog.toString());
		List<DogNoticeDTO> dogList = null;
		System.out.println("DogServiceImpl Start ");
		dogList = dogDao.listDog(dog);
		System.out.println("DogServiceImpl listDog 갯수 -> " + dogList.size());
		return dogList;
	}

	@Override
	public DogNoticeDTO selContent(Long noticeNo) {
		DogNoticeDTO dogNoticeDTO = null;
		dogNoticeDTO = dogDao.selContent(noticeNo);
		return dogNoticeDTO;
	}
	
	//수정페이지 이동
	@Override
	public DogNoticeDTO modForm(Long noticeNo) {
		System.out.println("DogServiceImpl 수정페이지 이동 Start ");
		DogNoticeDTO dog = null;
		dog = dogDao.modForm(noticeNo);
		return dog;
	}
	

	@Override
	public int modContent(DogNoticeDTO dog) {
		System.out.println("DogService modContent Start");
		int count = 0;
		count = dogDao.modContent(dog);
		return count;
	}
	
	// 알림장글쓰기 - 강아지 이름 가져오기
	@Override
	public List<DogDTO> insWrite(Long id) {
		List<DogDTO> dog = null;
		System.out.println("DogServiceImpl insWrite Start");
		dog = dogDao.insWrite(id);
		return dog;
	}
	// 강아지 알림장 Insert
	@Override
	public int insertNotice(DogNoticeDTO dog) {
		int result = 0;
		System.out.println("DogServiceImpl insertNotice Start");
		result = dogDao.insertNotice(dog);
		return result;
	}
	// 알림장 삭제
	@Override
	public Long delDogNotice(Long noticeNo) {
		Long result ;
		System.out.println("DogServiceImpl delDogNotice Start");
		
		result = dogDao.delDogNotice(noticeNo);

		return result;
	}

	@Override
	public List<DogDTO> TinsWrite(Long id) {
		List<DogDTO> dog = null;
		System.out.println("DogServiceImpl TinsWrite Start");
		dog = dogDao.TinsWrite(id);
		return dog;
	}
	// 선생님 목록 가져오기
	@Override
	public MemberDTO MeminsWrite(Long id) {
		MemberDTO member = null;
		System.out.println("DogServiceImpl MeminsWrite Start");
		member = dogDao.MeminsWrite(id);
		return member;
	}

	@Override
	public List<DogNoticeDTO> teacherList(DogNoticeDTO Tdog) {
		List<DogNoticeDTO> dog = null;
		System.out.println("DogServiceImpl teacherList Start..");
		dog = dogDao.teacherList(Tdog);
		return dog;
	}

	@Override
	public int TinsertNotice(DogNoticeDTO dog) {
		int result = 0;
		System.out.println("DogServiceImpl TinsertNotice Start..");
		result=dogDao.TinsertNotice(dog);
		return result;
	}

	@Override
	public List<CommonDTO> getCategoryList() {
		return dogDao.getCategoryList();
	}

	@Override
	public DogDTO getDogInfo(Long dogNo) {
		System.out.println("DogServiceImpl  강아지 번호에 대한 주인 id 호출");
		DogDTO dog = null;
		dog = dogDao.getDogInfo(dogNo);
		return dog;
	
	}

	@Override
	public int totalCount(Long id) {
		System.out.println("DogServiceImpl totalCount start ~!");
		int totalCount = dogDao.totalCount(id);
		return totalCount;
	}

	@Override
	public int TtotalCount(Long id) {
		System.out.println("DogServiceImpl 선생님카운트 start ~!");
		int TtotalCount = dogDao.TtotalCount(id);
		return TtotalCount;
	}

	@Override
	public MemberDTO TNameinsWrite(Long id) {
		MemberDTO member = null;
		System.out.println("DogServiceImpl TNameinsWrite Start");
		member = dogDao.TNameinsWrite(id);
		return member;
	}	
}
