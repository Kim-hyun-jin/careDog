package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class PageDTO {

	private int currentPage = 1 ;	private int rowPage = 10;
	private int pageBlock = 5;
	private int totalPage;
	private int startPage;
	private int endPage;
	private boolean pre,next;
	private int start;
	private int end;
	private String searchOption;
	private String searchString;
	
	private int total;
	private CriteriaDTO cri;
	
	public PageDTO(int total, String currentPage1) {
		this.total = total;		//140
		if(currentPage1 != null) {
			this.currentPage=Integer.parseInt(currentPage1);	//2
		}
		start = (currentPage-1) * rowPage+1;		//시작시1	11
		end = start + rowPage - 1;					//시작시10	20
		totalPage = (int)Math.ceil((double)total/rowPage);//시작시 2 5 14
		
		startPage = currentPage - (currentPage-1)%pageBlock;//시작시 1
		endPage = startPage + pageBlock -1;//10
//			10		14
		if(endPage>totalPage) {
			endPage=totalPage;
		}
	}
	
	
	
}
