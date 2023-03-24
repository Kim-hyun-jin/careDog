package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class DogTypeDTO {
	private String dogKind;  //견종명
	private String dogSize;	 //반려견사이즈
	private String dogInfo;  //반려견정보
	
	
	// 조회용
	private String search;   	private String keyword;
	private String pageNum;  
	private int start; 		 	private int end;
	

}
