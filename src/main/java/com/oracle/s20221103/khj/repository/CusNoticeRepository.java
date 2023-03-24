package com.oracle.s20221103.khj.repository;

import com.oracle.s20221103.domain.CusNotice;

import com.oracle.s20221103.khj.dto.PageRequestDTO;
import com.oracle.s20221103.khj.dto.PageResponseDTO;

public interface CusNoticeRepository {

	public Long getTotal();

	public PageResponseDTO<CusNotice> findAll(PageRequestDTO pageRequestDTO);

	public PageResponseDTO<CusNotice> findBytitleOrderByRegdate(PageRequestDTO pageRequestDTO);

	public Long byTitleCount(PageRequestDTO pageRequestDTO);

	public PageResponseDTO<CusNotice> findByContentOrderByRegdate(PageRequestDTO pageRequestDTO);

	public Long byContentCount(PageRequestDTO pageRequestDTO);

	public PageResponseDTO<CusNotice> findByAllOrderByRegdate(PageRequestDTO pageRequestDTO);

	public Long byAllCount(PageRequestDTO pageRequestDTO);

	// 저장 & 파일

}
