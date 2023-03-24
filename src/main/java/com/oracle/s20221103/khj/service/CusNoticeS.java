package com.oracle.s20221103.khj.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.oracle.s20221103.domain.CusNotice;
import com.oracle.s20221103.dto.CusNoticeDTO;
import com.oracle.s20221103.khj.dto.AttachFileDTO;
import com.oracle.s20221103.khj.dto.PageRequestDTO;
import com.oracle.s20221103.khj.dto.PageResponseDTO;
import com.oracle.s20221103.khj.dto.SearchFormDTO;

public interface CusNoticeS {

	// 나중에 페이징 처리용 객체 파라미터로 전달(pageable)
	List<com.oracle.s20221103.domain.CusNotice> selNoticeAll();

	Page<com.oracle.s20221103.domain.CusNotice> selNoticeAll(Pageable pageable);

	// CusNoticeRepository findAll(pageRequest)
	PageResponseDTO<CusNotice> findAll(PageRequestDTO pageRequestDTO);

	CusNotice selNotice(Long cusNo);

	int updNotice(Long cusNo, CusNotice notice);

	void delNotice(Long cusNo);

	// ========================MyBatis INSERT==============================//
	int insNotice(CusNoticeDTO notice);
	// ========================MyBatis INSERT==============================//

	CusNotice insNotice(CusNotice notice) throws Exception;

	int insIMG(Long cusNo, CusNotice cusNtocie, AttachFileDTO attachDTO) throws Exception;

	// ==================검색=================================//
	PageResponseDTO<CusNotice> search(PageRequestDTO pageRequestDTO, Model model);

}
