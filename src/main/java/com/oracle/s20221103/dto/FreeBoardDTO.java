package com.oracle.s20221103.dto;


import lombok.Data;


@Data
public class FreeBoardDTO {

	private Long freeboardNo;
	private String category;
	private Long id;
	private String title;
	private String username;
	private String content;
	private String regdate;
	private Long viewCount;
	private Long recommand;
	
//	readOnly
	private String role;
	private String memberName;
	
}
