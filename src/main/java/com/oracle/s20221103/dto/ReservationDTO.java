package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class ReservationDTO {
	private Long resNo;      		 //예약번호
	private int roomNum;			 //방번호
	private String resStartdate;  	 //시작일
	private String resEnddate;  	 //종료일
	private Long id;  				 //회원아이디
	private String dogName; 		 //애견명 
	private int dogSize;			 //애견사이즈
	private int deSexing;			 //중성화여부
	
	//member 조회용
	private String  memberName;      //회원이름
	private String	memberCall;		 //회원연락처
	private String	memberAddress;	 //회원주소
	
	//Respay 조회용
	private Long resPrice;           //결제금액
	private String orderStatus;		 //결제상태
	
	//hotel 조회용
	private String roomType;   		//방유형이름
	
	// 페이지용
	private String search;
	private String keyword;
	private String pageNum;
	private int start;
	private int end;
}
