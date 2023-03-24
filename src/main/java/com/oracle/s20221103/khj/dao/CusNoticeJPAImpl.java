package com.oracle.s20221103.khj.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Repository;

import com.oracle.s20221103.domain.CusNotice;
import com.oracle.s20221103.khj.dto.AttachFileDTO;
import com.oracle.s20221103.khj.repository.CusNoticeJpaRepository;

//@Service
@Repository
public class CusNoticeJPAImpl implements CusNoticeJPA {

	@Autowired
	private final CusNoticeJpaRepository cusNoticeRepository;

	public CusNoticeJPAImpl(CusNoticeJpaRepository cusNoticeRepository) {

		this.cusNoticeRepository = cusNoticeRepository;
	}

	// DB 문제로 조회 안됨
	@Override
	public List<CusNotice> selectAll() {

		List<CusNotice> noticeList = cusNoticeRepository.findAll();
		System.out.println("CusNotiveJPAImpl selectAll size:" + noticeList.size());
		return noticeList;
	}

	@Override
	public Page<CusNotice> selectAll(Pageable pageable) {
		Page<CusNotice> noticePageList = cusNoticeRepository.findAll(pageable);

		return noticePageList;
	}

	@Override
	public CusNotice select(Long cusNo) {

		CusNotice selectedNotice = cusNoticeRepository.getReferenceById(cusNo);
		// cusNoticeRepository.findById(cusNo);
		return selectedNotice;
	}

	@Override
	public CusNotice insert(CusNotice notice) {

		CusNotice insertedNotice = cusNoticeRepository.save(notice);
		// log
		return insertedNotice;
	}

	@Override
	public int update(Long cusNo, CusNotice cusNotice) throws Exception {

		int result = 0;
		Optional<CusNotice> selectedNotice = cusNoticeRepository.findById(cusNo);

		if (selectedNotice.isPresent()) {
			CusNotice notice = selectedNotice.get();
			notice.setTitle(cusNotice.getTitle());
			notice.setContent(cusNotice.getContent());
			CusNotice updatedNotice = cusNoticeRepository.save(notice);

			System.out.println("update result: " + updatedNotice.getTitle());
		} else {

			throw new Exception();
		}
		result = 1;
		return result;
	}

	@Override
	public int updateFile(Long cusNo, CusNotice cusNotice, AttachFileDTO attachDTO) throws Exception {

		int result = 0;
		Optional<CusNotice> selectedNotice = cusNoticeRepository.findById(cusNo);

		if (selectedNotice.isPresent()) {
			CusNotice notice = selectedNotice.get();
			notice.setTitle(cusNotice.getTitle());
			notice.setContent(cusNotice.getContent());
			notice.setAttachPath(attachDTO.getUploadPath());
			CusNotice updatedNotice = cusNoticeRepository.save(notice);

			System.out.println("file upload result: " + updatedNotice.getTitle());
		} else {

			throw new Exception();
		}
		result = 1;
		return result;
	}

	@Override
	public void delete(Long cusNo) throws Exception {

		Optional<CusNotice> selectedNotice = cusNoticeRepository.findById(cusNo);

		if (selectedNotice.isPresent()) {
			CusNotice notice = selectedNotice.get();

			cusNoticeRepository.delete(notice);

		} else {

			throw new Exception();
		}

	}

}
