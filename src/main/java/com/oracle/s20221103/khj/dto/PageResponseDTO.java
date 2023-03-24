package com.oracle.s20221103.khj.dto;

import java.util.List;

import com.oracle.s20221103.domain.CusNotice;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageResponseDTO<E> {

	private int page;//currentPage
	private int size;//rowSize
	private int pageBlock = 5;
	private Long total;//전체글수
	
	private int totalPage;
	
	private int start;//시작페이지
	private int end;//끝페이지
	
	
	private int startPage;
	private int endPage;
	
	private boolean prev;
	private boolean next;
	
	private List<CusNotice> noticeList;
	
	public PageResponseDTO(Long total, PageRequestDTO pageRequestDTO,List<CusNotice> noticeList) {
		
		this.page = pageRequestDTO.getPage();
		this.size = pageRequestDTO.getSize();
		this.total = total;
		this.totalPage = (int)Math.ceil((double)total/size);
		
		this.noticeList = noticeList;
	
		this.startPage = page - (page-1) % pageBlock;
		this.endPage = startPage + pageBlock -1;
		
		this.start = (page-1) * pageBlock + 1;		
		this.end = start + pageBlock - 1;	
		
		int last = (int)Math.ceil((total/(double)size));//마지막 페이지 번호
		this.end = end > last ? last: end;
		this.prev = this.startPage > 1;
		this.next = total > this.end * this.size;
	}
	
	
}
