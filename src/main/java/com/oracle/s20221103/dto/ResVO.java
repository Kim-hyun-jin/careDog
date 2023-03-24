package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class ResVO {
	private int roomNum;			 //방번호
	private String resStartdate;  	 //시작일
	private String resEnddate;  	 //종료일
	private Long id;  				 //회원아이디
	private String dogName; 		 //애견명 
	private int dogSize;			 //애견사이즈
	private int deSexing;			 //중성화여부
	
	private int resPrice;
}
