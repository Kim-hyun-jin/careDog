package com.oracle.s20221103.dto;

import lombok.Data;

@Data
public class QADTO {

	private Integer questionNo;
	private Long id;
	private String title;
	private String content;
	private String status;
	private String teacherId;
	private String answerContent;
	
	// member 조회용
	private String memberName;		// 회원이름
	private String 	role;			// 권한
	
	// page
	private String search;
	private String keyword;
	private String pageNum;
	private int start;
	private int end;
}
