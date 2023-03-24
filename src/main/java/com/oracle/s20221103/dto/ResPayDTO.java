package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class ResPayDTO {

	private Long 	resNo;   		//예약번호
	private String 	orderNum; 		//승인번호
	private String 	token;    		//토큰
	private Long 	resPrice;		//결제금액
	private String 	orderDate; 		//결제승인일
	private String 	orderStatus; 	//결제상태
	
	//조회용
	private Long id;   			//결제자 ID
	private String memberName; 		//결제자 이름
	private String memberCall;		//결제자 연락처
	
	// 페이지용
	private String search;
	private String keyword;
	private String pageNum;
	private int start;
	private int end;
	
}
