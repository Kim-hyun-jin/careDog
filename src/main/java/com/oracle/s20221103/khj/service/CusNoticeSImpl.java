package com.oracle.s20221103.khj.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.oracle.s20221103.domain.CusNotice;
import com.oracle.s20221103.dto.CusNoticeDTO;
import com.oracle.s20221103.khj.dao.CusNoticeJPA;

import com.oracle.s20221103.khj.dto.AttachFileDTO;
import com.oracle.s20221103.khj.dto.PageRequestDTO;
import com.oracle.s20221103.khj.dto.PageResponseDTO;
import com.oracle.s20221103.khj.repository.CusNoticeJpaRepository;
import com.oracle.s20221103.khj.repository.CusNoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CusNoticeSImpl implements CusNoticeS {

	private final CusNoticeJPA cusNoticeJPA;
	private final CusNoticeJpaRepository cusNoticeJpaRepository;
	private final CusNoticeRepository cusNoticeRepository;
	
	
	
	@Override
	public List<com.oracle.s20221103.domain.CusNotice> selNoticeAll() {
		
		List<com.oracle.s20221103.domain.CusNotice> noticeList = cusNoticeJPA.selectAll();
		return noticeList;
	}
	
	@Override
	public Page<CusNotice> selNoticeAll(Pageable pageable) {
		Page<CusNotice>  noticeList = cusNoticeJPA.selectAll(pageable);
		return noticeList;
	}
	
	@Override
	public PageResponseDTO<CusNotice> findAll(PageRequestDTO pageRequestDTO) {
		PageResponseDTO<CusNotice> dto =cusNoticeRepository.findAll(pageRequestDTO);
		
		return dto;
	}
	
	
	@Override
	public CusNotice selNotice(Long cusNo) {

		CusNotice notice = cusNoticeJPA.select(cusNo);

		return notice;
	}

	
	@Override
	public CusNotice insNotice(CusNotice notice) throws Exception {
	
		
		CusNotice result = cusNoticeJPA.insert(notice);

		
		return result;
	}
	
	
	//파일경로저장
	@Override
	public int insIMG(Long cusNo, CusNotice cusNotice, AttachFileDTO attachDTO) throws Exception {
//		int result =
//				cusNoticeJpaRepository.insIMG(attachDTO.getCusNo(), attachDTO.getUploadPath(), attachDTO.getFileName());

		int result =cusNoticeJPA.updateFile(cusNo, cusNotice, attachDTO);
		return result;
	}


	
	@Override
	public int updNotice(Long cusNo, CusNotice notice) {
		
		int result =0;
		try {
			result = cusNoticeJPA.update(cusNo, notice);
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void delNotice(Long cusNo) {
		try {
			cusNoticeJPA.delete(cusNo);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	
	//==========================================검색=================================//

	@Override
	public PageResponseDTO<CusNotice> search(PageRequestDTO pageRequestDTO,Model model) {
		
		PageResponseDTO<CusNotice>  dto = null;
		//Long total = 0L;
		if((pageRequestDTO.getCategory()).equals("title")) {
			System.out.println("Service findBytitleOrderByRegdateDesc start");
			dto = cusNoticeRepository.findBytitleOrderByRegdate(pageRequestDTO);
//			 total = cusNoticeRepository.byTitleCount(pageRequestDTO);
//			 model.addAttribute("total", total);
		}
		if((pageRequestDTO.getCategory()).equals("content")) {
			dto = cusNoticeRepository.findByContentOrderByRegdate(pageRequestDTO);
//			total = cusNoticeRepository.byContentCount(pageRequestDTO);
//			model.addAttribute("total", total);
		}
		if((pageRequestDTO.getCategory()).equals("titleContent")) {
			dto = cusNoticeRepository.findByAllOrderByRegdate(pageRequestDTO);
//			total = cusNoticeRepository.byAllCount(pageRequestDTO);
//			model.addAttribute("total", total);
		}
		
		return dto;
	}

	@Override
	public int insNotice(CusNoticeDTO notice) {
		// TODO Auto-generated method stub
		return 0;
	}




}
