package com.oracle.s20221103.khj.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oracle.s20221103.domain.CusNotice;
import com.oracle.s20221103.khj.dto.AttachFileDTO;

public interface CusNoticeJPA {

	// 초기조회
	List<CusNotice> selectAll();

	// 페이징 적용 조회
	Page<CusNotice> selectAll(Pageable pageable);

	CusNotice select(Long cusNo);

	// 입력
	CusNotice insert(CusNotice cusNotice);

	// 수정
	int update(Long cusNo, CusNotice cusNotice) throws Exception;

	// 삭제
	void delete(Long cusNo) throws Exception;

	// 파일저장용
	int updateFile(Long cusNo, CusNotice cusNotice, AttachFileDTO attachDTO) throws Exception;

}
