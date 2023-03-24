package com.oracle.s20221103.kdj.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.s20221103.dto.CommonDTO;
import com.oracle.s20221103.dto.DogDTO;
import com.oracle.s20221103.dto.DogNoticeDTO;
import com.oracle.s20221103.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class DogNoticeDaoImpl implements DogNoticeDao {

	//private final EntityManager em;
	private final SqlSession session;

	@Override
	public List<DogNoticeDTO> listDog(DogNoticeDTO dog) {
		List<DogNoticeDTO> dogList = null;
		System.out.println("DogNoticeDaoImpl listDog start");
		try {
			dogList = session.selectList("djDogListAll", dog);
			System.out.println("DogNoticeDaoImpl listDog dogList.size ->" + dogList.size());
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl listDog e.getMessage" + e.getMessage());
		}
		return dogList;
	}

	@Override
	public DogNoticeDTO selContent(Long noticeNo) {
		DogNoticeDTO dogNoticeDTO = new DogNoticeDTO();
		try {
			dogNoticeDTO = session.selectOne("djContSelOne", noticeNo);
			System.out.println("DogNoticeDaoImpl selContent" + dogNoticeDTO.getNoticeNo());
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl selContent Exception->" + e.getMessage());
		}
		return dogNoticeDTO;
	}

	@Override
	public DogNoticeDTO modForm(Long noticeNo) {
		System.out.println("수정페이지 Dao");
		DogNoticeDTO dog = new DogNoticeDTO();
		try {
			dog = session.selectOne("djModOne", noticeNo);
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl modForm Exception->" + e.getMessage());
		}
		return dog;
	}

	@Override
	public int modContent(DogNoticeDTO dog) {
		System.out.println("DogNoticeDaoImpl modContent start");
		int count = 0;
		try {
			count = session.update("djModContent", dog);
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl modContent Exception->" + e.getMessage());

		}
		return count;
	}

//알림장 글쓰기
	@Override
	public List<DogDTO> insWrite(Long id) {
		List<DogDTO> dogList = null;
		try {
			dogList = session.selectList("djInsWrite", id);
			System.out.println("DogNoticeDaoImpl dog.size() -> " + dogList.size());
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl insWrite Exception->" + e.getMessage());
		}
		return dogList;
	}

	// 알림장 글 Insert
	@Override
	public int insertNotice(DogNoticeDTO dog) {
		int result = 0;
		System.out.println("DogNoticeDaoImpl insertNotice start");
		System.out.println("DogNoticeDaoImpl insertNotice start" + dog.toString());

		try {
			result = session.insert("djInsertNotice", dog);
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl insertNotice Exception->" + e.getMessage());
		}
		return result;
	}

	@Override
	public Long delDogNotice(Long noticeNo) {
		Long result = null;
		System.out.println("DogNoticeDaoImpl delDogNotice start");
		try {
			result = (long) session.delete("djDelDogNotice", noticeNo);
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl delDogNotice Exception->" + e.getMessage());

		}

		return result;
	}

	@Override
	public List<DogDTO> TinsWrite(Long id) {
		List<DogDTO> dogList = null;
		try {
			dogList = session.selectList("djTinsWrite", id);
			System.out.println("DogNoticeDaoImpl dog.size() -> " + dogList.size());
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImplTinsWrite Exception->" + e.getMessage());
		}
		return dogList;
	}

	@Override
	public MemberDTO MeminsWrite(Long id) {
		MemberDTO teacher = null;
		try {
			teacher = session.selectOne("djMeminsWrite", id);
			System.out.println("DogNoticeDaoImpl teacher -> " + teacher);
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl MeminsWrite Exception->" + e.getMessage());
		}
		return teacher;
	}

	@Override
	public List<DogNoticeDTO> teacherList(DogNoticeDTO Tdog) {
		List<DogNoticeDTO> dog = null;
		try {
			dog = session.selectList("djTeacherList", Tdog);
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl teacherList Exception->" + e.getMessage());
		}
		return dog;
	}

	@Override
	public int TinsertNotice(DogNoticeDTO dog) {
		int result = 0;
		System.out.println("DogNoticeDaoImpl insertNotice start");
		System.out.println("DogNoticeDaoImpl insertNotice start" + dog.toString());

		try {
			result = session.insert("djTeacherInsertNotice", dog);
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl insertNotice Exception->" + e.getMessage());
		}
		return result;
	}

	@Override
	public List<CommonDTO> getCategoryList() {
		log.info("getCategoryList() start...");
		List<CommonDTO> result = null;
		try {
			result = session.selectList("getCategoryList");
		} catch (Exception e) {
			log.info("getCategoryList() e.getMessage() : " + e.getMessage());
		}
		log.info("getCategoryList() finish...");
		return result;
	}

	@Override
	public DogDTO getDogInfo(Long dogNo) {
		DogDTO dog = null;
		try {
			dog = session.selectOne("djgetDogInfoId", dogNo);
			System.out.println("DogNoticeDaoImplselContent" + dog.getDogNo());
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl selContent Exception->" + e.getMessage());
		}
		return dog;

	}

	@Override
	public int totalCount(Long id) {
		int totalCount = 0;
		try {
			totalCount = session.selectOne("djTotalCount", id);
			System.out.println("토탈 수 => " + totalCount);
		} catch (Exception e) {
			System.out.println("" + e.getMessage());
		}
		return totalCount;
	}

	@Override
	public int TtotalCount(Long id) {
		int TtotalCount = 0;
		try {
			TtotalCount = session.selectOne("djTeacherTotalCnt", id);
		} catch (Exception e) {
			System.out.println(""+e.getMessage());
		}
		return TtotalCount;
	}

	@Override
	public MemberDTO TNameinsWrite(Long id) {
		MemberDTO teacherName = null;
		try {
			teacherName = session.selectOne("djTNameinsWrite", id);
			System.out.println("DogNoticeDaoImpl teacher -> " + teacherName);
		} catch (Exception e) {
			System.out.println("DogNoticeDaoImpl MeminsWrite Exception->" + e.getMessage());
		}
		return teacherName;
	}

}
